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
import javafx.scene.layout.HBox;

public class ProductosController implements Initializable {

	@Override
	public void initialize(URL url, ResourceBundle rb) {
/*		productID.setCellValueFactory(new PropertyValueFactory<>("id"));
		productName.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		productBrand.setCellValueFactory(new PropertyValueFactory<>("marca"));
		productPrice.setCellValueFactory(new PropertyValueFactory<>("precioUso"));
		productStatus.setCellValueFactory(new PropertyValueFactory<>("estatus"));

		tabla.getItems().add(new Producto(1, Estatus.ACTIVO, "Crema", "hands", 200));
		tabla.getItems().add(new Producto(2, Estatus.ACTIVO, "Perfume", "CR7", 500));*/

	}

	@FXML
	private void agregar(ActionEvent event) throws IOException {
		Scene currentWindow = ((Node) event.getTarget()).getScene();
		HBox mainContainer = (HBox) currentWindow.lookup("#mainContainer");
		Node nodo = FXMLLoader.load(getClass().getResource("ProductosForm.fxml"));
		mainContainer.getChildren().setAll(nodo);
	}

}
