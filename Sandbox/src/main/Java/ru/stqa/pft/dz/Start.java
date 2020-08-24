package ru.stqa.pft.dz;

public class Start {
  public static void main(String[] args){
    Point point = new Point();

    Point p1 = new Point();
    p1.x = 0.5;
    p1.y = 0.5;

    Point p2 = new Point();
    p2.x = 4.5;
    p2.y = 4.5;

    System.out.println("Расстояние между точками p1 и p2 = " + point.distance(p1,p2));
  }
}
