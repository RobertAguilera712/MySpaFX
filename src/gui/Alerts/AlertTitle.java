package gui.Alerts;

import javafx.scene.control.Label;

/**
 * A label that acts as a title for an AlertContainer
 * @author kasparov
 */
public class AlertTitle extends Label {

	/**
	 * Creates an instance of this class with Titulo as default value
	 */
	public AlertTitle(){
		this("Titulo");
	}

	/**
	 * Creates an instance of this class with the provided String as text of the label
	 * @param titulo Text to be displayed in the label
	 */
	public AlertTitle(String titulo){
		super(titulo);
		this.getStyleClass().setAll("h1");
	}

	public void setTitle(String title){
		this.setText(title);
	}
	
}
