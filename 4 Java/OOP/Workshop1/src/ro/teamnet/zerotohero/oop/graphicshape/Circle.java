package ro.teamnet.zerotohero.oop.graphicshape;

import static java.lang.Math.PI;

public class Circle extends Shape {

    int xPos;
    int yPos;
    int radius;

    public Circle(int xPos) {
        this.xPos = xPos;
    }

    public Circle(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public Circle(int xPos, int yPos, int radius) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.radius = radius;
    }

    public Circle() {
        this.xPos = 3;
        this.yPos = 4;
        this.radius = 7;
    }

    @Override
    public double area() {
        return radius * radius * PI;
    }

    @Override
    public String toString() {
        return "center=(" + xPos + "," + yPos + ") and radius=" + radius;

    }

    public void fillColour() {
        System.out.println(super.color);
    }

    public void fillColour(int x) {
        System.out.println("The circle color is now 2");
    }

    public void fillColour(float x) {
        super.setSaturation(x);
    }
}
