package gui.Alerts;

import javafx.scene.control.Label;

/**
 * An Label that acts as icon. It is intended to be inside an AlertContainer
 * @author kasparov
 */
public class AlertIcon extends Label {

	private String iconName;
	private String bgColorClass;

	/**
	 * Creates an Alert icon with the values provided as arguments
	 * @param iconName Name of the icon. Example: warning, error, question... etc
	 * @param bgColorClass Background color css class. Example: bg-danger, bg-warining.. etc
	 */
	public AlertIcon(String iconName, String bgColorClass) {
		super();
		this.iconName = iconName;
		this.bgColorClass = bgColorClass;
		this.getStyleClass().setAll("icon", iconName, bgColorClass, "alert-icon");
	}

	/**
	 * Creates an Alert icon with a warning icon with bg-waring as background color
	 */
	public AlertIcon(){
		this("warning", "bg-warning");
	}

	public String getIconName() {
		return iconName;
	}

	public void setIconName(String iconName) {
		this.iconName = iconName;
		this.getStyleClass().set(1, this.iconName);
	}

	public String getBgColorClass() {
		return bgColorClass;
	}

	public void setBgColorClass(String bgColorClass) {
		this.bgColorClass = bgColorClass;
		this.getStyleClass().set(2, this.bgColorClass);
	}
	
}
