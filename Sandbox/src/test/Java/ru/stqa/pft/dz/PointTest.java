package ru.stqa.pft.dz;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTest {

  @Test
  public void testDistance(){
    Point p1 = new Point();
    p1.x1 = 1.5;
    p1.y1 = 1.5;

    Point p2 = new Point();
    p2.x2 = 5.5;
    p2.y2 = 5.5;

    Assert.assertEquals(Point.distance(p1,p2),4);
  }
}
