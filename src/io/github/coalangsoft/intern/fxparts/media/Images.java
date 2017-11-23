package io.github.coalangsoft.intern.fxparts.media;

import io.github.coalangsoft.intern.fxparts.Shapes;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Images {
	
	public static Color averageColor(Image i){
		PixelReader reader = i.getPixelReader();
		
		double r = 0,g = 0,b = 0, count = 0;
		for(int x = 0; x < i.getWidth(); x++){
			for(int y = 0; y < i.getHeight(); y++){
				Color c = reader.getColor(x, y);
				r += c.getRed();
				g += c.getBlue();
				b += c.getGreen();
				
				count++;
			}
		}
		
		return new Color(r / count, g / count, b / count, 1);
	}
	
	public static Image round(Image i, double radius){
		Rectangle r = Shapes.roundedRectangle(i.getWidth(), i.getHeight(), radius);
		r.setFill(new ImagePattern(i));
		return r.snapshot(null, null);
	}
	
}
