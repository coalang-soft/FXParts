package test.fxparts;

import io.github.coalangsoft.intern.fxparts.EditableListView;
import io.github.coalangsoft.intern.fxparts.StreamLineCopyService;
import io.github.coalangsoft.intern.fxparts.StringPropertyOutputStream;
import javafx.application.Application;
import javafx.beans.property.StringProperty;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.net.URL;

public class EditListTest extends Application {

	@Override
	public void start(Stage s) throws Exception {
		URL u = new URL("https://mrbjarnelp.jimdo.com");

		TextArea a = new TextArea();

		new StreamLineCopyService(u.openStream(), new StringPropertyOutputStream(a.textProperty())).start();

		s.setScene(new Scene(a));
		s.show();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}

}
