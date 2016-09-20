package models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Usuario implements Serializable {
	@Id
    private String email;
	private String nombre;
    private String password;
	private String direccion;
	private String telefono;
    private Boolean administrador;

    public Usuario() { administrador = false; }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public Boolean getAdministrador() {
        return administrador;
    }
    public void setAdministrador(Boolean administrador) {
        this.administrador = administrador;
    }
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String name) {
		this.nombre = name;
	}
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
