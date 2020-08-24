package ru.stqa.pft.dz;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTest {

  @Test
  public void testDistance(){
    Point point = new Point();

    Point p1 = new Point();
    p1.x = 1.5;
    p1.y = 1.5;

    Point p2 = new Point();
    p2.x = 5.5;
    p2.y = 5.5;

    Assert.assertEquals(point.distance(p1,p2),4);
  }
}
