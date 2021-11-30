package gui;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import gui.Alerts.AlertIcon;
import gui.Alerts.ConfirmationAlert;
import gui.Alerts.WaitAlert;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Item;
import utils.Utils;
import model.Producto;
import rest.Rest;

public class ProductosController implements Initializable {

    @FXML
    private JFXComboBox<Item> cmbBusqueda;

    @FXML
    private JFXTextField txtBusqueda;

    @FXML
    private JFXComboBox<Item> cmbEstatus;

    @FXML
    private TableView<Producto> tablaProductos;

    @FXML
    private TableColumn<?, ?> columnId;

    @FXML
    private TableColumn<?, ?> columnNombre;

    @FXML
    private TableColumn<?, ?> columnMarca;

    @FXML
    private TableColumn<?, ?> columnPrecioUso;

    @FXML
    private TableColumn<?, ?> columnAcciones;

    private FXMLLoader loader;
    private ProductosFormController formController;
    
    private final static Item filtrosBusqueda[] = {new Item("ID", "idProducto"), new Item("Nombre", "nombre"), new Item("Marca", "marca"), new Item("Precio", "precioUso")};

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columnMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        columnPrecioUso.setCellValueFactory(new PropertyValueFactory<>("precioUso"));
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
            Producto productos[] = Rest.obtenerRegistros("product", estatus, Producto[].class);
            tablaProductos.getItems().setAll(productos);
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
            Producto productos[] = Rest.buscar("product", estatus, filtro, Producto[].class);
            tablaProductos.getItems().setAll(productos);
            ponerAcciones();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    
    private void ponerAcciones() {
        tablaProductos.getItems().forEach(e -> {
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
    
    private void modificar(Producto producto, ActionEvent e) {
        try {
            Scene currentScene = Utils.getCurrentScene(e);
            ScrollPane mainContainer = (ScrollPane) currentScene.lookup("#mainContainer");
            loader = new FXMLLoader(getClass().getResource("ProductosForm.fxml"));
            Node nodo;
            nodo = loader.load();
            formController = loader.getController();
            formController.setProducto(producto);
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
            Rest.eliminar("product", id);
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
        Node nodo = FXMLLoader.load(getClass().getResource("ProductosForm.fxml"));
        mainContainer.setContent(nodo);
    }

}
