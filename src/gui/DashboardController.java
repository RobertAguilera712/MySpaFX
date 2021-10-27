package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;

public class DashboardController implements Initializable {

	@FXML
	private ColumnConstraints dashboard;

	@FXML
	private Button btnMenu;

	@FXML
	private HBox mainContainer;

	@Override
	public void initialize(URL url, ResourceBundle rb) {

	}

	@FXML
	private void toggleDashboard(ActionEvent event) {
		if (btnMenu.getStyleClass().contains("menu")) {
			btnMenu.getStyleClass().remove("menu");
			btnMenu.getStyleClass().add("cerrar-menu");
			dashboard.setPrefWidth(170);
		} else if (btnMenu.getStyleClass().contains("cerrar-menu")) {
			btnMenu.getStyleClass().remove("cerrar-menu");
			btnMenu.getStyleClass().add("menu");
			dashboard.setPrefWidth(50);
		}
	}

	@FXML
	private void switchModule(ActionEvent event) throws IOException {
		Button btn = ((Button) event.getTarget());
		HBox parent = (HBox) btn.getParent();

		Scene currentWindow = ((Node) event.getTarget()).getScene();

		((Rectangle) currentWindow.lookup(".active")).getStyleClass().setAll("unactive");

		((Rectangle) parent.getChildren().get(0)).getStyleClass().setAll("active");

		String moduleName = ((Label) parent.getChildren().get(2)).getText();
		Node nodo = FXMLLoader.load(getClass().getResource(String.format("%s.fxml", moduleName)));
	
		mainContainer.getChildren().setAll(nodo);
	}

}
