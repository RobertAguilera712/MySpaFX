package gui;

import com.google.gson.Gson;
import com.jfoenix.controls.JFXComboBox;
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
import javafx.scene.image.ImageView;
import model.Item;
import model.Sala;
import model.Sucursal;
import rest.Rest;
import utils.Checar;
import utils.Utils;

public class SalasFormController implements Initializable {

	@FXML
	private JFXTextField txtNombre;

	@FXML
	private JFXTextField txtDescripcion;

	@FXML
	private JFXComboBox<Item> cmbSucursal;

	@FXML
	private Label lbImg;

	@FXML
	private ImageView selectedImg;

	private Gson gson;
	private Sala temp;
	private static final Item defaultItem = new Item("Seleccionar sucursal", "");

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		gson = new Gson();
		temp = null;
		cmbSucursal.getItems().add(defaultItem);
		Sucursal sucursalesTemp[] = Rest.obtenerRegistros("branch", "1", Sucursal[].class);
		for (Sucursal s : sucursalesTemp) {
			cmbSucursal.getItems().add(new Item(s.getNombre(), String.valueOf(s.getId())));
		}
		cmbSucursal.setValue(defaultItem);
	}

	@FXML
	void guardar(ActionEvent event) {
		String mensajeError = Checar.checarInputsTexto(txtNombre, txtDescripcion) + Checar.checarCombos(cmbSucursal);

		if (mensajeError.isEmpty()) {
			ConfirmationAlert alerta = new ConfirmationAlert(AlertIcon.QUESTION, Utils.getCurrentWindow(event));
			if (temp == null) {
				alerta.setTitle("¿Quieres guardar el nuevo registro?");
				alerta.setTextContent("");
				alerta.setConfirmationButtonText("Si, guardarlo");
				alerta.setCancellationButtonText("No, Cancelar");

				alerta.setConfirmationButtonAction(e -> {
					Sala nuevaSala = getSala();
					Rest.agregarPost("room", gson.toJson(nuevaSala));
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
					Sala nuevaSala = getSala();
					nuevaSala.setId(temp.getId());

					String json = gson.toJson(nuevaSala);
					System.out.println(json);

					Rest.modificarPost("room", json);
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
			Node nodo = FXMLLoader.load((getClass().getResource("Salas.fxml")));
			mainContainer.setContent(nodo);
		} catch (IOException ex) {
			Logger.getLogger(SalasFormController.class.getName()).log(Level.SEVERE, null, ex);
		}
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

	private void limpiarForm() {
		txtNombre.setText("");
		txtDescripcion.setText("");
		cmbSucursal.setValue(defaultItem);
		selectedImg.setImage(null);
		lbImg.setText("No has seleccionando\n"
				+ "ninguna imagen");
	}

	public void setSala(Sala s) {
		this.temp = s;
		txtNombre.setText(s.getNombre());
		txtDescripcion.setText(s.getDescripcion());

		if (!s.getFotoString().isEmpty()) {
			selectedImg.setImage(Utils.decodeImage(s.getFotoString()));
			lbImg.setText("");
		}


		for (Item item : cmbSucursal.getItems()) {
			if (item.getTexto().equalsIgnoreCase(s.getSucursalNombre())) {
				cmbSucursal.setValue(item);
				break;
			}
		}
	}

	private Sala getSala() {
		Sala sala = new Sala();
		Sucursal sucursal = new Sucursal();

		sucursal.setId(Integer.parseInt(cmbSucursal.getValue().getValor()));

		sala.setNombre(txtNombre.getText());
		sala.setDescripcion(txtDescripcion.getText());
		sala.setFoto(Utils.encodeImage(selectedImg.getImage()));
		sala.setSucursal(sucursal);
		sala.setEstatus(1);

		return sala;
	}

}
