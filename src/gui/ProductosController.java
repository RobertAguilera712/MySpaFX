package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Producto;
import utils.Utils;

public class ProductosController implements Initializable {

	@FXML
    private TableView<Producto> tablaProductos;

    @FXML
    private TableColumn<?, ?> columnId;

    @FXML
    private TableColumn<?, ?> columnNombre;

    @FXML
    private TableColumn<?, ?> columnMarca;

    @FXML
    private TableColumn<?, ?> columnPrecio;

    @FXML
    private TableColumn<?, ?> columndEstatus;

    @FXML
    private TableColumn<?, ?> columnAcciones;

	private FXMLLoader loader;
	private ProductosFormController formController;


	@Override
	public void initialize(URL url, ResourceBundle rb) {
		columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		columnNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		columnMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
		columnPrecio.setCellValueFactory(new PropertyValueFactory<>("precioUso"));
		columndEstatus.setCellValueFactory(new PropertyValueFactory<>("estatus"));
		columnAcciones.setCellValueFactory(new PropertyValueFactory<>("acciones"));
	}

	@FXML
	private void agregar(ActionEvent event) throws IOException{
		Scene currentScene = Utils.getCurrentScene(event);
		ScrollPane mainContainer = (ScrollPane) currentScene.lookup("#mainContainer");
		loader = new FXMLLoader(getClass().getResource("ProductosForm.fxml"));
		Node nodo =loader.load();
		formController = loader.getController();
		formController.setLista(tablaProductos.getItems());
		mainContainer.setContent(nodo);
	}

	public void setProductos(ObservableList<Producto> productos){
		tablaProductos.getItems().setAll(productos);
	}

}
