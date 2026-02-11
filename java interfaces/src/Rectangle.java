public class Rectangle extends Shape implements Resizable {
    private double length;
    private double width;

    public Rectangle(String name, double length, double width) {
        super(name);
        this.length = length;
        this.width = width;
    }


    public double getArea() {
        return length * width;
    }


    public double getPerimeter() {
        return 2 * (length + width);
    }


    public void resize(double scaleFactor) {
        length *= scaleFactor;
        width *= scaleFactor;
    }


    public double getResizedArea() {
        return getArea();
    }


    public double getResizedPerimeter() {
        return getPerimeter();
    }
}
