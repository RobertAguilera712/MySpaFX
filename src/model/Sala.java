package model;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import utils.Utils;

public class Sala {
    private int id;
    private String nombre;
    private String descripcion;
    private String foto;
    private int estatus;
    private Sucursal sucursal;
	private transient HBox acciones;

    public Sala() {
    }

    public Sala(int id, String nombre, String descripcion, String rutaFoto, int estatus, Sucursal sucursal) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.foto = rutaFoto;
        this.estatus = estatus;
        this.sucursal = sucursal;
    }

    public Sala(String nombre, String descripcion, String rutaFoto, int estatus, Sucursal sucursal) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.foto = rutaFoto;
        this.estatus = estatus;
        this.sucursal = sucursal;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }
	
	public String getSucursalNombre(){
		return this.sucursal.getNombre();
	}

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    public int getEstatus() {
        return estatus;
    }

    public void setEstatus(int estatus) {
        this.estatus = estatus;
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
        return "Sala{" + "id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", rutaFoto=" + foto + ", estatus=" + estatus + ", sucursal=" + sucursal + '}';
    }
}
