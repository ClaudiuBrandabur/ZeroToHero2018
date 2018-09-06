package ro.teamnet.zerotohero.oop.graphicshape;

public class Circles {

    public double getAreaPub() {

        Circle circle1 = new Circle();
        return circle1.area();
    }

    public void getAreaDef() {
        Circle circle2 = new Circle();
        circle2.fillColour();
        circle2.fillColour((int)3);
        circle2.fillColour((float)3.3);
    }

}
