package test.fxparts;

import io.github.coalangsoft.intern.fxparts.EditableListView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class EditListTest extends Application {

	@Override
	public void start(Stage s) throws Exception {
		EditableListView<String> list = new EditableListView<>();
		list.getItems().addAll("a","b","c","d","e");
		
		s.setScene(new Scene(list));
		s.show();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}

}
