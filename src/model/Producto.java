package model;

import gui.Alerts.AlertIcon;
import gui.Alerts.Btn;
import gui.Alerts.BtnType;
import gui.ProductosFormController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import utils.Utils;
import gui.Alerts.ConfirmationAlert;
import gui.Alerts.WaitAlert;

public class Producto {

	private int id, estatus;
	String nombre, marca;
	float precioUso;

	public Producto() {
	}

	public Producto(int id, int estatus, String nombre, String marca, float precioUso) {
		this.id = id;
		this.estatus = estatus;
		this.nombre = nombre;
		this.marca = marca;
		this.precioUso = precioUso;
	}

	public Producto(int estatus, String nombre, String marca, float precioUso) {
		this.estatus = estatus;
		this.nombre = nombre;
		this.marca = marca;
		this.precioUso = precioUso;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getEstatus() {
		return estatus;
	}

	public void setEstatus(int estatus) {
		this.estatus = estatus;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public HBox getAcciones() {
		HBox btnContainer = new HBox(20);
		btnContainer.setAlignment(Pos.CENTER);
		Btn modifyButton = new Btn(BtnType.WARNING, "Modificar");
		Btn deleteButton = new Btn(BtnType.DANGER, "Eliminar");

		modifyButton.setOnAction(e -> {
			try {
				Scene currentScene = Utils.getCurrentScene(e);
				TableView tabla = (TableView) currentScene.lookup("#tablaProductos");
				ScrollPane mainContainer = (ScrollPane) currentScene.lookup("#mainContainer");
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/ProductosForm.fxml"));
				Node nodo = loader.load();
				ProductosFormController formController = loader.getController();
				formController.setProducto(this);
				formController.setLista(tabla.getItems());
				mainContainer.setContent(nodo);
			} catch (IOException ex) {
				Logger.getLogger(Producto.class.getName()).log(Level.SEVERE, null, ex);
			}
		});

		deleteButton.setOnAction(e ->{
			ConfirmationAlert alert = new ConfirmationAlert(AlertIcon.WARNING, Utils.getCurrentWindow(e));
			alert.setTitle("¿Estas seguro de eliminar el registro?");
			alert.setTextContent("");
			alert.setCancellationButtonText("No, cancelar");
			alert.setConfirmationButtonText("Si, eliminarlo");

			alert.setCancellationButtonAction(event ->{
				WaitAlert waitAlert = new WaitAlert(AlertIcon.ERROR, Utils.getCurrentWindow(e));
				waitAlert.setTitle("Cancelado");
				waitAlert.setTextContent("El registro no ha sido eliminado");
				waitAlert.showAndWaitFor(3);
			});

			alert.setConfirmationButtonAction(event ->{
				Scene currentScene = Utils.getCurrentScene(e);
				TableView tabla = (TableView)currentScene.lookup("#tablaProductos");
				tabla.getItems().remove(this);
				WaitAlert waitAlert = new WaitAlert(AlertIcon.SUCCESS, Utils.getCurrentWindow(e));
				waitAlert.setTitle("Registro eliminado");
				waitAlert.setTextContent("El registro ah sido eliminado correctamente");
				waitAlert.showAndWaitFor(3);
			});

			alert.showAndWait();

		});

		btnContainer.getChildren().setAll(modifyButton, deleteButton);

		return btnContainer;
	}

	public float getPrecioUso() {
		return precioUso;
	}

	public void setPrecioUso(float precioUso) {
		this.precioUso = precioUso;
	}

	@Override
	public String toString() {
		return "Producto{" + "id=" + id + ", estatus=" + estatus + ", nombre=" + nombre + ", marca=" + marca + ", precioUso=" + precioUso + '}';
	}
}
