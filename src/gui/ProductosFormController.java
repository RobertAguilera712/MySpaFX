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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.ColumnConstraints;
import model.Estatus;
import model.Producto;

public class ProductosFormController implements Initializable {

	@FXML
	private ColumnConstraints dashboard;

	@FXML
	private Button btnMenu;


	@Override
	public void initialize(URL url, ResourceBundle rb) {

	}

	@FXML
	private void toggleDashboard() {
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
	private void regresar(ActionEvent event) {
		try {
			Parent newWindow = FXMLLoader.load(getClass().getResource("Productos.fxml"));
			Scene currentWindow = ((Node) event.getTarget()).getScene();
			currentWindow.setRoot(newWindow);
		} catch (IOException ex) {
			Logger.getLogger(ProductosFormController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

}
