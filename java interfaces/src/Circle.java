public class Circle extends Shape implements Resizable {
    private double radius;

    public Circle(String name, double radius) {
        super(name);
        this.radius = radius;
    }

    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }

    @Override
    public void resize(double scaleFactor) {
        radius *= scaleFactor;
    }

    @Override
    public double getResizedArea() {
        return getArea();
    }

    @Override
    public double getResizedPerimeter() {
        return getPerimeter();
    }
}



