package gui.Alerts;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 * A hbox that contains a label. This object is intended to be inside an AlertContainer
 * @author kasparov
 */
public class AlertContent extends HBox {

	private Label label;

	/**
	 * Creates an AlertContent with lorem ipsum as default text
	 */
	public AlertContent() {
		this("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor\n" +
"incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis\n" +
"nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.\n" +
"Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu\n" +
"fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in\n" +
"culpa qui officia deserunt mollit anim id est laborum.");
	}

	/**
	 * Creates an AlertContainer with the provided text
	 * @param text text to be set inside the label
	 */
	public AlertContent(String text) {
		this(new Label(text));
	}

	/**
	 * Creates an AlertContainer with the label provided as child
	 * @param label Laber to be set as child of this object
	 */
	public AlertContent(Label label){
		this.label = label;
		this.getChildren().add(this.label);
	}

	public void setText(String text) {
		this.label.setText(text);
	}

	public void setLabel(Label label) {
		this.label = label;
	}

}
