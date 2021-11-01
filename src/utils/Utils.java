package utils;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Window;

public class Utils {

	public static Window getCurrentWindow(ActionEvent event){
		return ((Node)event.getTarget()).getScene().getWindow();
	}

	public static Scene getCurrentScene(ActionEvent event){
		return ((Node)event.getTarget()).getScene();
	}
	
}
