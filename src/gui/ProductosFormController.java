package gui;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import utils.Check;
import com.jfoenix.controls.JFXTextField;
import gui.Alerts.AlertIcon;
import gui.Alerts.ConfirmationAlert;
import gui.Alerts.OkAlert;
import gui.Alerts.WaitAlert;
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
import utils.Utils;
import model.Producto;

public class ProductosFormController implements Initializable {

	private ObservableList<Producto> listaProductos;

	@FXML
	private JFXTextField txtNombre;

	@FXML
	private JFXTextField txtMarca;

	@FXML
	private JFXTextField txtPrecio;

	private FXMLLoader loader;
	private ProductosController productosController;
	private Producto temp;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
	}

	public void setLista(ObservableList<Producto> lista){
		this.listaProductos = lista;
	}

	@FXML
	private void guardar(ActionEvent event) throws IOException {

		String errorMessage = Check.checkTextInputs(txtNombre, txtMarca, txtPrecio);

		// If message is empty that means that all fields were filled
		if (errorMessage.isEmpty()) {
			ConfirmationAlert alerta = new ConfirmationAlert(AlertIcon.QUESTION, Utils.getCurrentWindow(event));
			alerta.setTitle("¿Quieres guardar el nuevo registro?");
			alerta.setTextContent("");
			alerta.setConfirmationButtonText("Si, gaurdarlo");
			alerta.setCancellationButtonText("No, Cancelar");

			alerta.setConfirmationButtonAction(e -> {
				Producto nuevoProducto = getProducto();
				WaitAlert waitAlert = new WaitAlert(AlertIcon.SUCCESS, Utils.getCurrentWindow(event));
				if (temp != null) {
					int index = getIndex();
					listaProductos.remove(index);
					listaProductos.add(index, nuevoProducto);
					waitAlert.setTitle("Modificación existosa");
					waitAlert.setTextContent("Se modificaron los datos");
				} else {
					listaProductos.add(getProducto());
					waitAlert.setTitle("Registro guardado");
					waitAlert.setTextContent("El nuevo registro se guardó correctamente");
				}
				limpiarForm();
				waitAlert.showAndWaitFor(2);
			});

			alerta.setCancellationButtonAction(e -> {
				WaitAlert waitAlert = new WaitAlert(AlertIcon.ERROR, Utils.getCurrentWindow(event));
				waitAlert.setTitle("Cancelado");
				waitAlert.setTextContent("El registro no fue guardado");
				waitAlert.showAndWaitFor(2);
			});

			alerta.showAndWait();

		} else {
			OkAlert alert = new OkAlert(AlertIcon.WARNING, Utils.getCurrentWindow(event));
			alert.setTitle("No has llenado Todos los campos");
			alert.setTextContent(errorMessage);
			alert.showAndWait();
		}

	}

	@FXML
	private void regresar(ActionEvent event) throws IOException {
		Scene currentScene = Utils.getCurrentScene(event);
		ScrollPane mainContainer = (ScrollPane) currentScene.lookup("#mainContainer");
		loader = new FXMLLoader(getClass().getResource("Productos.fxml"));
		Node nodo = loader.load();
		productosController = loader.getController();
		productosController.setProductos(listaProductos);
		mainContainer.setContent(nodo);
	}

	private Producto getProducto() {
		return new Producto(1, 1, txtNombre.getText(), txtMarca.getText(), Float.valueOf(txtPrecio.getText()));
	}

	public void setProducto(Producto producto) {
		this.temp = producto;
		this.txtNombre.setText(this.temp.getNombre());
		this.txtMarca.setText(this.temp.getMarca());
		this.txtPrecio.setText(String.valueOf(this.temp.getPrecioUso()));
	}

	private void limpiarForm() {
		txtNombre.setText("");
		txtMarca.setText("");
		txtPrecio.setText("");
	}

	private int getIndex(){
		int index = -1;
		String tempToString = temp.toString();
		String currenToString = "";
		Producto current ;
		for (int i = 0, n = listaProductos.size(); i < n; i++){
			current = listaProductos.get(i);
			currenToString = current.toString();
			if (tempToString.equals(currenToString)){
				index = i;
				break;
			}
		}

		return index;
	}

}
