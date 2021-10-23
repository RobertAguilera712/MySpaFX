package gui.Alerts;

import com.jfoenix.controls.JFXButton;

/**
 * A button to be inside an AlertBtnContainer.
 * @author kasparov
 */
public class AlertButton extends JFXButton {
	
	/**
	 * Creates a primary button with Ok as text
	 */
	public AlertButton(){
		this("Ok");
	}

	/**
	 * Creates a primary button with the text provided
	 * @param text text to be inside the button.
	 */
	public AlertButton(String text){
		this(text, "btn-primary");
	}

	/**
	 * Creates a button with the text and className provided.
	 * @param text text to be inside the button.
	 * @param className btn class. Example: btn-primary, btn-danger, btn-success.
	 */
	public AlertButton(String text, String className){
		super(text);
		this.getStyleClass().add("btn");
		this.getStyleClass().add(className);
	}
	
}
