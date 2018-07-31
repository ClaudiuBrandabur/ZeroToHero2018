package ro.teamnet.zerotohero.oop.graphicshape;

public class Shape extends AbstractShape implements ShapeBehaviour{
    protected int color;
    protected float saturation;

    public double area(){
        return 3.0;
    }

    void setColor(int color){
        this.color = color;
    }

    void setSaturation(float saturation){
        this.saturation = saturation;
    }
}
