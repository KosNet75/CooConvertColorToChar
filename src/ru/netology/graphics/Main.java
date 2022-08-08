package ru.netology.graphics;


import ru.netology.graphics.image.Converter;

import ru.netology.graphics.image.TextGraphicsConverter;
import ru.netology.graphics.server.GServer;



public class Main {
    public static void main(String[] args) throws Exception {

        TextGraphicsConverter converter = new Converter(); // Создайте тут объект вашего класса конвертера



        GServer server = new GServer(converter); // Создаём объект сервера

        converter.setMaxHeight(60);
        converter.setMaxWidth(80);
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











//      try {
//        test(new StringBuffer(""));
//        test(new StringBuilder(""));
//      } catch (java.io.IOException e) {
//        System.err.println(e.getMessage());
//      }
//    }
//  private static void test(Appendable obj) throws java.io.IOException {
//    // узнаем текущее время до теста
//    long before = System.currentTimeMillis();
//    for (int i = 0; i++ < 1e9; ) {
//      obj.append("");
//    }
//    // узнаем текущее время после теста
//    long after = System.currentTimeMillis();
//    // выводим результат
//    System.out.println(obj.getClass().getSimpleName() + ": " + (after - before) + "ms.");
//  }
//}


