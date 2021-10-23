package model;

public class Producto {

	private int id;
	private Estatus estatus;
	String nombre, marca;
	float precioUso;

	public Producto() {
	}

	public Producto(int id, Estatus estatus, String nombre, String marca, float precioUso) {
		this.id = id;
		this.estatus = estatus;
		this.nombre = nombre;
		this.marca = marca;
		this.precioUso = precioUso;
	}

	public Producto(Estatus estatus, String nombre, String marca, float precioUso) {
		this.estatus = estatus;
		this.nombre = nombre;
		this.marca = marca;
		this.precioUso = precioUso;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getValorEstatus(){
		return this.estatus.getValor();
	}

	public String getEstatus() {
		return estatus.toString();
	}

	public void setEstatus(Estatus estatus) {
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

	@Override
	public String toString() {
		return "Producto{" + "id=" + id + ", estatus=" + estatus + ", nombre=" + nombre + ", marca=" + marca + ", precioUso=" + precioUso + '}';
	}
}
