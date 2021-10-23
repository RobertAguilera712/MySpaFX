package model;
public class Sucursal {
    private int id;
    private int estatus;
    private String nombre;
    private String domicilio;
    private double latitud;
    private double longitud;

    public Sucursal() {
    }

    public Sucursal(int id, int estatus, String nombre, String domicilio, double latitud, double longitud) {
        this.id = id;
        this.estatus = estatus;
        this.nombre = nombre;
        this.domicilio = domicilio;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public Sucursal(int estatus, String nombre, String domicilio, double latitud, double longitud) {
        this.estatus = estatus;
        this.nombre = nombre;
        this.domicilio = domicilio;
        this.latitud = latitud;
        this.longitud = longitud;
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

    @Override
    public String toString() {
        return "Sucursal{" + "id=" + id + ", estatus=" + estatus + ", nombre=" + nombre + ", domicilio=" + domicilio + ", latitud=" + latitud + ", longitud=" + longitud + '}';
    }
}
