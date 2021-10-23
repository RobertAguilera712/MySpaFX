package gui.Alerts;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class AlertContent extends HBox {

	private Label label;

	public AlertContent() {
		this("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor\n" +
"incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis\n" +
"nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.\n" +
"Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu\n" +
"fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in\n" +
"culpa qui officia deserunt mollit anim id est laborum.");
	}

	public AlertContent(String text) {
		super();
		this.label = new Label(text);
		this.getChildren().add(this.label);
	}

	public void setText(String text) {
		this.label.setText(text);
	}

	public void setLabel(Label label) {
		this.label = label;
	}

}
