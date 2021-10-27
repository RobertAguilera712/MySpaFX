package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;

public class ReservacionesController implements Initializable {

	@Override
	public void initialize(URL url, ResourceBundle rb) {

	}

	@FXML
	private void agregar(ActionEvent event) throws IOException {
		Scene currentWindow = ((Node) event.getTarget()).getScene();
		ScrollPane mainContainer = (ScrollPane) currentWindow.lookup("#mainContainer");
		Node nodo = FXMLLoader.load(getClass().getResource("ReservacionesForm.fxml"));
		mainContainer.setContent(nodo);
	}

}
