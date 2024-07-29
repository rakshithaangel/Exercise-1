import java.util.ArrayList;
import java.util.List;


interface Shape {
    void draw();
}


class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("Drawing a Circle");
    }
}


class Rectangle implements Shape {
    @Override
    public void draw() {
        System.out.println("Drawing a Rectangle");
    }
}

class CompositeShape implements Shape {
    private List<Shape> shapes = new ArrayList<>();

    public void addShape(Shape shape) {
        shapes.add(shape);
    }

    public void removeShape(Shape shape) {
        shapes.remove(shape);
    }

    @Override
    public void draw() {
        System.out.println("Drawing Composite Shape:");
        for (Shape shape : shapes) {
            shape.draw();
        }
    }
}


public class CompositePatternDemo {
    public static void main(String[] args) {
        Shape circle = new Circle();
        Shape rectangle = new Rectangle();

        CompositeShape compositeShape = new CompositeShape();
        compositeShape.addShape(circle);
        compositeShape.addShape(rectangle);

        compositeShape.draw();
    }
}