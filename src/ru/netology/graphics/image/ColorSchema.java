package ru.netology.graphics.image;

public class ColorSchema implements TextColorSchema {

  // 256/8=32  n=color/32

  public static char[] Symbol = {'#', '$', '@', '%', '*', '+', '-', '\''};

  @Override
  public char convert(int color) {
    int n = color / 32;
    return Symbol[n];
  }


}
