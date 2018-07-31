package ro.teamnet.zerotohero.oop.graphicshape;

import static java.lang.Math.PI;

public class Circle extends Shape{
    private int xPos;
    private int yPos;
    private int radius;

    public Circle(){
        xPos = 1;
        yPos = 1;
        radius = 30;
    }

    public Circle(int xPos){

        this.xPos = xPos;
    }

    public Circle(int xPos, int yPos){
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public Circle(int xPos, int yPos, int radius){
        this.xPos = xPos;
        this.yPos = yPos;
        this.radius = radius;
    }

    public double area() {
        return radius*radius*PI;
    }

    public String toString(){
        return "center = (" + xPos + "," + yPos + ") and radius = " + radius;
    }

    public void fillColor(){
        System.out.println(super.color);
    }

    public void fillColor(int x){
        super.setColor(x);
        System.out.println("the circle message is now " + super.color);
    }

    public void fillColor(float x){
        super.setSaturation(x);
    }
}
