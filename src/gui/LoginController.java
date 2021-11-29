package gui;

import utils.Checar;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import gui.Alerts.AlertIcon;
import gui.Alerts.OkAlert;
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
import model.Empleado;
import rest.Rest;
import utils.Utils;

public class LoginController implements Initializable {

	enum Estatus {
		OK, ERROR, INCORRECTO;
	}

	@FXML
	private JFXTextField txtUsuario;

	@FXML
	private JFXPasswordField txtPassword;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
	}

	@FXML
	private void login(ActionEvent event) throws IOException {

		String errorMessage = Checar.checarInputsTexto(txtUsuario, txtPassword);

		// If message is empty that means that field were filled
		if (errorMessage.isEmpty()) {

			switch (checkCredentials()) {
				case OK:
				try {
					Parent newWindow = FXMLLoader.load(getClass().getResource("MainDashboard.fxml"));
					Scene currentWindow = ((Node) event.getTarget()).getScene();
					currentWindow.setRoot(newWindow);
				} catch (IOException ex) {
					Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
				}
				break;
				case ERROR:
					OkAlert alertError = new OkAlert(AlertIcon.ERROR, txtPassword.getScene().getWindow());
					alertError.setTitle("Erro de conexión");
					alertError.setTextContent("No se pudo establecer conexión con el servidor. Por favor intentalo más tarde");
					alertError.showAndWait();
					break;
				case INCORRECTO:
					OkAlert alert = new OkAlert(AlertIcon.ERROR, Utils.getCurrentWindow(event));
					alert.setTitle("Datos de inicio de sesión incorrectos");
					alert.setTextContent("El usuario o la contraseña son icorrectos. Por favor vuelve a intentarlo");
					alert.showAndWait();
					break;
			}
		} else {
			OkAlert alert = new OkAlert(AlertIcon.WARNING, Utils.getCurrentWindow(event));
			alert.setTitle("No has llenado todos los campos");
			alert.setTextContent(errorMessage);
			alert.showAndWait();
		}

	}

	private Estatus checkCredentials() {
		String username = txtUsuario.getText();
		String password = txtPassword.getText();

		Empleado e;

		try {
			e = Rest.login(username, password);
		} catch (Exception ex) {
			return Estatus.ERROR;
		}

		if (e != null) {
			Utils.cuentaEmpleado = e;
			return Estatus.OK;
		}

		return Estatus.INCORRECTO;
	}

}
