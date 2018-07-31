package ro.teamnet.zerotohero.oop.graphicshape;

public class Point {

    int xPos;
    int yPos;

    public Point(int xPos, int yPos){
        this.xPos = xPos;
        this.yPos = yPos;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null)
            return false;
        if(obj instanceof Point){
            Point point = (Point)obj;
            if((xPos == point.xPos) && (yPos == point.yPos))
                return true;
        }
        return false;
    }
}
