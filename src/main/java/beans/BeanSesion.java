package beans;

import models.Producto;
import models.Usuario;
import wrappers.GestorDetalleVentas;
import wrappers.GestorProductos;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.util.Locale;

/**
 * Created by forte on 28/09/16.
 */
@ManagedBean
@SessionScoped
public class BeanSesion {
    Usuario usuario;

    private Locale locale;

    public BeanSesion() {
        locale = FacesContext.getCurrentInstance().getExternalContext().getRequestLocale();
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }
    public Locale getLocale() {
        return locale;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
