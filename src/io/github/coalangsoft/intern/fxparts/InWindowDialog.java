package io.github.coalangsoft.intern.fxparts;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Dialog;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Callback;

public class InWindowDialog<T> {

	private ObjectProperty<Callback<T,Object>> onResult;
	
	private Dialog<T> dialog;
	private Node mainGraphNode;

	public InWindowDialog(Dialog<T> d, Node mainGraphNode){
		this.dialog = d;
		this.mainGraphNode = mainGraphNode;
		this.onResult = new SimpleObjectProperty<Callback<T,Object>>(new Callback<T, Object>() {

			public Object call(T arg0) {
				// TODO Auto-generated method stub
				return null;
			}
			
		});
	}
	
	public void show(){
		final Scene s = mainGraphNode.getScene();
		
		final Parent root;
		
		final StackPane p = new StackPane();
		p.getChildren().add(root = s.getRoot());
		
		Rectangle bg = new Rectangle();
		bg.widthProperty().bind(p.widthProperty());
		bg.heightProperty().bind(p.heightProperty());
		bg.setFill(new Color(0.5, 0.5, 0.5, 0.5));
		p.getChildren().add(bg);
		
		p.getChildren().add(new Group(dialog.getDialogPane()));
		s.setRoot(p);
		
		final Service<T> service = new Service<T>() {

			@Override
			protected Task<T> createTask() {
				return new Task<T>() {
					
					@Override
					protected T call() throws Exception {
						while(dialog.getResult() == null);
						return dialog.getResult();
					}
				};
			}
		};
		
		service.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
			
			public void handle(WorkerStateEvent e) {
				p.getChildren().clear();
				s.setRoot(root);
				onResult.get().call(service.getValue());
			}
			
		});
		
		service.start();
	}
	
	public void setOnResult(Callback<T,Object> c){
		onResult.setValue(c);
	}
	
	public Callback<T,Object> getOnResult(){
		return onResult.getValue();
	}
	
	public ObjectProperty<Callback<T,Object>> onResultProperty(){
		return onResult;
	}
	
}
