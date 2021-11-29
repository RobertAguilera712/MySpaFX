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
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import model.Cliente;
import model.Item;
import model.Persona;
import model.Usuario;
import rest.Rest;
import utils.Checar;
import utils.Utils;

public class ClientesFormController implements Initializable {

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
    private JFXTextField txtCorreo;

    @FXML
    private JFXTextField txtUsuario;

    @FXML
    private JFXPasswordField txtPassword;


	private static final Item generos[] = {new Item("Selecciona el género", ""), new Item("Masculino", "M"), new Item("Femenino", "F"), new Item("Otro", "O")};

	private Gson gson;
	private Cliente temp;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		gson = new Gson();
		temp = null;
		cmbGenero.getItems().setAll(generos);
		cmbGenero.setValue(generos[0]);
	}

	@FXML
	void guardar(ActionEvent event) {
		String mensajeError = Checar.checarInputsTexto(txtNombre, txtApellido1, txtApellido2, txtDomicilio,
				txtPassword, txtCorreo, txtRfc, txtTelefono, txtUsuario)
				+ Checar.checarCombos(cmbGenero);

		if (mensajeError.isEmpty()) {
			ConfirmationAlert alerta = new ConfirmationAlert(AlertIcon.QUESTION, Utils.getCurrentWindow(event));
			if (temp == null) {
				alerta.setTitle("¿Quieres guardar el nuevo registro?");
				alerta.setTextContent("");
				alerta.setConfirmationButtonText("Si, guardarlo");
				alerta.setCancellationButtonText("No, Cancelar");

				alerta.setConfirmationButtonAction(e -> {
					Cliente nuevoCliente = getCliente();
					Rest.agregarPost("cliente", gson.toJson(nuevoCliente));
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
					Cliente nuevoCliente = getCliente();
					nuevoCliente.setId(temp.getId());
					nuevoCliente.getPersona().setId(temp.getPersona().getId());
					nuevoCliente.getUsuario().setId(temp.getUsuario().getId());

					String json = gson.toJson(nuevoCliente);

					System.out.println(json);

					Rest.modificarPost("cliente", json);
					WaitAlert waitAlert = new WaitAlert(AlertIcon.SUCCESS, Utils.getCurrentWindow(event));
					waitAlert.setTitle("Registro modificado correctamente");
					waitAlert.setTextContent("El registro se modificó correctamente");
					waitAlert.showAndWaitFor(2);
					try {
						regresar(event);
					} catch (IOException ex) {
						Logger.getLogger(ClientesFormController.class.getName()).log(Level.SEVERE, null, ex);
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
		Node nodo = FXMLLoader.load((getClass().getResource("Clientes.fxml")));
		mainContainer.setContent(nodo);
	}


	private void limpiarForm() {
		txtApellido1.setText("");
		txtApellido2.setText("");
		txtDomicilio.setText("");
		txtNombre.setText("");
		txtPassword.setText("");
		txtCorreo.setText("");
		txtRfc.setText("");
		txtTelefono.setText("");
		txtUsuario.setText("");
		cmbGenero.setValue(generos[0]);
	}

	public void setCliente(Cliente c) {
		this.temp = c;
		txtApellido1.setText(c.getApellidoP());
		txtApellido2.setText(c.getApellidoM());
		txtDomicilio.setText(c.getDomicilio());
		txtNombre.setText(c.getNombre());
		txtPassword.setText(c.getContrasenia());
		txtCorreo.setText(c.getCorreo());
		txtRfc.setText(c.getRfc());
		txtTelefono.setText(c.getTelefono());
		txtUsuario.setText(c.getNombreUsu());

		for (Item item : generos) {
			if (item.getValor().equalsIgnoreCase(c.getGenero())) {
				cmbGenero.setValue(item);
				break;
			}
		}
	}

	public void setTitulo(String titulo) {
		txtTitulo.setText(titulo);
	}

	private Cliente getCliente() {
		Cliente cliente = new Cliente();
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
		usuario.setRol("Cliente");

		cliente.setCorreo(txtCorreo.getText());
		cliente.setEstatus(1);
		cliente.setPersona(persona);
		cliente.setUsuario(usuario);

		return cliente;
	}
}
