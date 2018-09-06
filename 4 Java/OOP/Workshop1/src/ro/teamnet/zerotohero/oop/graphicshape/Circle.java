package ro.teamnet.zerotohero.oop.graphicshape;

import static java.lang.Math.PI;

public class Circle extends Shape{

    int xPos;
    int yPos;
    int radius;

    public Circle() {
        this.xPos = 3;
        this.yPos = 4;
        this.radius = 7;
    }

    @Override
    public double area() {
        return radius*radius*PI;
    }
}
