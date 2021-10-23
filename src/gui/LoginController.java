package gui;

import com.jfoenix.controls.JFXAlert;
import com.jfoenix.controls.JFXButton;
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
import javafx.stage.Stage;

public class LoginController implements Initializable {

	@FXML
	private JFXTextField txtUsuario;

	@FXML
	private JFXPasswordField txtPassword;

	@FXML
	private JFXButton btnLogin;

	private Stage stage;
	private Scene scene;
	private Parent root;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
	}

	@FXML
	private void login(ActionEvent event) {

		String errorMessage = Check.checkTextInputs(txtUsuario, txtPassword);

		if (errorMessage.isEmpty()) {

			if (checkCredentials()) {
				// Go to the dashboard
				try {
					FXMLLoader loader = new FXMLLoader(getClass().getResource("Productos.fxml"));
					root = loader.load();
					stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
					scene = new Scene(root);
					stage.setScene(scene);
					stage.setMaximized(true);
					stage.show();
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
