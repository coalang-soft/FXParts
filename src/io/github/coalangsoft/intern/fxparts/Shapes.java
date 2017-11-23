package io.github.coalangsoft.intern.fxparts;

import javafx.scene.shape.Rectangle;

public class Shapes {
	
	public static Rectangle roundedRectangle(double width, double height, double radius){
		Rectangle r = new Rectangle(width, height);
		r.setArcHeight(radius);
		r.setArcWidth(radius);
		return r;
	}
	
}
