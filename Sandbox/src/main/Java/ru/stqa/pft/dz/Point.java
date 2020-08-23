package ru.stqa.pft.dz;

public class Point {

  public double x1;
  public double x2;
  public double y1;
  public double y2;




  public static double distance(Point p1, Point p2){
    return Math.sqrt( ((p2.x2 - p1.x1)*2) + ((p2.y2 - p1.y1)*2) );
  }
}
