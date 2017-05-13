package io.github.coalangsoft.intern.fxparts;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ProgressBar;

public class ProgressDialog extends Alert{

	private ProgressBar progressBar;
	
	private DoubleProperty max;
	private DoubleProperty progress;

	public ProgressDialog(AlertType t) {
		super(t);
		
		progressBar = new ProgressBar();
		max = new SimpleDoubleProperty(1);
		progress = new SimpleDoubleProperty(0);
		
		getButtonTypes().clear();
		setTitle("Working...");
		getDialogPane().setExpandableContent(progressBar);
		progressBar.progressProperty().addListener(new ChangeListener<Number>() {

			public void changed(ObservableValue<? extends Number> v,
					Number o, Number n) {
				if(progressBar.getProgress() >= 1){
					setResult(ButtonType.FINISH);
					hide();
				}
			}
			
		});
		progressBar.progressProperty().bind(progress.divide(max));
	}
	
	public void setMax(double m){
		max.set(m);
	}
	public double getMax(){
		return max.get();
	}
	public DoubleProperty maxProperty(){
		return max;
	}
	
	public void setProgress(double m){
		progress.set(m);
	}
	public double getProgress(){
		return progress.get();
	}
	public DoubleProperty progressProperty(){
		return progress;
	}
	
	public ProgressBar getProgressBar(){
		return progressBar;
	}
	
	public void update(double max, double current, String state){
		setMax(max);
		setProgress(current);
		setContentText(state);
	}
	
}
