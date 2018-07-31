package ro.teamnet.zerotohero.oop.graphicshape.runapp;

import ro.teamnet.zerotohero.oop.graphicshape.*;
import ro.teamnet.zerotohero.oop.graphicshape.canvas.Canvas;

public class RunApp {

    public static void main(String[] args){
        Circles c = new Circles();
        System.out.println(c.getAreaPub());
        c.getAreaDef();

        Canvas canvas = new Canvas();
        canvas.paint();

        Shape shape = new Circle(10);
        System.out.println(shape.area());
        System.out.println(shape.toString());

        ShapeBehaviour sb = new Square(10);
        System.out.println(sb.area());

        Object p1 = new Point(10, 20);
        Object p2 = new Point(50, 100);
        Object p3 = new Point(10, 20);
        System.out.println("p1 equals p2 is " + p1.equals(p2));
        System.out.println("p1 equals p3 is " + p1.equals(p3));


        ImmutableClass i1 = new ImmutableClass(9,10);
        System.out.println(i1.getX());

//        int i = 0;
//        i--;
//        if(i == -1) {
//            throw new MyException("My exception");
//        }

        int[] v = new int[10];
        int z;

        try{
           // System.out.println(v[11]);
            z = v[0]/0;
        }catch (ArrayIndexOutOfBoundsException e){
            throw new ArrayIndexOutOfBoundsException();
        }catch (ArithmeticException g){
            throw new ArithmeticException();
        }finally {
            System.out.println("Text de afisat in consola");
        }




    }
}
