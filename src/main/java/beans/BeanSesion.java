package beans;

import models.Producto;
import models.Usuario;
import wrappers.GestorDetalleVentas;
import wrappers.GestorProductos;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * Created by forte on 28/09/16.
 */
@ManagedBean
@SessionScoped
public class BeanSesion {
    Usuario usuario;

    public BeanSesion() { }

    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
