package ru.stqa.pft.dz;

public class Start {
  public static void main(String[] args){
    Point point = new Point(4.5,4.5);
    Point p2 = new Point(0.5,0.5);

    System.out.println("Расстояние между точками p1 и p2 = " + point.distance(p2));
  }
}
