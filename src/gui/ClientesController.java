package gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import gui.Alerts.AlertIcon;
import gui.Alerts.ConfirmationAlert;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Cliente;
import model.Item;
import rest.Rest;
import utils.Utils;

public class ClientesController implements Initializable {

	 @FXML
    private JFXComboBox<Item> cmbBusqueda;

    @FXML
    private JFXTextField txtBusqueda;

    @FXML
    private JFXComboBox<Item> cmbEstatus;

    @FXML
    private TableView<Cliente> tablaClientes;

    @FXML
    private TableColumn<?, ?> columnId;

    @FXML
    private TableColumn<?, ?> columnNombre;

    @FXML
    private TableColumn<?, ?> columnApellido1;

    @FXML
    private TableColumn<?, ?> columnApellido2;

    @FXML
    private TableColumn<?, ?> columnGenero;

    @FXML
    private TableColumn<?, ?> columnDomicilio;

    @FXML
    private TableColumn<?, ?> columnTelefono;

    @FXML
    private TableColumn<?, ?> columnRFC;

    @FXML
    private TableColumn<?, ?> columnNumeroUni;

    @FXML
    private TableColumn<?, ?> columnCorreo;

    @FXML
    private TableColumn<?, ?> columnUsuario;

    @FXML
    private TableColumn<?, ?> columnEstatus;

    @FXML
    private TableColumn<?, ?> columnAcciones;

	private FXMLLoader loader;
	private ClientesFormController formController;

	private final static Item filtrosBusqueda[] = {new Item("ID", "idCliente"), new Item("Nombre", "nombre"),
		new Item("Apellido paterno", "apellidoPaterno"), new Item("Apellido materno", "apellidoMaterno"),
		new Item("Género", "genero"), new Item("Domicilio", "domicilio"), new Item("Telefono", "telefono"),
		new Item("RFC", "rfc"), new Item("Número único", "numeroUni"), new Item("Correo", "correo"),
		new Item("Nombre de usuario", "nombreUsuario")};


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		columnNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		columnApellido1.setCellValueFactory(new PropertyValueFactory<>("apellidoP"));
		columnApellido2.setCellValueFactory(new PropertyValueFactory<>("apellidoM"));
		columnGenero.setCellValueFactory(new PropertyValueFactory<>("genero"));
		columnDomicilio.setCellValueFactory(new PropertyValueFactory<>("domicilio"));
		columnTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
		columnRFC.setCellValueFactory(new PropertyValueFactory<>("rfc"));
		columnUsuario.setCellValueFactory(new PropertyValueFactory<>("nombreUsu"));
		columnEstatus.setCellValueFactory(new PropertyValueFactory<>("estatus"));
		columnCorreo.setCellValueFactory(new PropertyValueFactory<>("correo"));
		columnNumeroUni.setCellValueFactory(new PropertyValueFactory<>("numeroUni"));
		columnAcciones.setCellValueFactory(new PropertyValueFactory<>("acciones"));

		cmbBusqueda.getItems().setAll(filtrosBusqueda);
		cmbEstatus.getItems().setAll(Utils.filtrosEstatus);

		cmbBusqueda.setValue(filtrosBusqueda[0]);
		cmbEstatus.setValue(Utils.filtrosEstatus[0]);

		cmbEstatus.setOnAction(e -> {
			if (txtBusqueda.getText().trim().length() > 0) {
				buscar();
			} else {
				llenarTabla();
			}
		});

		cmbBusqueda.setOnAction(e -> {
			buscar();
		});

		txtBusqueda.setOnKeyReleased(e -> {
			if (e.getText().length() > 0) {
				buscar();
			}
		});

		llenarTabla();

	}

	private void llenarTabla() {
		try {
			String estatus = cmbEstatus.getValue().getValor();
			Cliente clientes[] = Rest.obtenerRegistros("cliente", estatus, Cliente[].class);
			tablaClientes.getItems().setAll(clientes);
			ponerAcciones();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	private void ponerAcciones() {
		tablaClientes.getItems().forEach(c -> {
			// Modificar
			((JFXButton) c.getAcciones().getChildren().get(0)).setOnAction(event -> {
				modificar(c, event);
			});

			// Eliminar
			((JFXButton) c.getAcciones().getChildren().get(1)).setOnAction(event -> {
				eliminar(String.valueOf(c.getId()), event);
			});
		});
	}

	private void buscar() {
		try {
			String estatus = cmbEstatus.getValue().getValor();
			String filtroBusqueda = cmbBusqueda.getValue().getValor();
			String consulta = txtBusqueda.getText().trim();
			// nombre LIKE "%luis%" 
			// %% = % en String
			// %s = placeholder donde va a ir un string que le pasemos como parametros
			// %%25 = % en un URL
			String filtro = String.format("%s LIKE \"%%25%s%%25\"", filtroBusqueda, consulta);
			Cliente clientes[] = Rest.buscar("cliente", estatus, filtro, Cliente[].class);
			tablaClientes.getItems().setAll(clientes);
			ponerAcciones();
		} catch (Exception e) {
			System.out.println(e.toString());
		}

	}

	@FXML
	void agregar(ActionEvent event) throws IOException {
		Scene currentScene = Utils.getCurrentScene(event);
		ScrollPane mainContainer = (ScrollPane) currentScene.lookup("#mainContainer");
		Node nodo = FXMLLoader.load(getClass().getResource("ClientesForm.fxml"));
		mainContainer.setContent(nodo);
	}

	private void modificar(Cliente cliente, ActionEvent e) {
		try {
			Scene currentScene = Utils.getCurrentScene(e);
			ScrollPane mainContainer = (ScrollPane) currentScene.lookup("#mainContainer");
			loader = new FXMLLoader(getClass().getResource("ClientesForm.fxml"));
			Node nodo;
			nodo = loader.load();
			formController = loader.getController();
			formController.setCliente(cliente);
			formController.setTitulo("Modificar cliente");
			mainContainer.setContent(nodo);
		} catch (IOException ex) {
			Logger.getLogger(ClientesController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private void eliminar(String id, ActionEvent e) {
		ConfirmationAlert alert = new ConfirmationAlert(AlertIcon.WARNING, Utils.getCurrentWindow(e));
		alert.setTitle("¿Estas seguro de eliminar el registro?");
		alert.setTextContent("");
		alert.setCancellationButtonText("No, cancelar");
		alert.setConfirmationButtonText("Si, eliminarlo");

		alert.setCancellationButtonAction(event -> {
			WaitAlert waitAlert = new WaitAlert(AlertIcon.ERROR, Utils.getCurrentWindow(e));
			waitAlert.setTitle("Cancelado");
			waitAlert.setTextContent("El registro no ha sido eliminado");
			waitAlert.showAndWaitFor(3);
		});

		alert.setConfirmationButtonAction(event -> {
			Rest.eliminar("cliente", id);
			llenarTabla();
			WaitAlert waitAlert = new WaitAlert(AlertIcon.SUCCESS, Utils.getCurrentWindow(e));
			waitAlert.setTitle("Registro eliminado");
			waitAlert.setTextContent("El registro ah sido eliminado correctamente");
			waitAlert.showAndWaitFor(3);
		});

		alert.showAndWait();
	}

}
