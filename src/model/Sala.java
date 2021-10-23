package model;

public class Sala {
    private int id;
    private String nombre;
    private String descripcion;
    private String rutaFoto;
    private int estatus;
    private Sucursal sucursal;

    public Sala() {
    }

    public Sala(int id, String nombre, String descripcion, String rutaFoto, int estatus, Sucursal sucursal) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.rutaFoto = rutaFoto;
        this.estatus = estatus;
        this.sucursal = sucursal;
    }

    public Sala(String nombre, String descripcion, String rutaFoto, int estatus, Sucursal sucursal) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.rutaFoto = rutaFoto;
        this.estatus = estatus;
        this.sucursal = sucursal;
    }

    public Sucursal getSucursal() {
        return sucursal;
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

    public String getRutaFoto() {
        return rutaFoto;
    }

    public void setRutaFoto(String rutaFoto) {
        this.rutaFoto = rutaFoto;
    }

    public int getEstatus() {
        return estatus;
    }

    public void setEstatus(int estatus) {
        this.estatus = estatus;
    }

    @Override
    public String toString() {
        return "Sala{" + "id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", rutaFoto=" + rutaFoto + ", estatus=" + estatus + ", sucursal=" + sucursal + '}';
    }
}
