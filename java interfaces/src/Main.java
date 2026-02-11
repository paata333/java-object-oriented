public class Main {
    public static void main(String[] args) {
        Circle circle = new Circle("Circle1", 5);
        Rectangle rectangle = new Rectangle("Rectangle1", 4, 6);

        circle.display();
        rectangle.display();

        System.out.println("\nResizing shapes by factor of 2...");

        // Remove circle resizing
        rectangle.resize(2);

        System.out.println("Resized Rectangle - Area: " + rectangle.getResizedArea() + ", Perimeter: " + rectangle.getResizedPerimeter());
    }
}
