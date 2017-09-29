package io.github.coalangsoft.intern.fxparts.input;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;

public interface StandardKeyCombinations {
	
	KeyCombination ZOOM_PLUS =
			new KeyCodeCombination(KeyCode.PLUS, KeyCombination.CONTROL_DOWN);
	KeyCombination ZOOM_MINUS =
			new KeyCodeCombination(KeyCode.MINUS, KeyCombination.CONTROL_DOWN);
	KeyCombination FIND = 
			new KeyCodeCombination(KeyCode.F, KeyCombination.CONTROL_DOWN);
	
}
