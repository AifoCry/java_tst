package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class MathTest {

    @Test
    public void testArea() {
        Point p3 = new Point(2,5);
        Point p4 = new Point(4,10);
        Assert.assertEquals( p3.distance(p4),  5.385164807134504);

    }
}
