package model;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;

public class Cliente {
    private int id, estatus;
    private String numeroUni, correo;
    private Persona persona;
    private Usuario usuario;
	private transient HBox acciones;

    public Cliente() {
    }

    public Cliente(int id, int estatus, String numeroUni, String correo, Persona persona, Usuario usuario) {
        this.id = id;
        this.estatus = estatus;
        this.numeroUni = numeroUni;
        this.correo = correo;
        this.persona = persona;
        this.usuario = usuario;
    }

    public Cliente(int estatus, String numeroUni, String correo, Persona persona, Usuario usuario) {
        this.estatus = estatus;
        this.numeroUni = numeroUni;
        this.correo = correo;
        this.persona = persona;
        this.usuario = usuario;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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
	
	public String getNombreUsu() {
		return this.usuario.getNombreUsu();
	}

	public String getContrasenia() {
		return this.usuario.getContrasenia();
	}

    public String getNumeroUni() {
        return numeroUni;
    }

    public void setNumeroUni(String numeroUni) {
        this.numeroUni = numeroUni;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
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
        return "Cliente{" + "id=" + id + ", estatus=" + estatus + ", numeroUni=" + numeroUni + ", correo=" + correo + ", persona=" + persona + ", usuario=" + usuario + '}';
    }
}
