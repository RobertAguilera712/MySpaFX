package gui.Alerts;

import com.jfoenix.controls.JFXButton;
import javafx.scene.layout.HBox;

/**
 * A container of buttons. A confirmation one, and a cancelation one
 * @author kasparov
 */
public class AlertBtnContainer extends HBox {

	private JFXButton confirmactionButton;
	private JFXButton cancelationButton;

	/**
	 * Creates an AlertBtnContainer with a confirmation button.
	 * The confirmation button is a default instance of an AlertButton
	 */
	public AlertBtnContainer(){
		this(new AlertButton());
	}

	/**
	 * Creates an AlertBtnContainer with the provided confirmationButton.
	 * The cancelation button is not instance, hence is not a child of this container 
	 * @param confirmationButton Button to be set as the confirmation button
	 */
	public AlertBtnContainer(JFXButton confirmationButton){
		super();
		this.confirmactionButton = confirmationButton;
		this.getChildren().setAll(this.confirmactionButton);
		this.getStyleClass().setAll("alert-btn-container");
	}
	
	/**
	 * Creates an AlertBtnContainer with the provided confirmation and cancelation Buttons.
	 * @param confirmationButton Button to be set as the confirmation button
	 * @param cancelationButton Button to be set as the cancelation button
	 */
	public AlertBtnContainer(JFXButton confirmationButton, JFXButton cancelationButton){
		this(confirmationButton);
		this.cancelationButton = cancelationButton;
		this.getChildren().add(this.cancelationButton);
	}

	public JFXButton getConfirmactionButton() {
		return confirmactionButton;
	}

	public void setConfirmactionButton(JFXButton confirmactionButton) {
		this.confirmactionButton = confirmactionButton;
	}

	public JFXButton getCancelationButton() {
		return cancelationButton;
	}

	public void setCancelationButton(JFXButton cancelationButton) {
		this.cancelationButton = cancelationButton;
	}

}