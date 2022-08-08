package ru.netology.graphics;


import ru.netology.graphics.image.Converter;
import ru.netology.graphics.image.TextGraphicsConverter;
import ru.netology.graphics.server.GServer;



public class Main {
    public static void main(String[] args) throws Exception {

        TextGraphicsConverter converter = new Converter(); // Создайте тут объект вашего класса конвертера



        GServer server = new GServer(converter); // Создаём объект сервера

        converter.setMaxHeight(1);
        converter.setMaxWidth(1);
        converter.setMaxRatio(1);

        server.start(); // Запускаем



        String url = "https://i.ibb.co/6DYM05G/edu0.jpg";
      //String url = "https://raw.githubusercontent.com/netology-code/java-diplom/main/pics/simple-test.png";
      // String url = "http://file.siam2web.com/template/images/header/header143.jpg";//широкая. 2 ошибка, 3 норм
      //String url = "https://raw.githubusercontent.com/netology-code/java-diplom/main/pics/simple-test.png";//  мелкая


      String imgTxt = converter.convert(url);

      System.out.println(imgTxt);
    }

}



