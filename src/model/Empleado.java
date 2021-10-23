package model;

public class Empleado {
	private int id;
    private String numeroEmpleado;
    private String puesto;
    private String rutaFoto;
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
    
}
