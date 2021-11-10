package model;

import gui.Alerts.AlertIcon;
import gui.Alerts.Btn;
import gui.Alerts.BtnType;
import gui.Alerts.ConfirmationAlert;
import gui.Alerts.WaitAlert;
import gui.EmpleadosFormController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import utils.Utils;

public class Empleado {
	private int id;
    private String numeroEmpleado;
    private String puesto;
    private String rutaFoto;
    private String foto;
	private Persona persona;
	private Usuario usuario;
    private int estatus;

	public Empleado() {
	}

	public Empleado(int id, String numeroEmpleado, String puesto, String rutaFoto, Persona persona, Usuario usuario, int estatus) {
		this.id = id;
		this.numeroEmpleado = numeroEmpleado;
		this.puesto = puesto;
		this.rutaFoto = rutaFoto;
		this.persona = persona;
		this.usuario = usuario;
		this.estatus = estatus;
	}

	public String getNombre(){
		return this.persona.getNombre();
	}


    public String getApellidoP() {
        return this.persona.getApellidoP();
    }


    public String getApellidoM() {
        return this.persona.getApellidoM();
    }

    public String getDomicilio() {
        return this.persona.getDomicilio();
    }

    public String getTelefono() {
        return this.persona.getTelefono();
    }

    public String getRfc() {
        return this.persona.getRfc();
    }

    public char getGenero() {
        return this.persona.getGenero();
    }

	public int getEstatus() {
		return estatus;
	}

	public void setEstatus(int estatus) {
		this.estatus = estatus;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNumeroEmpleado() {
		return numeroEmpleado;
	}

	public void setNumeroEmpleado(String numeroEmpleado) {
		this.numeroEmpleado = numeroEmpleado;
	}

	public String getPuesto() {
		return puesto;
	}

	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}

	public String getRutaFoto() {
		return rutaFoto;
	}

	public void setRutaFoto(String rutaFoto) {
		this.rutaFoto = rutaFoto;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

    public String getNombreUsu() {
        return this.usuario.getNombreUsu();
    }

    public String getContrasenia() {
        return this.usuario.getContrasenia();
    }

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public HBox getAcciones() {
		HBox btnContainer = new HBox(20);
		btnContainer.setAlignment(Pos.CENTER);
		Btn modifyButton = new Btn(BtnType.WARNING, "Modificar");
		Btn deleteButton = new Btn(BtnType.DANGER, "Eliminar");

		modifyButton.setOnAction(e -> {
			try {
				Scene currentScene = Utils.getCurrentScene(e);
				TableView tabla = (TableView) currentScene.lookup("#tablaEmpleados");
				ScrollPane mainContainer = (ScrollPane) currentScene.lookup("#mainContainer");
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/EmpleadosForm.fxml"));
				Node nodo = loader.load();
				EmpleadosFormController formController = loader.getController();
//				formController.setEmpleado(this);
//				formController.setLista(tabla.getItems());
				mainContainer.setContent(nodo);
			} catch (IOException ex) {
				Logger.getLogger(Producto.class.getName()).log(Level.SEVERE, null, ex);
			}
		});

		deleteButton.setOnAction(e ->{
			ConfirmationAlert alert = new ConfirmationAlert(AlertIcon.WARNING, Utils.getCurrentWindow(e));
			alert.setTitle("¿Estas seguro de eliminar el registro?");
			alert.setTextContent("");
			alert.setCancellationButtonText("No, cancelar");
			alert.setConfirmationButtonText("Si, eliminarlo");

			alert.setCancellationButtonAction(event ->{
				WaitAlert waitAlert = new WaitAlert(AlertIcon.ERROR, Utils.getCurrentWindow(e));
				waitAlert.setTitle("Cancelado");
				waitAlert.setTextContent("El registro no ha sido eliminado");
				waitAlert.showAndWaitFor(3);
			});

			alert.setConfirmationButtonAction(event ->{
				Scene currentScene = Utils.getCurrentScene(e);
				TableView tabla = (TableView)currentScene.lookup("#tablaEmpleados");
				tabla.getItems().remove(this);
				WaitAlert waitAlert = new WaitAlert(AlertIcon.SUCCESS, Utils.getCurrentWindow(e));
				waitAlert.setTitle("Registro eliminado");
				waitAlert.setTextContent("El registro ah sido eliminado correctamente");
				waitAlert.showAndWaitFor(3);
			});

			alert.showAndWait();

		});

		btnContainer.getChildren().setAll(modifyButton, deleteButton);

		return btnContainer;
	}
    
}
