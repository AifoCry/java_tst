package ru.stqa.pft.sandbox;

public class Point {
    public Double x;
    public Double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public  double distance(Point p2)
    {
        return Math.sqrt(((p2.x - this.x) * (p2.x - this.x)) + ((p2.y - this.y) * (p2.y - this.y)));
    }
}