package ro.teamnet.zerotohero.oop.graphicshape;

public final class ImmutableClass { //make the class final so it cannot be overridden

    private final int x;
    private final int y;   //make fields private

    public ImmutableClass(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }
}
