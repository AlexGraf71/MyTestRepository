package ru.stqa.pft.sandbox;

public class StartLengthPoint {

  public static void main(String[] args) {

    Point p1 = new Point(56, 76);
    Point p2 = new Point(65, 85);

    System.out.println("Расстояние между точками " + distance(p1, p2));
  }

  public static double distance(Point p1, Point p2) {

    return Math.sqrt((Math.pow((p2.x - p1.x), 2) + Math.pow((p2.y - p1.y), 2)));
  }

}
