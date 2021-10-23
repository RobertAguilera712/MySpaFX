package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.ColumnConstraints;
import model.Estatus;
import model.Producto;

public class ProductosController implements Initializable {

	@FXML
	private ColumnConstraints dashboard;

	@FXML
	private Button btnMenu;

	@FXML
    private TableView<Producto> tabla;

	 @FXML
    private TableColumn<Producto, Integer> productID;

    @FXML
    private TableColumn<Producto, String> productName;

    @FXML
    private TableColumn<Producto, String> productBrand;

    @FXML
    private TableColumn<Producto, Float> productPrice;

    @FXML
    private TableColumn<Producto, Integer> productStatus;

    @FXML
    private TableColumn<?, ?> productActions;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		productID.setCellValueFactory(new PropertyValueFactory<>("id"));
		productName.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		productBrand.setCellValueFactory(new PropertyValueFactory<>("marca"));
		productPrice.setCellValueFactory(new PropertyValueFactory<>("precioUso"));
		productStatus.setCellValueFactory(new PropertyValueFactory<>("estatus"));

		tabla.getItems().add(new Producto(1, Estatus.ACTIVO, "Crema", "hands", 200));
		tabla.getItems().add(new Producto(2, Estatus.ACTIVO, "Perfume", "CR7", 500));
	
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

}
