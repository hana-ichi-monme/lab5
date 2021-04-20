package lab5.types;

public class Coordinates {
    private double x;
    private long y; //Максимальное значение поля: 441

    public Coordinates(double x, long y) {
        this.x = x;
        this.y = y;
    }
    public Coordinates() {
        super();
    }

    public double getX() {
        return x;
    }

    public long getY() {
        return y;
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
