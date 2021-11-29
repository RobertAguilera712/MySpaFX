package model;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
	private transient HBox acciones;

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

	public String getNombre() {
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

	public String getGenero() {
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

	public String getFotoString() {
		return foto;
	}

	public ImageView getFoto(){
		if (this.foto.isEmpty()){
			return null;
		}

		ImageView img =new ImageView(Utils.decodeImage(this.foto));
		img.setFitWidth(50);
		img.setFitHeight(50);
		img.setPreserveRatio(true);

		return img;
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

	private void setAcciones() {
		try {
			this.acciones = (HBox) FXMLLoader.load(getClass().getResource("Acciones.fxml"));
		} catch (IOException ex) {
			Logger.getLogger(Empleado.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public HBox getAcciones() {
		if (this.acciones == null){
			setAcciones();
		}
		return this.acciones;
	}

	@Override
	public String toString() {
		return "Empleado{" + "id=" + id + ", numeroEmpleado=" + numeroEmpleado + ", puesto=" + puesto + ", rutaFoto=" + rutaFoto + ", foto=" + foto + ", persona=" + persona + ", usuario=" + usuario + ", estatus=" + estatus + '}';
	}

}
