package ru.stqa.pft.sandbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Collections {

  public static void main(String[] args){
    String [] langs = {"Java","C#", "Python", "PHP"};

    for (String l : langs){
      System.out.println("Я хочу учить " + l);
    }

    List<String> Languages = new ArrayList<String>();
    Languages.add("java");
    Languages.add("C#");
    Languages.add("Python");
    Languages.add("PHP");
    System.out.println(Languages);


    List<String> Languages2 = Arrays.asList("Java","C#", "Python", "PHP");
    System.out.println(Languages2);

    for (int i = 0 ; i < Languages2.size(); i++ ){
      System.out.println("Я хочу учить " + Languages2.get(i));
    }

  }


}
