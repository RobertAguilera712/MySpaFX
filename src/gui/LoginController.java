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
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class LoginController implements Initializable {

	@FXML
	private JFXTextField txtUsuario;

	@FXML
	private JFXPasswordField txtPassword;

	@FXML
	private JFXButton btnLogin;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
	}

	@FXML
	private void login(ActionEvent event) {

		String errorMessage = Validar.validarCamposTexto(txtUsuario, txtPassword);

		if (errorMessage.isEmpty()) {
			System.out.println(errorMessage);

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

}
