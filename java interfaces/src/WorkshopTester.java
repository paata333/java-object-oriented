public class WorkshopTester {
    public static void main(String[] args) {
        // Create an array of Shape objects
        Shape[] shapes = new Shape[3];
        shapes[0] = new Rectangle("Rectangle1", 4, 5);
        shapes[1] = new Circle("Circle1", 3);
        shapes[2] = new Rectangle("Rectangle2", 6, 2);

        // Display all shapes
        for (Shape shape : shapes) {
            shape.display();
            System.out.println();
        }

        // Resize rectangles and print resized values
        System.out.println("Resizing rectangles by a factor of 2...\n");

        for (Shape shape : shapes) {
            if (shape instanceof Resizable) {
                Resizable resizable = (Resizable) shape;
                resizable.resize(2.0);
                System.out.println("Shape: " + ((Shape) resizable).name);
                System.out.println("Resized Area: " + resizable.getResizedArea());
                System.out.println("Resized Perimeter: " + resizable.getResizedPerimeter());
                System.out.println();
            }
        }
    }
}


