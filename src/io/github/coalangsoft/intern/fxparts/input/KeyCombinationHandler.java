package io.github.coalangsoft.intern.fxparts.input;

import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.util.Callback;

public class KeyCombinationHandler implements EventHandler<KeyEvent> {

	private ArrayList<KeyCombination> combi;
	private ArrayList<Callback<KeyEvent, Void>> action;
	
	public KeyCombinationHandler(KeyCombination c, Callback<KeyEvent, Void> action){
		this.combi = new ArrayList<>();
		this.combi.add(c);
		this.action = new ArrayList<>();
		this.action.add(action);
	}
	
	public void addAction(KeyCombination c, Callback<KeyEvent, Void> action){
		this.combi.add(c);
		this.action.add(action);
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
		for(int i = 0; i < combi.size(); i++){
			if(combi.get(i).match(arg0)){
				action.get(i).call(arg0);
			}
		}
	}

}
