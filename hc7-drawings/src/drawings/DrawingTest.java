package drawings;


import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

 record Point(int x, int y) {}

abstract class Shape {
	
	abstract String toSVG(); // overridden method; is overridden by Circle.toSVG() and Polygon.toSVG()
}

class Circle extends Shape {
	Point centre;
	int radius;
	
	Circle(Point centre, int radius) {
		this.centre = centre;
		this.radius = radius;
	}
	
	String toSVG() { // overriding method; overrides Shape.toSVG()
		return "<circle x='" + centre.x() + "' y='" + centre.y() + "' r='" + this.radius + "' />";
	}
}

class Polygon extends Shape {
	Point[] vertices;
	
	Polygon(Point[] vertices) {
		this.vertices = vertices.clone();
	}
	
	String toSVG() {
		String result = "<polygon points=";
		for (Point point : vertices)
			result += point.x() + " " + point.y() + " ";
		result += "' />";
		return result;
	}
			
}

class Drawing {
	
	Shape[] shapes;
	
	String toSVG() {
		String result = "<svg xmlns='http://w3c.org/2000/svg'>";
		for (Shape shape : shapes) {
			result += shape.toSVG(); // dynamisch gebonden methode-opgroep
		}
		return result + "</svg>";
	}

}

public class DrawingTest {
	
	@Test
	void testDrawing() {
		Drawing myDrawing = new Drawing();
		myDrawing.shapes = new Shape[] {
				new Circle(new Point(5, 5), 10),
				new Polygon(new Point[] {new Point(0,10), new Point(10,0), new Point(10,10)})
		};
		assertEquals("<svg xmlns='http://w3c.org/2000/svg'><circle x='5' y='5' r='10' /><polygon points=0 10 10 0 10 10 ' /></svg>", myDrawing.toSVG());
		
		Object x = Integer.valueOf(10);
		if (x instanceof Integer) {
			int y = (int)x;
		}
		
		assertEquals("Point[x=3, y=7]", "" + new Point(3, 7));
		assertEquals(new Point(3, 7), new Point(3, 7));
	}
}