package io.github.coalangsoft.intern.fxparts;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.BorderPane;

public class EditableListCell<T> extends ListCell<T> {

	{
		itemProperty().addListener(new ChangeListener<T>() {
			@Override
			public void changed(ObservableValue<? extends T> arg0,
					T arg1, final T n) {
				if(n != null){
					BorderPane p = new BorderPane();
					
					Button b = new Button("Remove");
					b.setOnAction(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent e) {
							getListView().getItems().remove(n);
						}
					});
					
					p.setRight(b);
					p.setLeft(n instanceof Node ? (Node) n : new Label(n.toString()));
					setGraphic(p);
				}else{
					setGraphic(null);
				}
			}
		});
	}
	
}
