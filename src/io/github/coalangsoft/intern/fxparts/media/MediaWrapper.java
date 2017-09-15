package io.github.coalangsoft.intern.fxparts.media;

import javafx.scene.media.Media;

public class MediaWrapper {
	
	public final Media raw;

	public MediaWrapper(Media m){
		this.raw = m;
	}
	
	public String toString(){
		String title = (String) raw.getMetadata().get("title");
		if(title != null){
			String artist = (String) raw.getMetadata().get("artist");
			if(artist != null){
				return title + " - " + artist;
			}
			return title;
		}
		return raw.getSource();
	}
	
}
