package gui;

import com.jfoenix.controls.JFXAlert;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import gui.Alerts.AlertBtnContainer;
import gui.Alerts.AlertContainer;
import gui.Alerts.AlertContent;
import gui.Alerts.AlertIcon;
import gui.Alerts.AlertTitle;
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
	private void login(ActionEvent event) {

		String errorMessage = Check.checkTextInputs(txtUsuario, txtPassword);

		// If message is empty that means that field were filled
		if (errorMessage.isEmpty()) {

			if (checkCredentials()) {
				// Go to the dashboard
				try {
					Parent newWindow = FXMLLoader.load(getClass().getResource("Productos.fxml"));
					Scene currentWindow = ((Node)event.getTarget()).getScene();
					currentWindow.setRoot(newWindow);
					/*FXMLLoader loader = new FXMLLoader(getClass().getResource("Productos.fxml"));
					Parent root = loader.load();
					Window currentWindow = ((Node) event.getSource()).getScene().getWindow();
					Stage stage = (Stage) (currentWindow);
					Scene newScene = new Scene(root, currentWindow.getWidth(), currentWindow.getHeight());
					stage.setScene(newScene);
					stage.show();*/
				} catch (IOException ex) {
					Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
				}

			} else {
				JFXAlert<Void> alert = new JFXAlert<>(((Node) event.getTarget()).getScene().getWindow());
				alert.setOverlayClose(false);
				AlertContainer container = new AlertContainer(new AlertIcon("error", "bg-danger"), new AlertTitle("Datos de inicio de sesión incorrectos"), new AlertContent("El nombre de usuario o la contraseña no son correctos. Por favor intenta de nuevo"), new AlertBtnContainer());
				alert.setContent(container);
				container.getBtnContainer().getConfirmactionButton().setOnAction(event1 -> {
					alert.close();
				});
				alert.showAndWait();

			}

		} else {
			// New alert is created
			JFXAlert<Void> alert = new JFXAlert<>(((Node) event.getTarget()).getScene().getWindow());
			// Avoid alert to be closed when user clicks in main window
			alert.setOverlayClose(false);
			// Creating the content of the alert. See AlertContainer documentation
			AlertContainer container = new AlertContainer(new AlertIcon(), new AlertTitle("No has llenado todos los campos"), new AlertContent(errorMessage), new AlertBtnContainer());
			// asigning the content to the alert
			alert.setContent(container);
			// Setting the ok Button action event. 
			container.getBtnContainer().getConfirmactionButton().setOnAction(event1 -> {
				// When button is clicked. Close the alert
				alert.close();
			});
			// Show the alert and wait
			alert.showAndWait();
		}

	}

	private boolean checkCredentials() {
		String username = txtUsuario.getText();
		String password = txtPassword.getText();

		return username.equals("admin") && password.equals("admin");
	}

}
