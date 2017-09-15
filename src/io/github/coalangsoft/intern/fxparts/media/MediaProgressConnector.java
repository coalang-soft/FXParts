package io.github.coalangsoft.intern.fxparts.media;

import javafx.beans.property.Property;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.util.Duration;

public class MediaProgressConnector implements ChangeListener<Duration> {

	public MediaProgressConnector(Property<Number> prop){
		this.prop = prop;
	}
	
	private final Property<Number> prop;
	
	@Override
	public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
		prop.setValue(newValue.toSeconds());
	}
	
}
