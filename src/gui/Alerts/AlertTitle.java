package gui.Alerts;

import javafx.scene.control.Label;

public class AlertTitle extends Label {

	public AlertTitle(){
		this("Titulo");
	}

	public AlertTitle(String titulo){
		super(titulo);
		this.getStyleClass().setAll("h1");
	}

	public void setTitle(String title){
		this.setText(title);
	}
	
}
