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
import javafx.scene.layout.HBox;
import model.Empleado;
import model.Item;
import rest.Rest;
import utils.Utils;

public class EmpleadosController implements Initializable {

	@FXML
	private JFXComboBox<Item> cmbBusqueda;

	@FXML
	private JFXTextField txtBusqueda;

	@FXML
	private JFXComboBox<Item> cmbEstatus;

	@FXML
	private TableView<Empleado> tablaEmpleados;

	@FXML
	private TableColumn<Empleado, Integer> columnId;

	@FXML
	private TableColumn<Empleado, String> columnNombre;

	@FXML
	private TableColumn<Empleado, String> columnApellido1;

	@FXML
	private TableColumn<Empleado, String> columnApellido2;

	@FXML
	private TableColumn<Empleado, String> columnGenero;

	@FXML
	private TableColumn<Empleado, String> columnRFC;

	@FXML
	private TableColumn<Empleado, String> columnTelefono;

	@FXML
	private TableColumn<Empleado, String> columnDomicilio;

	@FXML
	private TableColumn<Empleado, String> columnPuesto;

	@FXML
	private TableColumn<Empleado, String> columnFoto;

	@FXML
	private TableColumn<Empleado, String> columnUsuario;

	@FXML
	private TableColumn<Empleado, HBox> columnAcciones;

	private FXMLLoader loader;
	private EmpleadosFormController formController;

	private final static Item filtrosBusqueda[] = {new Item("ID", "idEmpleado"), new Item("Nombre", "nombre"),
		new Item("Apellido paterno", "apellidoPaterno"), new Item("Apellido materno", "apellidoMaterno"),
		new Item("Género", "genero"), new Item("Domicilio", "domicilio"), new Item("Telefono", "telefono"),
		new Item("RFC", "rfc"), new Item("Número de empleado", "numeroEmpleado"), new Item("Puesto", "puesto"),
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
		columnPuesto.setCellValueFactory(new PropertyValueFactory<>("puesto"));
		columnFoto.setCellValueFactory(new PropertyValueFactory<>("foto"));
		columnUsuario.setCellValueFactory(new PropertyValueFactory<>("nombreUsu"));
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
			Empleado empleados[] = Rest.obtenerRegistros("employee", estatus, Empleado[].class);
			tablaEmpleados.getItems().setAll(empleados);
			ponerAcciones();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	private void ponerAcciones() {
		tablaEmpleados.getItems().forEach(e -> {
			// Modificar
			((JFXButton) e.getAcciones().getChildren().get(0)).setOnAction(event -> {
				modificar(e, event);
			});

			// Eliminar
			((JFXButton) e.getAcciones().getChildren().get(1)).setOnAction(event -> {
				eliminar(String.valueOf(e.getId()), event);
			});
		});
	}

	private void buscar() {
		try {
			String estatus = cmbEstatus.getValue().getValor();
			String filtroBusqueda = cmbBusqueda.getValue().getValor();
			String consulta = txtBusqueda.getText().trim();
			// 	nombre LIKE "%luis%" 
			// %% = % en String
			// %s = placeholder donde va a ir un string que le pasemos como parametros
			// %%25 = % en un URL
			String filtro = String.format("%s LIKE \"%%25%s%%25\"", filtroBusqueda, consulta);
			Empleado empleados[] = Rest.buscar("employee", estatus, filtro, Empleado[].class);
			tablaEmpleados.getItems().setAll(empleados);
			ponerAcciones();
		} catch (Exception e) {
			System.out.println(e.toString());
		}

	}

	@FXML
	void agregar(ActionEvent event) throws IOException {
		Scene currentScene = Utils.getCurrentScene(event);
		ScrollPane mainContainer = (ScrollPane) currentScene.lookup("#mainContainer");
		Node nodo = FXMLLoader.load(getClass().getResource("EmpleadosForm.fxml"));
		mainContainer.setContent(nodo);
	}

	private void modificar(Empleado empleado, ActionEvent e) {
		try {
			Scene currentScene = Utils.getCurrentScene(e);
			ScrollPane mainContainer = (ScrollPane) currentScene.lookup("#mainContainer");
			loader = new FXMLLoader(getClass().getResource("EmpleadosForm.fxml"));
			Node nodo;
			nodo = loader.load();
			formController = loader.getController();
			formController.setEmpleado(empleado);
			mainContainer.setContent(nodo);
		} catch (IOException ex) {
			Logger.getLogger(EmpleadosController.class.getName()).log(Level.SEVERE, null, ex);
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
			Rest.eliminar("employee", id);
			llenarTabla();
			WaitAlert waitAlert = new WaitAlert(AlertIcon.SUCCESS, Utils.getCurrentWindow(e));
			waitAlert.setTitle("Registro eliminado");
			waitAlert.setTextContent("El registro ah sido eliminado correctamente");
			waitAlert.showAndWaitFor(3);
		});

		alert.showAndWait();
	}

}
