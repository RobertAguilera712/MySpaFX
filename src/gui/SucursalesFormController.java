package gui;

import com.google.gson.Gson;
import com.jfoenix.controls.JFXTextField;
import gui.Alerts.AlertIcon;
import gui.Alerts.ConfirmationAlert;
import gui.Alerts.OkAlert;
import gui.Alerts.WaitAlert;
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
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import model.Sucursal;
import rest.Rest;
import utils.Checar;
import utils.Utils;

public class SucursalesFormController implements Initializable {

	@FXML
	private JFXTextField txtNombre;

	@FXML
	private JFXTextField txtDomicilio;

	@FXML
	private JFXTextField txtLatitud;

	@FXML
	private JFXTextField txtLongitud;

	private Gson gson;
	private Sucursal temp;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		gson = new Gson();
		temp = null;
	}

	@FXML
	void guardar(ActionEvent event) {
		String mensajeError = Checar.checarInputsTexto(txtNombre, txtDomicilio, txtLatitud, txtLongitud);

		if (mensajeError.isEmpty()) {
			ConfirmationAlert alerta = new ConfirmationAlert(AlertIcon.QUESTION, Utils.getCurrentWindow(event));
			if (temp == null) {
				alerta.setTitle("¿Quieres guardar el nuevo registro?");
				alerta.setTextContent("");
				alerta.setConfirmationButtonText("Si, guardarlo");
				alerta.setCancellationButtonText("No, Cancelar");

				alerta.setConfirmationButtonAction(e -> {
					Sucursal nuevaSucursal = getSucursal();
					Rest.agregarGet("branch", gson.toJson(nuevaSucursal));
					WaitAlert waitAlert = new WaitAlert(AlertIcon.SUCCESS, Utils.getCurrentWindow(event));
					waitAlert.setTitle("Registro guardado");
					waitAlert.setTextContent("El nuevo registro se guardó correctamente");
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
				alerta.setTitle("¿Quieres guardar los cambios?");
				alerta.setTextContent("");
				alerta.setConfirmationButtonText("Si, guardarlos");
				alerta.setCancellationButtonText("No, Cancelar");

				alerta.setConfirmationButtonAction(e -> {
					Sucursal nuevaSucursal = getSucursal();
					nuevaSucursal.setId(temp.getId());

					String json = gson.toJson(nuevaSucursal);

					Rest.modificarGet("branch", json);
					WaitAlert waitAlert = new WaitAlert(AlertIcon.SUCCESS, Utils.getCurrentWindow(event));
					waitAlert.setTitle("Registro modificado correctamente");
					waitAlert.setTextContent("El registro se modificó correctamente");
					waitAlert.showAndWaitFor(2);
					regresar(event);
				});

				alerta.setCancellationButtonAction(e -> {
					WaitAlert waitAlert = new WaitAlert(AlertIcon.ERROR, Utils.getCurrentWindow(event));
					waitAlert.setTitle("Cancelado");
					waitAlert.setTextContent("No se guardaron los cambios");
					waitAlert.showAndWaitFor(2);
				});

				alerta.showAndWait();

			}

		} else {
			OkAlert alert = new OkAlert(AlertIcon.WARNING, Utils.getCurrentWindow(event));
			alert.setTitle("No has llenado Todos los campos");
			alert.setTextContent(mensajeError);
			alert.showAndWait();
		}

	}

	@FXML
	void regresar(ActionEvent event) {
		try {
			Scene currentScene = Utils.getCurrentScene(event);
			ScrollPane mainContainer = (ScrollPane) currentScene.lookup("#mainContainer");
			Node nodo = FXMLLoader.load((getClass().getResource("Sucursales.fxml")));
			mainContainer.setContent(nodo);
		} catch (IOException ex) {
			Logger.getLogger(SucursalesFormController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private void limpiarForm() {
		txtDomicilio.setText("");
		txtNombre.setText("");
		txtLatitud.setText("");
		txtLongitud.setText("");
	}

	public void setSucursal(Sucursal s) {
		this.temp = s;
		txtNombre.setText(s.getNombre());
		txtDomicilio.setText(s.getDomicilio());
		txtLatitud.setText(String.valueOf(s.getLatitud()));
		txtLongitud.setText(String.valueOf(s.getLatitud()));
	}

	private Sucursal getSucursal() {
		Sucursal sucursal = new Sucursal();

		sucursal.setNombre(txtNombre.getText());
		sucursal.setDomicilio(txtDomicilio.getText());
		sucursal.setLatitud(Double.parseDouble(txtLatitud.getText()));
		sucursal.setLongitud(Double.parseDouble(txtLongitud.getText()));
		sucursal.setEstatus(1);

		return sucursal;
	}

}
