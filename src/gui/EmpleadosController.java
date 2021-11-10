package gui;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
	private TableColumn<?, ?> columnRFC;

	@FXML
	private TableColumn<?, ?> columnTelefono;

	@FXML
	private TableColumn<?, ?> columnDomicilio;

	@FXML
	private TableColumn<?, ?> columnPuesto;

	@FXML
	private TableColumn<?, ?> columnFoto;

	@FXML
	private TableColumn<?, ?> columnUsuario;

	@FXML
	private TableColumn<?, ?> columnAcciones;

	private static Item filtrosBusqueda[] = {new Item("ID", "idEmpleado"), new Item("Nombre", "nombre"),
		new Item("Apellido paterno", "apellidoPaterno"), new Item("Apellido materno", "apellidoMaterno"),
		new Item("Género", "genero"), new Item("Domicilio", "domicilio"), new Item("Telefono", "telefono"),
		new Item("RFC", "rfc"), new Item("Número de empleado", "numeroEmpleado"), new Item("Puesto", "puesto"),
		new Item("Nombre de usuario", "nombreUsuario")};

	private static Item filtrosEstatus[] = {new Item("Activos", "1"), new Item("Inactivos", "0")};

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
		cmbEstatus.getItems().setAll(filtrosEstatus);

		cmbBusqueda.setValue(filtrosBusqueda[0]);
		cmbEstatus.setValue(filtrosEstatus[0]);

		cmbEstatus.setOnAction(e -> {
			if (txtBusqueda.getText().trim().length() > 0){
				buscar();
			}else{
				llenarTabla();
			}
		});

		cmbBusqueda.setOnAction(e -> {
			buscar();
		});

		txtBusqueda.setOnKeyReleased(e -> {
			if (e.getText().length() > 0){
				buscar();
			}
		});

		llenarTabla();

	}

	private void llenarTabla() {
		try {
			String estatus = cmbEstatus.getValue().getValue();
			Empleado empleados[] = Rest.obtenerRegistros("employee", estatus, Empleado[].class);
			tablaEmpleados.getItems().setAll(empleados);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	private void buscar() {
		try {
			String estatus = cmbEstatus.getValue().getValue();
			String filtroBusqueda = cmbBusqueda.getValue().getValue();
			String consulta = txtBusqueda.getText().trim();
			String filtro = String.format("%s LIKE \"%%25%s%%25\"", filtroBusqueda, consulta);
			Empleado empleados[] = Rest.buscar("employee", estatus, filtro, Empleado[].class);
			tablaEmpleados.getItems().setAll(empleados);
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

}
