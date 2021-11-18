package utils;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Window;
import model.Item;

public class Utils {

	public final static Item filtrosEstatus[] = {new Item("Activos", "1"), new Item("Inactivos", "0")};

	public static Window getCurrentWindow(ActionEvent event){
		return ((Node)event.getTarget()).getScene().getWindow();
	}

	public static Scene getCurrentScene(ActionEvent event){
		return ((Node)event.getTarget()).getScene();
	}
	
}
