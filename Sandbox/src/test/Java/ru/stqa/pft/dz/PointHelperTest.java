package ru.stqa.pft.dz;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointHelperTest {

  @Test
  public void testDistance(){
    PointHelper pointHelper = new PointHelper(0,0,0,2);
    Assert.assertEquals(pointHelper.distance(),2);
  }
}
