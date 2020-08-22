package ru.stqa.pft.dz;

public class Point {


  public static void main(String[] args) {
    PointHelper pointHelper = new PointHelper(0.5,0.5,5,5);

    System.out.println("Расстояние между точками p1 и p2 = " + pointHelper.distance());
  }



}