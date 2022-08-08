package ru.netology.graphics.image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;


public class Converter implements TextGraphicsConverter {

  private int width;  //GServ
  private int height;   //GServ
  private double maxRatio;   //GServ
  private TextColorSchema schema;
  private int newWidth;
  private int newHeight;

  public Converter() {

    schema = new ColorSchema();
  }

  @Override
  public void setMaxWidth(int width) {
    this.width = width;
  }

  @Override
  public void setMaxHeight(int height) {
    this.height = height;
  }

  @Override
  public void setMaxRatio(double maxRatio) {
    this.maxRatio = maxRatio;
  }

  @Override
  public void setTextColorSchema(TextColorSchema colorSchema) {
    this.schema = colorSchema;
  }


  //по сторонам и формату
  private void maxiRatio(BufferedImage img) throws BadImageSizeException {
    int ratio;
    int wh = (img.getWidth() / img.getHeight());
    int hw = (img.getHeight() / img.getWidth());

    System.out.println("W/H - " + img.getWidth() / img.getHeight() + " H/W - "
        + img.getHeight() / img.getWidth() + " wh - " + wh + " hw - " + hw + " ширина - "
        + img.getWidth() + " высота - " + img.getHeight() + " maxRatio - " + maxRatio);

    if (wh > hw) {
      if (hw == 0) {
        hw = 1;
      }
      ratio = (wh / hw);

      if (ratio > maxRatio && maxRatio != 0) {
        throw new BadImageSizeException(ratio, maxRatio);
      }
      System.out.println("соотношение " + ratio);
    } else if (hw > wh) {
      if (wh == 0) {
        wh = 1;
      }
      ratio = (hw / wh);
      if (ratio > maxRatio && maxRatio != 0) {
        throw new BadImageSizeException(ratio, maxRatio);
      }
      System.out.println("соотношение " + ratio);
    }

  }

  // вывод
  private void printText(char[][] graph, StringBuilder strBild) {
    for (char[] chars : graph) {
      for (char aChar : chars) {

        strBild.append(aChar); //ели нужно два символа на пиксель
        strBild.append(aChar);
      }
      strBild.append("\n");
    }
  }

  // масштабирование
  private void resImage(BufferedImage img) {
    int resW;
    int resH;
    int maxRes = 0;
    if (img.getWidth() > width || img.getHeight() > height) {
      if (width != 0) {
        resW = img.getWidth() / width;
      } else {
        resW = 1;
      }
      if (height != 0) {
        resH = img.getHeight() / height;
      } else {
        resH = 1;
      }
      if (resW >= resH) {
        maxRes = resW;
      }
      if (resH > resW) {
        maxRes = resH;
      }
      newWidth = img.getWidth() / maxRes;
      newHeight = img.getHeight() / maxRes;
    } else {
      newWidth = img.getWidth();
      newHeight = img.getHeight();
    }
    System.out.println(
        " высота ограничения GServ " + height + "  ширина ограничения GServ " + width);
    System.out.println(" новая высота " + newHeight + "   новая ширина " + newWidth);
  }


  // изменение размера, чб, вывод..
  @Override
  public String convert(String url)
      throws IOException, BadImageSizeException {

    BufferedImage img = ImageIO.read(new URL(url));
    maxiRatio(img);
    resImage(img);
    char[][] graph = new char[newHeight][newWidth];
    Image scaledImage = img.getScaledInstance(newWidth, newHeight,
        BufferedImage.SCALE_SMOOTH);
    BufferedImage bwImg = new BufferedImage(newWidth, newHeight,
        BufferedImage.TYPE_BYTE_GRAY);
    Graphics2D graphics = bwImg.createGraphics();
    graphics.drawImage(scaledImage, 0, 0, null);
    var bwRaster = bwImg.getRaster();

    for (int h = 0; h < newHeight; h++) {
      for (int w = 0; w < newWidth; w++) {
        int color = bwRaster.getPixel(w, h, new int[3])[0];   //???????
        char pix = schema.convert(color);
        graph[h][w] = pix;
      }
    }
    StringBuilder strBild = new StringBuilder();
    printText(graph, strBild);
    return strBild.toString();
  }
}


//    java.lang.ArrayIndexOutOfBoundsException: Coordinate out of bounds!
//    at java.desktop/java.awt.image.ComponentSampleModel.getPixel(ComponentSampleModel.java:733)