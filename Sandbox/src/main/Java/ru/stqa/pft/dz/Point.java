package ru.stqa.pft.dz;

public class Point {

  public double x;
  public double y;

  public double distance(Point p1, Point p2){
    return Math.sqrt( ((p2.x - p1.x)*2) + ((p2.y - p1.y)*2) );
  }
}
