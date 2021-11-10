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
import model.Empleado;
import model.Persona;
import utils.Utils;
import model.Producto;
import model.Usuario;

public class EmpleadosFormController implements Initializable {

	@FXML
    private JFXTextField txtNombre;

    @FXML
    private JFXTextField txtApellido1;

    @FXML
    private JFXTextField txtApellido2;

    @FXML
    private JFXTextField txtDomicilio;

    @FXML
    private JFXComboBox<?> cmbGenero;

    @FXML
    private JFXTextField txtRfc;

    @FXML
    private JFXTextField txtTelefono;

    @FXML
    private JFXTextField txtPuesto;

    @FXML
    private JFXTextField txtUsuario;

    @FXML
    private JFXPasswordField txtPassword;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}
/*


	private Empleado getEmpleado() {
		Empleado empleado = new Empleado(1, "E1234566", txtPuesto.getText(),
				txtRutaImg.getText(),
				new Persona(txtNombre.getText(), txtApellido1.getText(),
						txtApellido2.getText(), txtDireccion.getText(),
						txtTelefono.getText(), txtRfc.getText(), txtGenero.getValue()),
				new Usuario(txtUsuario.getText(), txtPassword.getText(),
						txtPuesto.getText(), "12345"), 1);
		return empleado;
	}

	private void limpiarForm() {
		txtApellido1.setText("");
		txtApellido2.setText("");
		txtDireccion.setText("");
		txtNombre.setText("");
		txtPassword.setText("");
		txtPuesto.setText("");
		txtRfc.setText("");
		txtRutaImg.setText("");
		txtTelefono.setText("");
		txtUsuario.setText("");
	}

	public void setEmpleado(Empleado empleado) {
		this.temp = empleado;
		txtApellido1.setText(this.temp.getApellidoP());
		txtApellido2.setText(this.temp.getApellidoM());
		txtDireccion.setText(this.temp.getDomicilio());
		txtNombre.setText(this.temp.getNombre());
		txtPassword.setText(this.temp.getContrasenia());
		txtPuesto.setText(this.temp.getPuesto());
		txtRfc.setText(this.temp.getRfc());
		txtRutaImg.setText(this.temp.getRutaFoto());
		txtTelefono.setText(this.temp.getTelefono());
		txtUsuario.setText(this.temp.getNombreUsu());
		txtGenero.setValue(this.temp.getGenero());
	}

	@FXML
	void guardar(ActionEvent event) {
		String errorMessage = Check.checkTextInputs(txtApellido1, txtApellido2, txtDireccion,
				txtNombre, txtPassword, txtPuesto, txtRfc, txtRutaImg, txtTelefono, txtUsuario);

		// If message is empty that means that all fields were filled
		if (errorMessage.isEmpty()) {
			ConfirmationAlert alerta = new ConfirmationAlert(AlertIcon.QUESTION, Utils.getCurrentWindow(event));
			alerta.setTitle("¿Quieres guardar el nuevo registro?");
			alerta.setTextContent("");
			alerta.setConfirmationButtonText("Si, gaurdarlo");
			alerta.setCancellationButtonText("No, Cancelar");

			alerta.setConfirmationButtonAction(e -> {
				Empleado nuevoEmpleado = getEmpleado();
				WaitAlert waitAlert = new WaitAlert(AlertIcon.SUCCESS, Utils.getCurrentWindow(event));
				if (temp != null) {
					int index = getIndex();
					listaEmpleados.remove(index);
					listaEmpleados.add(index, nuevoEmpleado);
					waitAlert.setTitle("Modificación existosa");
					waitAlert.setTextContent("Se modificaron los datos");
				} else {
					listaEmpleados.add(nuevoEmpleado);
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
	void regresar(ActionEvent event) throws IOException {
		Scene currentScene = Utils.getCurrentScene(event);
		ScrollPane mainContainer = (ScrollPane) currentScene.lookup("#mainContainer");
		loader = new FXMLLoader(getClass().getResource("Empleados.fxml"));
		Node nodo = loader.load();
		empleadosController = loader.getController();
		empleadosController.setEmpleados(listaEmpleados);
		mainContainer.setContent(nodo);
	}

	private int getIndex() {
		int index = -1;
		String tempToString = temp.toString();
		String currenToString = "";
		Empleado current;
		for (int i = 0, n = listaEmpleados.size(); i < n; i++) {
			current = listaEmpleados.get(i);
			currenToString = current.toString();
			if (tempToString.equals(currenToString)) {
				index = i;
				break;
			}
		}

		return index;
	}
*/
}
