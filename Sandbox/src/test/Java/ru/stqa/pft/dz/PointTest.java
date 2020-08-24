package ru.stqa.pft.dz;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTest {

  @Test
  public void testDistance(){
    Point point = new Point(5.5,5.5);
    Point p2 = new Point(1.5,1.5);

    Assert.assertEquals(point.distance(p2),4);
  }
}
