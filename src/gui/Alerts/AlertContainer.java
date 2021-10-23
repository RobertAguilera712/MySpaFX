package gui.Alerts;

import javafx.scene.layout.VBox;

/**
 * A Vbox that acts as a container of an JFX Alert
 * @author kasparov
 */
public class AlertContainer extends VBox{

	private AlertIcon icon;
	private AlertTitle title;
	private AlertContent content;
	private AlertBtnContainer btnContainer;

	/**
	 * Creates an instance of this object with the provided values
	 * @param icon Icon to be set
	 * @param title Title of the alert
	 * @param content Content of the alert
	 * @param btnContainer BtnContainer to be set
	 */
	public AlertContainer(AlertIcon icon, AlertTitle title, AlertContent content, AlertBtnContainer btnContainer){
		super();
		this.getStylesheets().setAll("gui/styles/icons.css", "gui/styles/styles.css");
		this.getStyleClass().add("alert-container");
		this.icon = icon;
		this.title = title;
		this.content = content;
		this.btnContainer = btnContainer;

		this.getChildren().setAll(icon, title, content, btnContainer);
	}

	/**
	 * Creates an instance of this object with default values for AlertIcon, AlertTitle, 
	 * AlertContent and AlertBtnContainer
	 */
	public AlertContainer(){
		this(new AlertIcon(), new AlertTitle(), new AlertContent(), new AlertBtnContainer());
	}

	public AlertIcon getIcon() {
		return icon;
	}

	public void setIcon(AlertIcon icon) {
		this.icon = icon;
	}

	public AlertTitle getTitle() {
		return title;
	}

	public void setTitle(AlertTitle title) {
		this.title = title;
	}

	public AlertContent getContent() {
		return content;
	}

	public void setContent(AlertContent content) {
		this.content = content;
	}

	public AlertBtnContainer getBtnContainer() {
		return btnContainer;
	}

	public void setBtnContainer(AlertBtnContainer btnContainer) {
		this.btnContainer = btnContainer;
	}
	
}
