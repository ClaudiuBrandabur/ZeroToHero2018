package ro.teamnet.zerotohero.oop;

import ro.teamnet.zerotohero.canvas.Canvas;
import ro.teamnet.zerotohero.oop.graphicshape.*;

import java.lang.Exception;

public class RunApp {
    public static void main(String[] args) throws Exception1 {
        Circles c = new Circles();
        System.out.println(c.getAreaPub());
        c.getAreaDef();

        Canvas canvas = new Canvas();
//        canvas.paint();

        Shape s = new Circle(10);
        System.out.println(s.area());

        ShapeBehaviour sq = new Square(10);
        System.out.println(sq.area());

        Object p1 = new Point(10, 20);
        Object p2 = new Point(50, 100);
        Object p3 = new Point(10, 20);

        System.out.println("p1 equals p2 is " + p1.equals(p2));
        System.out.println("p1 equals p3 is " + p1.equals(p3));
//        int i = 1;
//        if (i == 1) {
//            throw new Exception1("My exception");
//        }


    }
}

