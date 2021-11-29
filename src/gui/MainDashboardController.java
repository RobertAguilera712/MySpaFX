package gui;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Base64;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
import utils.Utils;

public class MainDashboardController implements Initializable {

	@FXML
	private ColumnConstraints dashboard;

	@FXML
	private Button btnMenu;

	@FXML
	private ScrollPane mainContainer;

	@FXML
	private ImageView imgUsuario;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		String imgEmpleado = Utils.cuentaEmpleado.getFotoString();
		if (!imgEmpleado.isEmpty()) {
			imgUsuario.setImage(Utils.decodeImage(imgEmpleado));
		}
		try {
			Node nodo = FXMLLoader.load(getClass().getResource("MySPA.fxml"));
			mainContainer.setContent(nodo);
		} catch (IOException ex) {
			Logger.getLogger(MainDashboardController.class.getName()).log(Level.SEVERE, null, ex);
		}
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

		mainContainer.setContent(nodo);
	}

	@FXML
	void salir(ActionEvent event) {
		Utils.cuentaEmpleado = null;
		try {
			Parent newWindow = FXMLLoader.load(getClass().getResource("Login.fxml"));
			Scene currentWindow = mainContainer.getScene();
			currentWindow.setRoot(newWindow);
		} catch (IOException ex) {
			Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

}
