package io.github.coalangsoft.intern.fxparts;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.util.Callback;

public class KeyCombinationHandler implements EventHandler<KeyEvent> {

	private KeyCombination combi;
	private Callback<KeyEvent, Void> action;
	
	public KeyCombinationHandler(KeyCombination c, Callback<KeyEvent, Void> action){
		this.combi = c;
		this.action = action;
	}
	
	public static KeyCombinationHandler focus(final Node n, KeyCombination c){
		return new KeyCombinationHandler(c, new Callback<KeyEvent, Void>() {
			@Override
			public Void call(KeyEvent param) {
				n.requestFocus();
				return null;
			}
		});
	}
	
	@Override
	public void handle(KeyEvent arg0) {
		if(combi.match(arg0)){
			action.call(arg0);
		}
	}

}
