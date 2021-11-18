package model;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;

public class Sucursal {

	private int id;
	private String nombre;
	private String domicilio;
	private double latitud;
	private double longitud;
	private int estatus;
	private transient HBox acciones;

	public Sucursal() {
	}

	public Sucursal(int id, String nombre, String domicilio, double latitud, double longitud, int estatus) {
		this.id = id;
		this.nombre = nombre;
		this.domicilio = domicilio;
		this.latitud = latitud;
		this.longitud = longitud;
		this.estatus = estatus;
	}

	public Sucursal(String nombre, String domicilio, double latitud, double longitud, int estatus) {
		this.nombre = nombre;
		this.domicilio = domicilio;
		this.latitud = latitud;
		this.longitud = longitud;
		this.estatus = estatus;
	}

	public double getLongitud() {
		return longitud;
	}

	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getEstatus() {
		return estatus;
	}

	public void setEstatus(int estatus) {
		this.estatus = estatus;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public double getLatitud() {
		return latitud;
	}

	public void setLatitud(double latitud) {
		this.latitud = latitud;
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
		return "Sucursal{" + "id=" + id + ", estatus=" + estatus + ", nombre=" + nombre + ", domicilio=" + domicilio + ", latitud=" + latitud + ", longitud=" + longitud + '}';
	}
}
