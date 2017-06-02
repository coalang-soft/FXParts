package io.github.coalangsoft.intern.fxparts;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class EditableListView<T> extends ListView<T> {
	
	{
		setCellFactory(new Callback<ListView<T>, ListCell<T>>() {
			@Override
			public ListCell<T> call(final ListView<T> lv) {
				return new EditableListCell<T>();
			}
		});
	}
	
}
