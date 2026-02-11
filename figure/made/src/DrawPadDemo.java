import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

// Abstract Shape class
abstract class Shape {
    protected int x;
    protected int y;
    protected Color color;
    protected String id;
    protected static int nextId = 1;

    public Shape(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.id = "Shape_" + nextId++;
    }

    public abstract void draw(Graphics g);

    public void setColor(Color color) {
        this.color = color;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

// Circle implementation
class Circle extends Shape {
    private int radius;

    public Circle(int x, int y, int radius, Color color) {
        super(x, y, color);
        this.radius = radius;
        this.id = "Circle_" + (nextId - 1);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(x - radius, y - radius, 2 * radius, 2 * radius);

        // Draw the identifier
        g.setColor(Color.BLACK);
        g.drawString(id, x - radius, y - radius - 5);
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
}

// Rectangle implementation
class Rectangle extends Shape {
    private int width;
    private int height;

    public Rectangle(int x, int y, int width, int height, Color color) {
        super(x, y, color);
        this.width = width;
        this.height = height;
        this.id = "Rect_" + (nextId - 1);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, width, height);

        // Draw the identifier
        g.setColor(Color.BLACK);
        g.drawString(id, x, y - 5);
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}

// Triangle implementation
class Triangle extends Shape {
    private int[] xPoints;
    private int[] yPoints;

    public Triangle(int x1, int y1, int x2, int y2, int x3, int y3, Color color) {
        super((x1 + x2 + x3) / 3, (y1 + y2 + y3) / 3, color); // Center point
        this.xPoints = new int[]{x1, x2, x3};
        this.yPoints = new int[]{y1, y2, y3};
        this.id = "Triangle_" + (nextId - 1);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillPolygon(xPoints, yPoints, 3);

        // Draw the identifier
        g.setColor(Color.BLACK);
        g.drawString(id, xPoints[0], yPoints[0] - 5);
    }

    public void setPosition(int x, int y) {
        int dx = x - this.x;
        int dy = y - this.y;

        for (int i = 0; i < 3; i++) {
            xPoints[i] += dx;
            yPoints[i] += dy;
        }

        super.setPosition(x, y);
    }
}

// Line implementation
class Line extends Shape {
    private int x2;
    private int y2;

    public Line(int x1, int y1, int x2, int y2, Color color) {
        super(x1, y1, color);
        this.x2 = x2;
        this.y2 = y2;
        this.id = "Line_" + (nextId - 1);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.drawLine(x, y, x2, y2);

        // Draw the identifier
        g.setColor(Color.BLACK);
        g.drawString(id, x, y - 5);
    }

    public void setPosition(int x, int y) {
        int dx = x - this.x;
        int dy = y - this.y;

        x2 += dx;
        y2 += dy;

        super.setPosition(x, y);
    }

    public int getX2() {
        return x2;
    }

    public int getY2() {
        return y2;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }
}

// Text implementation
class Text extends Shape {
    private String text;
    private Font font;

    public Text(int x, int y, String text, Font font, Color color) {
        super(x, y, color);
        this.text = text;
        this.font = font;
        this.id = "Text_" + (nextId - 1);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.setFont(font);
        g.drawString(text, x, y);

        // Draw the identifier (smaller and above the text)
        Font smallFont = new Font(font.getName(), Font.PLAIN, 10);
        g.setFont(smallFont);
        g.setColor(Color.BLACK);
        g.drawString(id, x, y - 15);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }
}

// DrawPadManager class
class DrawPadManager {
    private List<Shape> shapes;

    public DrawPadManager() {
        shapes = new ArrayList<>();
    }

    public void addShape(Shape shape) {
        shapes.add(shape);
    }

    public void removeShape(Shape shape) {
        shapes.remove(shape);
    }

    public List<Shape> getAllShapes() {
        return shapes;
    }

    public void drawAll(Graphics g) {
        for (Shape shape : shapes) {
            shape.draw(g);
        }
    }

    public Shape findShapeById(String id) {
        for (Shape shape : shapes) {
            if (shape.getId().equals(id)) {
                return shape;
            }
        }
        return null;
    }

    public Shape findShapeAt(int x, int y) {
        // This is a simple implementation that could be improved
        // Currently just returns the last shape that might be at that position
        for (int i = shapes.size() - 1; i >= 0; i--) {
            Shape shape = shapes.get(i);

            // Simple hit testing - can be improved for better accuracy
            if (shape instanceof Circle) {
                Circle circle = (Circle) shape;
                int radius = circle.getRadius();
                double distance = Math.sqrt(Math.pow(x - circle.getX(), 2) + Math.pow(y - circle.getY(), 2));
                if (distance <= radius) {
                    return shape;
                }
            } else if (shape instanceof Rectangle) {
                Rectangle rect = (Rectangle) shape;
                if (x >= rect.getX() && x <= rect.getX() + rect.getWidth() &&
                        y >= rect.getY() && y <= rect.getY() + rect.getHeight()) {
                    return shape;
                }
            }
            // For other shapes, more complex hit testing would be required
        }
        return null;
    }

    public void moveTo(Shape shape, int x, int y) {
        if (shape != null) {
            shape.setPosition(x, y);
        }
    }

    public void clear() {
        shapes.clear();
    }
}

// DrawPad panel class
class DrawPad extends JPanel {
    private DrawPadManager manager;
    private Shape selectedShape;
    private int dragOffsetX, dragOffsetY;

    public DrawPad() {
        manager = new DrawPadManager();

        // Add mouse listeners for interactivity
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                selectedShape = manager.findShapeAt(e.getX(), e.getY());
                if (selectedShape != null) {
                    dragOffsetX = e.getX() - selectedShape.getX();
                    dragOffsetY = e.getY() - selectedShape.getY();
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                selectedShape = null;
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (selectedShape != null) {
                    manager.moveTo(selectedShape, e.getX() - dragOffsetX, e.getY() - dragOffsetY);
                    repaint();
                }
            }
        });
    }

    public DrawPadManager getManager() {
        return manager;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        manager.drawAll(g);
    }
}

// Main application class
public class DrawPadDemo extends JFrame {

    public DrawPadDemo() {
        setTitle("DrawPad Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        DrawPad drawPad = new DrawPad();
        add(drawPad, BorderLayout.CENTER);

        // Add a toolbar with buttons for shapes
        JToolBar toolbar = new JToolBar();

        JButton btnCircle = new JButton("Circle");
        btnCircle.addActionListener(e -> {
            Color color = JColorChooser.showDialog(this, "Choose Circle Color", Color.RED);
            if (color != null) {
                drawPad.getManager().addShape(new Circle(200, 200, 50, color));
                drawPad.repaint();
            }
        });
        toolbar.add(btnCircle);

        JButton btnRectangle = new JButton("Rectangle");
        btnRectangle.addActionListener(e -> {
            Color color = JColorChooser.showDialog(this, "Choose Rectangle Color", Color.BLUE);
            if (color != null) {
                drawPad.getManager().addShape(new Rectangle(300, 200, 100, 80, color));
                drawPad.repaint();
            }
        });
        toolbar.add(btnRectangle);

        JButton btnTriangle = new JButton("Triangle");
        btnTriangle.addActionListener(e -> {
            Color color = JColorChooser.showDialog(this, "Choose Triangle Color", Color.GREEN);
            if (color != null) {
                drawPad.getManager().addShape(new Triangle(400, 200, 450, 300, 350, 300, color));
                drawPad.repaint();
            }
        });
        toolbar.add(btnTriangle);

        JButton btnLine = new JButton("Line");
        btnLine.addActionListener(e -> {
            Color color = JColorChooser.showDialog(this, "Choose Line Color", Color.BLACK);
            if (color != null) {
                drawPad.getManager().addShape(new Line(500, 200, 600, 300, color));
                drawPad.repaint();
            }
        });
        toolbar.add(btnLine);

        JButton btnText = new JButton("Text");
        btnText.addActionListener(e -> {
            String text = JOptionPane.showInputDialog(this, "Enter text:");
            if (text != null && !text.isEmpty()) {
                Color color = JColorChooser.showDialog(this, "Choose Text Color", Color.BLACK);
                if (color != null) {
                    Font font = new Font("Arial", Font.PLAIN, 18);
                    drawPad.getManager().addShape(new Text(100, 400, text, font, color));
                    drawPad.repaint();
                }
            }
        });
        toolbar.add(btnText);

        JButton btnClear = new JButton("Clear");
        btnClear.addActionListener(e -> {
            drawPad.getManager().clear();
            drawPad.repaint();
        });
        toolbar.add(btnClear);

        add(toolbar, BorderLayout.NORTH);

        // Initialize with some sample shapes
        createSampleShapes(drawPad.getManager());

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void createSampleShapes(DrawPadManager manager) {
        // Create sample shapes as shown in the example
        manager.addShape(new Circle(150, 150, 50, Color.RED));
        manager.addShape(new Rectangle(250, 100, 120, 80, Color.BLUE));
        manager.addShape(new Triangle(400, 100, 470, 200, 330, 200, Color.GREEN));
        manager.addShape(new Line(500, 150, 600, 200, Color.BLACK));
        manager.addShape(new Text(100, 300, "DrawPad Demo", new Font("Arial", Font.BOLD, 24), Color.MAGENTA));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new DrawPadDemo();
        });
    }
}