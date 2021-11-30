package model;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;

public class Producto {

    private int id;
    private int estatus;
    private String nombre;
    private String marca;
    private float precioUso;
    private transient HBox acciones;

    public Producto() {
    }

    public Producto(int id, int estatus, String nombre, String marca, float precioUso) {
        this.id = id;
        this.estatus = estatus;
        this.nombre = nombre;
        this.marca = marca;
        this.precioUso = precioUso;
    }

    public Producto(String nombre, String marca, float precioUso, int estatus) {
        this.nombre = nombre;
        this.marca = marca;
        this.precioUso = precioUso;
        this.estatus = estatus;
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

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public float getPrecioUso() {
        return precioUso;
    }

    public void setPrecioUso(float precioUso) {
        this.precioUso = precioUso;
    }

    private void setAcciones() {
        try {
            this.acciones = (HBox) FXMLLoader.load(getClass().getResource("Acciones.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(Empleado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public HBox getAcciones() {
        if (this.acciones == null) {
            setAcciones();
        }
        return this.acciones;
    }

    @Override
    public String toString() {
        return "Producto{" + "id=" + id + ", estatus=" + estatus + ", nombre=" + nombre + ", marca=" + marca + ", precioUso=" + precioUso + '}';
    }
}
