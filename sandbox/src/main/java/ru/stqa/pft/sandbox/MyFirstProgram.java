package ru.stqa.pft.sandbox;

 public class MyFirstProgram {

    public static void main(String[] args) {
        Point p1 = new Point(10.0,20.0);
        Point p2 = new Point(30.0,40.0);
        System.out.println("Растояние между точек P1 и P2 = " + distance(p1, p2));
 }

    public static double distance(Point p1, Point p2)
    {
        return Math.sqrt(((p2.y - p1.y) * (p2.y - p1.y)) + ((p2.x - p1.x) * (p2.x - p1.x)));
    }
}
