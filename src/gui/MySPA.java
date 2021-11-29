package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import utils.Utils;

public class MySPA implements Initializable {

	@FXML
    private Label txtUsuario;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		txtUsuario.setText(Utils.cuentaEmpleado.getNombre());
	}	
	
}
