package model;

public class Usuario {
    private int id;
    private String nombreUsu, contrasenia, rol, token;

    public Usuario() {
    }

    public Usuario(int id, String nombreUsu, String contrasenia, String rol, String token) {
        this.id = id;
        this.nombreUsu = nombreUsu;
        this.contrasenia = contrasenia;
        this.rol = rol;
        this.token = token;
    }

    public Usuario(String nombreUsu, String contrasenia, String rol, String token) {
        this.nombreUsu = nombreUsu;
        this.contrasenia = contrasenia;
        this.rol = rol;
        this.token = token;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreUsu() {
        return nombreUsu;
    }

    public void setNombreUsu(String nombreUsu) {
        this.nombreUsu = nombreUsu;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", nombreUsu=" + nombreUsu + ", contrasenia=" + contrasenia + ", rol=" + rol + ", token=" + token + '}';
    }
    
    
}
