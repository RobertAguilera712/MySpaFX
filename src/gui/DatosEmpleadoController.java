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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import model.Empleado;
import model.Item;
import model.Persona;
import model.Usuario;
import rest.Rest;
import utils.Checar;
import utils.Utils;

public class DatosEmpleadoController implements Initializable {

	@FXML
	private Label txtTitulo;

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

	@FXML
	private Label lbImg;

	@FXML
	private JFXTextField txtNumEmpleado;

	@FXML
	private ImageView selectedImg;

	private static final Item generos[] = {new Item("Selecciona el género", ""), new Item("Masculino", "M"), new Item("Femenino", "F"), new Item("Otro", "O")};

	private Gson gson;
	private Empleado temp;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		gson = new Gson();
		temp = null;
		cmbGenero.getItems().setAll(generos);
		cmbGenero.setValue(generos[0]);

		txtNumEmpleado.setText(Utils.cuentaEmpleado.getNumeroEmpleado());
		txtApellido1.setText(Utils.cuentaEmpleado.getApellidoP());
		txtApellido2.setText(Utils.cuentaEmpleado.getApellidoM());
		txtDomicilio.setText(Utils.cuentaEmpleado.getDomicilio());
		txtNombre.setText(Utils.cuentaEmpleado.getNombre());
		txtPassword.setText(Utils.cuentaEmpleado.getContrasenia());
		txtPuesto.setText(Utils.cuentaEmpleado.getPuesto());
		txtRfc.setText(Utils.cuentaEmpleado.getRfc());
		txtTelefono.setText(Utils.cuentaEmpleado.getTelefono());
		txtUsuario.setText(Utils.cuentaEmpleado.getNombreUsu());

		if (!Utils.cuentaEmpleado.getFotoString().isEmpty()) {
			selectedImg.setImage(Utils.decodeImage(Utils.cuentaEmpleado.getFotoString()));
			lbImg.setText("");
		}

		for (Item item : generos) {
			if (item.getValor().equalsIgnoreCase(Utils.cuentaEmpleado.getGenero())) {
				cmbGenero.setValue(item);
				break;
			}
		}
	}

	@FXML
	void guardar(ActionEvent event) {
		String mensajeError = Checar.checarInputsTexto(txtNombre, txtApellido1, txtApellido2, txtDomicilio,
				txtPassword, txtPuesto, txtRfc, txtTelefono, txtUsuario)
				+ Checar.checarCombos(cmbGenero);

		if (mensajeError.isEmpty()) {
			ConfirmationAlert alerta = new ConfirmationAlert(AlertIcon.QUESTION, Utils.getCurrentWindow(event));

			alerta.setTitle("¿Quieres guardar los cambios?");
			alerta.setTextContent("");
			alerta.setConfirmationButtonText("Si, guardarlos");
			alerta.setCancellationButtonText("No, Cancelar");

			alerta.setConfirmationButtonAction(e -> {
				Empleado nuevoEmpleado = getEmpleado();
				nuevoEmpleado.setId(Utils.cuentaEmpleado.getId());
				nuevoEmpleado.getPersona().setId(Utils.cuentaEmpleado.getPersona().getId());
				nuevoEmpleado.getUsuario().setId(Utils.cuentaEmpleado.getUsuario().getId());

				String json = gson.toJson(nuevoEmpleado);

				System.out.println(json);

				Rest.modificarPost("employee", json);
				WaitAlert waitAlert = new WaitAlert(AlertIcon.SUCCESS, Utils.getCurrentWindow(event));
				waitAlert.setTitle("Registro modificado correctamente");
				waitAlert.setTextContent("El registro se modificó correctamente");
				waitAlert.showAndWaitFor(2);
				Utils.cuentaEmpleado = nuevoEmpleado;
				try {
					Parent newWindow = FXMLLoader.load(getClass().getResource("MainDashboard.fxml"));
					Scene currentWindow = Utils.getCurrentScene(event);
					currentWindow.setRoot(newWindow);
				} catch (IOException ex) {
					Logger.getLogger(DatosEmpleadoController.class.getName()).log(Level.SEVERE, null, ex);
				}
			});

			alerta.setCancellationButtonAction(e -> {
				WaitAlert waitAlert = new WaitAlert(AlertIcon.ERROR, Utils.getCurrentWindow(event));
				waitAlert.setTitle("Cancelado");
				waitAlert.setTextContent("No se guardaron los cambios");
				waitAlert.showAndWaitFor(2);
			});

			alerta.showAndWait();

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
		Node nodo = FXMLLoader.load((getClass().getResource("MySPA.fxml")));
		mainContainer.setContent(nodo);
	}

	@FXML
	void selectImg(ActionEvent event) {
		try {
			selectedImg.setImage(Utils.readImage());
			lbImg.setText("");
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	@FXML
	void modificar(ActionEvent event) {
		Scene currentScene = Utils.getCurrentScene(event);
		ScrollPane mainContainer = (ScrollPane) currentScene.lookup("#mainContainer");
		// Activando inputs
		mainContainer.lookupAll(".text-field:disabled").forEach(node -> {
			node.setDisable(false);
		});

		// Activando combos
		mainContainer.lookupAll(".combo-box:disabled").forEach(node -> {
			node.setDisable(false);
		});

		// Activando botones
		mainContainer.lookupAll(".button:disabled").forEach(node -> {
			node.setDisable(false);
		});

		txtNumEmpleado.setDisable(true);
	}

	private Empleado getEmpleado() {
		Empleado empleado = new Empleado();
		Persona persona = new Persona();
		Usuario usuario = new Usuario();

		persona.setNombre(txtNombre.getText());
		persona.setApellidoP(txtApellido1.getText());
		persona.setApellidoM(txtApellido2.getText());
		persona.setGenero(cmbGenero.getValue().getValor());
		persona.setRfc(txtRfc.getText());
		persona.setDomicilio(txtDomicilio.getText());
		persona.setTelefono(txtTelefono.getText());

		usuario.setNombreUsu(txtUsuario.getText());
		usuario.setContrasenia(txtPassword.getText());
		usuario.setRol(txtPuesto.getText());

		empleado.setEstatus(1);
		empleado.setPuesto(txtPuesto.getText());
		empleado.setPersona(persona);
		empleado.setFoto(Utils.encodeImage(selectedImg.getImage()));
		empleado.setUsuario(usuario);

		return empleado;
	}
}
