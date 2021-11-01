package gui;

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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Empleado;
import model.Persona;
import model.Producto;
import model.Usuario;
import utils.Utils;

public class EmpleadosController implements Initializable {
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
    private TableColumn<?, ?> columnRutaImg;

    @FXML
    private TableColumn<?, ?> columnUsuario;

    @FXML
    private TableColumn<?, ?> columnPassword;

    @FXML
    private TableColumn<?, ?> columnAcciones;

	private FXMLLoader loader;
	private EmpleadosFormController formController;
	
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
		columnRutaImg.setCellValueFactory(new PropertyValueFactory<>("rutaFoto"));
		columnUsuario.setCellValueFactory(new PropertyValueFactory<>("nombreUsu"));
		columnPassword.setCellValueFactory(new PropertyValueFactory<>("contrasenia"));
		columnAcciones.setCellValueFactory(new PropertyValueFactory<>("acciones"));

		tablaEmpleados.getItems().add(new Empleado(1, "e1234567", "Jefe", "./foto.png", new Persona(1, "Roberto", "Aguilera", "Alcantar", "Calle false 123", "4791481494", "AUAR01207AN0", 'H'), new Usuario(1, "kasparov", "12345", "administrador", "777"), 1));
	}

    @FXML
    void agregar(ActionEvent event) throws IOException {
		Scene currentScene = Utils.getCurrentScene(event);
		ScrollPane mainContainer = (ScrollPane) currentScene.lookup("#mainContainer");
		loader = new FXMLLoader(getClass().getResource("EmpleadosForm.fxml"));
		Node nodo =loader.load();
		formController = loader.getController();
		formController.setLista(tablaEmpleados.getItems());
		mainContainer.setContent(nodo);

    }

	public void setEmpleados(ObservableList<Empleado> empleados){
		tablaEmpleados.getItems().setAll(empleados);
	}

}
