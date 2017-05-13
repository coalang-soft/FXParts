package io.github.coalangsoft.intern.fxparts;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.util.Callback;

public class TabFactoryPane extends TabPane{
	
	private ObjectProperty<Callback<Tab, Node>> factory;
	private Tab plusTab;
	private ChangeListener<Boolean> plusTabListener;

	{
		factory = new SimpleObjectProperty<Callback<Tab,Node>>();
		parentProperty().addListener(new ChangeListener<Parent>() {

			public void changed(ObservableValue<? extends Parent> v,
					Parent o, Parent n) {
				init();
				v.removeListener(this);
			}
			
		});
	}
	
	public void setTabFactory(Callback<Tab,Node> f){
		factory.setValue(f);
	}
	
	public Callback<Tab,Node> getTabFactory(){
		return factory.getValue();
	}
	
	public ObjectProperty<Callback<Tab,Node>> tabFactoryProperty(){
		return factory;
	}
	
	private void init(){
		plusTab = new Tab();
		addNewTab();
	}

	private Tab createPlusTab() {
		final Tab t = plusTab = new Tab("+");
		t.selectedProperty().addListener(plusTabListener = new ChangeListener<Boolean>() {

			public void changed(ObservableValue<? extends Boolean> v,
					Boolean o, Boolean n) {
				if(n){
					addNewTab();
				}
			}
			
		});
		return t;
	}

	public Tab addNewTab() {		
		if(plusTabListener != null){
			plusTab.setContent(getTabFactory().call(plusTab));
			plusTab.selectedProperty().removeListener(plusTabListener);
		}
		
		Tab t = plusTab;
		getTabs().add(createPlusTab());
		return t;
	}
	
}
