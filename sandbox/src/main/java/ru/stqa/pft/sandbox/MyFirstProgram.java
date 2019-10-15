package ru.stqa.pft.sandbox;

public class MyFirstProgram {

    public static void main(String[] args) {
        Point p1 = new Point(10.0,20.0);
        Point p2 = new Point(30.0,40.0);
        System.out.println("Растояние между точек P1 и P2 = " + p1.distance(p2));
    }

}