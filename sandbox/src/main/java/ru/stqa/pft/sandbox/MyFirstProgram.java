package ru.stqa.pft.sandbox;

 public class MyFirstProgram {

    public static void main(String[] args) {
        Point p1 = new Point();
        Point p2 = new Point();
        p1.x = 10;
        p1.y = 20;
        p2.x = 30;
        p2.y = 40;
    }

    public static double distance(Point p1, Point p2)
    {
        double dist =  Math.sqrt(((p2.y - p1.y) * (p2.y - p1.y)) + ((p2.x - p1.x) * (p2.x - p1.x)));
        return dist;
    }
}
