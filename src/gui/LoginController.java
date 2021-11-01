package gui;

import utils.Check;
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
import utils.Utils;

public class LoginController implements Initializable {

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

		String errorMessage = Check.checkTextInputs(txtUsuario, txtPassword);

		// If message is empty that means that field were filled
		if (errorMessage.isEmpty()) {

			if (checkCredentials()) {
				// Go to the dashboard
				try {
					Parent newWindow = FXMLLoader.load(getClass().getResource("MainDashboard.fxml"));
					Scene currentWindow = ((Node) event.getTarget()).getScene();
					currentWindow.setRoot(newWindow);
				} catch (IOException ex) {
					Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
				}

			} else {
				OkAlert alert = new OkAlert(AlertIcon.ERROR, Utils.getCurrentWindow(event));
				alert.setTitle("Datos de inicio de sesión incorrectos");
				alert.setTextContent("El usuario o la contraseña son icorrectos. Por favor vuelve a intentarloo");
				alert.showAndWait();
				/*Alert container = new Alert(new AlertIcon("error", "bg-danger"), new AlertTitle("Datos de inicio de sesión incorrectos"), new AlertContent("El nombre de usuario o la contraseña no son correctos. Por favor intenta de nuevo"), new AlertBtnContainer());*/
//				Alert content = new Alert(AlertIcon.ERROR);
//				content.setTitle("Hola jaja");
//				content.setTextContent("hola otra vez");
//				alert.setContent(content);
				/*container.getBtnContainer().getConfirmactionButton().setOnAction(event1 -> {
					alert.close();
				});*/

			}

		} else {
			OkAlert alert = new OkAlert(AlertIcon.WARNING, Utils.getCurrentWindow(event));
			alert.setTitle("No has llenado todos los campos");
			alert.setTextContent(errorMessage);
			alert.showAndWait();
		}

	}

	private boolean checkCredentials() {
		String username = txtUsuario.getText();
		String password = txtPassword.getText();

		return username.equals("admin") && password.equals("admin");
	}

}
