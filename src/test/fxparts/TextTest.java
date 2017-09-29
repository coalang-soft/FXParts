package test.fxparts;
import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.Window.Type;
import java.io.File;

import io.github.coalangsoft.intern.fxparts.input.Zoom;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class TextTest extends Application{
	
	@Override
	public void start(Stage primaryStage) throws Exception {		
		TextArea a = new TextArea();
		a.setFont(Font.font("Comic Sans MS", 30));
		Zoom.forTextInput(a);
		Scene s = new Scene(a);
		primaryStage.setScene(s);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
