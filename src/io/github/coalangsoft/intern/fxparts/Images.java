package io.github.coalangsoft.intern.fxparts;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.paint.Color;

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
	
}
