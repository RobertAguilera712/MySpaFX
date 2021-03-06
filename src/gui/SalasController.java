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
import model.Item;
import model.Sala;
import rest.Rest;
import utils.Utils;

public class SalasController implements Initializable {

	   @FXML
    private JFXComboBox<Item> cmbBusqueda;

    @FXML
    private JFXTextField txtBusqueda;

    @FXML
    private JFXComboBox<Item> cmbEstatus;

    @FXML
    private TableView<Sala> tablaSalas;

    @FXML
    private TableColumn<?, ?> columnId;

    @FXML
    private TableColumn<?, ?> columnNombre;

    @FXML
    private TableColumn<?, ?> columnDescripcion;

    @FXML
    private TableColumn<?, ?> columnFoto;

    @FXML
    private TableColumn<?, ?> columnSucursal;

    @FXML
    private TableColumn<?, ?> columnEstatus;

    @FXML
    private TableColumn<?, ?> columnAcciones;
	

	private FXMLLoader loader;
	private SalasFormController formController;

	private final static Item filtrosBusqueda[] = {new Item("ID", "idSala"), new Item("Nombre", "nombreSala"), new Item("Descripcion", "descripcion"), new Item("Nombre de sucursal", "nombre")};

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		columnNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		columnDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
		columnFoto.setCellValueFactory(new PropertyValueFactory<>("foto"));
		columnSucursal.setCellValueFactory(new PropertyValueFactory<>("sucursalNombre"));
		columnEstatus.setCellValueFactory(new PropertyValueFactory<>("estatus"));
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

	private void llenarTabla(){
		try {
			String estatus = cmbEstatus.getValue().getValor();
			Sala salas[] = Rest.obtenerRegistros("room", estatus, Sala[].class);
			tablaSalas.getItems().setAll(salas);
			ponerAcciones();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	private void buscar() {
		try {
			String estatus = cmbEstatus.getValue().getValor();
			String filtroBusqueda = cmbBusqueda.getValue().getValor();
			String consulta = txtBusqueda.getText().trim();
			String filtro = String.format("%s LIKE \"%%25%s%%25\"", filtroBusqueda, consulta);
			Sala salas[] = Rest.buscar("room", estatus, filtro, Sala[].class);
			tablaSalas.getItems().setAll(salas);
			ponerAcciones();
		} catch (Exception e) {
			System.out.println(e.toString());
		}

	}

	private void ponerAcciones() {
		tablaSalas.getItems().forEach(s -> {
//			 Modificar
			((JFXButton) s.getAcciones().getChildren().get(0)).setOnAction(event -> {
				modificar(s, event);
			});

			// Eliminar
			((JFXButton) s.getAcciones().getChildren().get(1)).setOnAction(event -> {
				eliminar(String.valueOf(s.getId()), event);
			});
		});
	}

	private void modificar(Sala sala, ActionEvent e) {
		try {
			Scene currentScene = Utils.getCurrentScene(e);
			ScrollPane mainContainer = (ScrollPane) currentScene.lookup("#mainContainer");
			loader = new FXMLLoader(getClass().getResource("SalasForm.fxml"));
			Node nodo;
			nodo = loader.load();
			formController = loader.getController();
			formController.setSala(sala);
			mainContainer.setContent(nodo);
		} catch (IOException ex) {
			Logger.getLogger(EmpleadosController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private void eliminar(String id, ActionEvent e) {
		ConfirmationAlert alert = new ConfirmationAlert(AlertIcon.WARNING, Utils.getCurrentWindow(e));
		alert.setTitle("??Estas seguro de eliminar el registro?");
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
			Rest.eliminar("room", id);
			llenarTabla();
			WaitAlert waitAlert = new WaitAlert(AlertIcon.SUCCESS, Utils.getCurrentWindow(e));
			waitAlert.setTitle("Registro eliminado");
			waitAlert.setTextContent("El registro ah sido eliminado correctamente");
			waitAlert.showAndWaitFor(3);
		});

		alert.showAndWait();
	}

    @FXML
    void agregar(ActionEvent event) throws IOException {
		Scene currentScene = Utils.getCurrentScene(event);
		ScrollPane mainContainer = (ScrollPane) currentScene.lookup("#mainContainer");
		Node nodo = FXMLLoader.load(getClass().getResource("SalasForm.fxml"));
		mainContainer.setContent(nodo);
    }
	
}
