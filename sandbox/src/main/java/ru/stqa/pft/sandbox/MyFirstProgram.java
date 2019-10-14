package ru.stqa.pft.sandbox;

 public class MyFirstProgram {

    public static void main(String[] args) {
        Point p1 = new Point();
        Point p2 = new Point();
        p1.x = 10.0;
        p1.y = 20.0;
        p2.x = 30.0;
        p2.y = 40.0;
        System.out.println("Растояние между точек P1 и P2 = " + distance(p1, p2));
 }

    public static double distance(Point p1, Point p2)
    {
        return Math.sqrt(((p2.y - p1.y) * (p2.y - p1.y)) + ((p2.x - p1.x) * (p2.x - p1.x)));
    }
}
