package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;


public class LengthTests {

  Point p1 = new Point(56, 76);
  Point p2 = new Point(65, 85);

  double distance;
  double y = 12.72;
  double x = 12.73;
  int z = 13;

  @Test
  public void testLengthPoint() {
    distance = p1.distance(p2);
    assert distance >= y && distance < x;
  }

  @Test
  public void testLength() {
    Assert.assertEquals(Math.round(p1.distance(p2)), z);
  }

  @Test
  public void testLength2() {
    distance = p1.distance(p2);
    if (distance >= y && distance < x) System.out.println("Тест пройден, расстояние вычисляется верно");
    else System.out.println("Тест провален, расстояние вычисляется неверно");
  }

  @Test
  public void testLength3() {
    if ((Math.round(p1.distance(p2)) == z)) System.out.println("Тест пройден, расстояние вычисляется верно");
    else System.out.println("Тест провален, расстояние вычисляется неверно");
  }
}
