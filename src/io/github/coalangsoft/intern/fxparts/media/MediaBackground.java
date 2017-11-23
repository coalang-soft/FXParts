package io.github.coalangsoft.intern.fxparts.media;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.MapChangeListener;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Region;
import javafx.scene.media.Media;
import javafx.util.Callback;

public class MediaBackground {
	
	public static void applyMusic(Callback<Image,Image> imageModifier, Media m, Region r, Callback<Image, Object> afterSet){
		Image i = (Image) m.getMetadata().get("image");
		if(i != null){
			BackgroundImage bgi = new BackgroundImage(imageModifier.call(i),
					BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
			Background b = new Background(bgi);
			r.backgroundProperty().bind(new SimpleObjectProperty<>(b));
			
			afterSet.call(i);
		}else{
			r.setBackground(null);
			m.getMetadata().addListener(new MapChangeListener<String, Object>(){
				@Override
				public void onChanged(
						javafx.collections.MapChangeListener.Change<? extends String, ? extends Object> change) {
					if(change.getKey().equals("image")){
						m.getMetadata().removeListener(this);
						applyMusic(m,r,afterSet);
					}
				}
			});
		}
	}
	
	public static void applyMusic(Media m, Region r, Callback<Image,Object> afterSet){
		applyMusic((i) -> i, m,r,afterSet);
	}
	
	public static void applyMusic(Media m, Region r){
		applyMusic(m,r,(i) -> null);
	}
	
}
