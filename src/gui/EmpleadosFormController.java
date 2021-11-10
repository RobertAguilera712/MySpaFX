package gui;

import com.google.gson.Gson;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
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
import model.Empleado;
import model.Item;
import model.Persona;
import model.Usuario;
import rest.Rest;
import utils.Check;
import utils.Utils;

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
	private JFXComboBox<Item> cmbGenero;

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

	private static final Item generos[] = {new Item("Selecciona el género", ""), new Item("Masculino", "M"), new Item("Femenino", "F"), new Item("Otro", "O")};

	private Gson gson;
	private Empleado temp;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		gson = new Gson();
		temp = null;
		cmbGenero.getItems().setAll(generos);
		cmbGenero.setValue(generos[0]);
	}

	@FXML
	void guardar(ActionEvent event) {
		String mensajeError = Check.checkTextInputs(txtNombre, txtApellido1, txtApellido2, txtDomicilio,
				txtPassword, txtPuesto, txtRfc, txtTelefono, txtUsuario)
				+ Check.checarCombos(cmbGenero);

		if (mensajeError.isEmpty()) {
			ConfirmationAlert alerta = new ConfirmationAlert(AlertIcon.QUESTION, Utils.getCurrentWindow(event));
			if (temp == null) {
				alerta.setTitle("¿Quieres guardar el nuevo registro?");
				alerta.setTextContent("");
				alerta.setConfirmationButtonText("Si, gaurdarlo");
				alerta.setCancellationButtonText("No, Cancelar");

				alerta.setConfirmationButtonAction(e -> {
					Empleado nuevoEmpleado = getEmpleado();
					Rest.agregarPost("employee", gson.toJson(nuevoEmpleado));
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

			}else{
				alerta.setTitle("¿Quieres guardar los cambios?");
				alerta.setTextContent("");
				alerta.setConfirmationButtonText("Si, guardarlos");
				alerta.setCancellationButtonText("No, Cancelar");

				alerta.setConfirmationButtonAction(e -> {
					Empleado nuevoEmpleado = getEmpleado();
					nuevoEmpleado.setId(temp.getId());
					nuevoEmpleado.getPersona().setId(temp.getPersona().getId());
					nuevoEmpleado.getUsuario().setId(nuevoEmpleado.getUsuario().getId());

					Rest.modificarPost("employee", gson.toJson(nuevoEmpleado));
					WaitAlert waitAlert = new WaitAlert(AlertIcon.SUCCESS, Utils.getCurrentWindow(event));
					waitAlert.setTitle("Registro modificado correctamente");
					waitAlert.setTextContent("El registro se modificó correctamente");
					waitAlert.showAndWaitFor(2);
					try {
						regresar(null);
					} catch (IOException ex) {
						Logger.getLogger(EmpleadosFormController.class.getName()).log(Level.SEVERE, null, ex);
					}
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
	void regresar(ActionEvent event) throws IOException {
		Scene currentScene = Utils.getCurrentScene(event);
		ScrollPane mainContainer = (ScrollPane) currentScene.lookup("#mainContainer");
		Node nodo = FXMLLoader.load((getClass().getResource("Empleados.fxml")));
		mainContainer.setContent(nodo);
	}

	private void limpiarForm() {
		txtApellido1.setText("");
		txtApellido2.setText("");
		txtDomicilio.setText("");
		txtNombre.setText("");
		txtPassword.setText("");
		txtPuesto.setText("");
		txtRfc.setText("");
		txtTelefono.setText("");
		txtUsuario.setText("");
		cmbGenero.setValue(generos[0]);
	}

	public void setEmpleado(Empleado e) {
		this.temp = e;
		txtApellido1.setText(e.getApellidoP());
		txtApellido2.setText(e.getApellidoM());
		txtDomicilio.setText(e.getDomicilio());
		txtNombre.setText(e.getNombre());
		txtPassword.setText(e.getContrasenia());
		txtPuesto.setText(e.getPuesto());
		txtRfc.setText(e.getRfc());
		txtTelefono.setText(e.getTelefono());
		txtUsuario.setText(e.getNombreUsu());

		for (Item item : generos) {
			if (item.getValue().equalsIgnoreCase(e.getGenero())) {
				cmbGenero.setValue(item);
				break;
			}
		}
	}

	private Empleado getEmpleado() {
		Empleado empleado = new Empleado();
		Persona persona = new Persona();
		Usuario usuario = new Usuario();

		persona.setNombre(txtNombre.getText());
		persona.setApellidoP(txtApellido1.getText());
		persona.setApellidoM(txtApellido2.getText());
		persona.setGenero(cmbGenero.getValue().getValue());
		persona.setRfc(txtRfc.getText());
		persona.setDomicilio(txtDomicilio.getText());
		persona.setTelefono(txtTelefono.getText());

		usuario.setNombreUsu(txtUsuario.getText());
		usuario.setContrasenia(txtPassword.getText());
		usuario.setRol(txtPuesto.getText());

		empleado.setEstatus(1);
		empleado.setPuesto(txtPuesto.getText());
		empleado.setPersona(persona);
		empleado.setUsuario(usuario);

		return empleado;
	}
	/*

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
