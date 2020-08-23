package ru.stqa.pft.dz;

public class Start {
  public static void main(String[] args){
    Point p1 = new Point();
    p1.x1 = 0.5;
    p1.y1 = 0.5;

    Point p2 = new Point();
    p2.x2 = 4.5;
    p2.y2 = 4.5;

    System.out.println("Расстояние между точками p1 и p2 = " + Point.distance(p1,p2));
  }
}
