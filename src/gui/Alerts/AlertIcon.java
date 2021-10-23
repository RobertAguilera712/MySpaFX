package gui.Alerts;

import javafx.scene.control.Label;

public class AlertIcon extends Label {

	private String iconName;
	private String bgColorClass;

	public AlertIcon(String iconName, String bgColorClass) {
		super();
		this.iconName = iconName;
		this.bgColorClass = bgColorClass;
		this.getStyleClass().setAll("icon", iconName, bgColorClass, "alert-icon");
	}

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
