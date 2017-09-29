package io.github.coalangsoft.intern.fxparts.input;

import javafx.beans.property.Property;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.input.ZoomEvent;
import javafx.scene.text.Font;
import javafx.util.Callback;

public class Zoom {
	
	public static KeyCombinationHandler key(Callback<Double, Void> action){
		KeyCombinationHandler h = new KeyCombinationHandler(StandardKeyCombinations.ZOOM_PLUS,
				new Callback<KeyEvent, Void>() {
					@Override
					public Void call(KeyEvent param) {
						return action.call(1d);
					}
				});
		h.addAction(StandardKeyCombinations.ZOOM_MINUS, new Callback<KeyEvent, Void>() {
			@Override
			public Void call(KeyEvent param) {
				return action.call(-1d);
			}
		});
		return h;
	}
	
	public static EventHandler<ScrollEvent> scroll(Callback<Double, Void> action){
		return new EventHandler<ScrollEvent>() {
			@Override
			public void handle(ScrollEvent event) {
				if(event.isControlDown()){
					action.call(event.getDeltaY() / 10);
				}
			}
		};
	}
	
	public static EventHandler<ZoomEvent> gesture(Callback<Double, Void> action){
		return new EventHandler<ZoomEvent>() {
			@Override
			public void handle(ZoomEvent event) {
				action.call(event.getZoomFactor());
			}
		};
	}
	
	public static void forTextInput(TextInputControl c){
		Callback<Double,Void> keyboardAndMouse = (d) -> {
			c.setFont(Font.font(c.getFont().getFamily(), c.getFont().getSize() + d));
			return null;
		};
		c.setOnKeyPressed(key(keyboardAndMouse));
		c.setOnScroll(scroll(keyboardAndMouse));
		c.setOnZoom(gesture((d) -> {
			c.setFont(Font.font(c.getFont().getFamily(), c.getFont().getSize() * d));
			return null;
		}));
	}
	
	public static void forFontProperty(Node c, Property<Font> f){
		Callback<Double,Void> keyboardAndMouse = (d) -> {
			f.setValue(Font.font(f.getValue().getFamily(), f.getValue().getSize() + d));
			return null;
		};
		c.setOnKeyPressed(key(keyboardAndMouse));
		c.setOnScroll(scroll(keyboardAndMouse));
		c.setOnZoom(gesture((d) -> {
			f.setValue(Font.font(f.getValue().getFamily(), f.getValue().getSize() * d));
			return null;
		}));
	}
	
	public static void forGeneralNode(Node c){
		Callback<Double,Void> keyboardAndMouse = (d) -> {
			c.setScaleX(c.getScaleX() + d / 4);
			c.setScaleY(c.getScaleY() + d / 4);
			c.setScaleZ(c.getScaleZ() + d / 4);
			return null;
		};
		c.setOnKeyPressed(key(keyboardAndMouse));
		c.setOnScroll(scroll(keyboardAndMouse));
		c.setOnZoom(gesture((d) -> {
			c.setScaleX(c.getScaleX() * d);
			c.setScaleY(c.getScaleY() * d);
			c.setScaleZ(c.getScaleZ() * d);
			return null;
		}));
	}
	
}
