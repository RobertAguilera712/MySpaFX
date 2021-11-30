package gui;

import com.google.gson.Gson;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXTextField;
import gui.Alerts.AlertIcon;
import gui.Alerts.ConfirmationAlert;
import gui.Alerts.OkAlert;
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
import model.Producto;
import rest.Rest;
import utils.Checar;
import utils.Utils;


public class ProductosFormController implements Initializable {
    
    @FXML
    private JFXTextField txtNombre;

    @FXML
    private JFXTextField txtMarca;

    @FXML
    private JFXTextField txtPrecioUso;
    
    private Gson gson;
    private Producto temp;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gson = new Gson();
        temp = null;
    }

    @FXML
    void guardar(ActionEvent event) {
        String mensajeError = Checar.checarInputsTexto(txtNombre, txtMarca, txtPrecioUso);

        if (mensajeError.isEmpty()) {
            ConfirmationAlert alerta = new ConfirmationAlert(AlertIcon.QUESTION, Utils.getCurrentWindow(event));
            if (temp == null) {
                alerta.setTitle("¿Quieres guardar el nuevo registro?");
                alerta.setTextContent("");
                alerta.setConfirmationButtonText("Si, guardarlo");
                alerta.setCancellationButtonText("No, Cancelar");

                alerta.setConfirmationButtonAction(e -> {
                    Producto nuevoProducto = getProducto();
                    Rest.agregarGet("product", gson.toJson(nuevoProducto));
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
                    Producto nuevoProducto = getProducto();
                    nuevoProducto.setId(temp.getId());

                    String json = gson.toJson(nuevoProducto);

                    Rest.modificarGet("product", json);
                    WaitAlert waitAlert = new WaitAlert(AlertIcon.SUCCESS, Utils.getCurrentWindow(event));
                    waitAlert.setTitle("Registro modificado correctamente");
                    waitAlert.setTextContent("El registro se modificó correctamente");
                    waitAlert.showAndWaitFor(2);
                    regresar(event);
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
    void regresar(ActionEvent event) {
        try {
            Scene currentScene = Utils.getCurrentScene(event);
            ScrollPane mainContainer = (ScrollPane) currentScene.lookup("#mainContainer");
            Node nodo = FXMLLoader.load((getClass().getResource("Productos.fxml")));
            mainContainer.setContent(nodo);
        } catch (IOException ex) {
            Logger.getLogger(ProductosFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void limpiarForm() {
        txtNombre.setText("");
        txtMarca.setText("");
        txtPrecioUso.setText("");
    }

    public void setProducto(Producto p) {
        this.temp = p;
        txtNombre.setText(p.getNombre());
        txtMarca.setText(p.getMarca());
        txtPrecioUso.setText(String.valueOf(p.getPrecioUso()));
    }

    private Producto getProducto() {
        Producto producto = new Producto();

        producto.setNombre(txtNombre.getText());
        producto.setMarca(txtMarca.getText());
        producto.setPrecioUso(Float.parseFloat(txtPrecioUso.getText()));
        producto.setEstatus(1);

        return producto;
    }
}
