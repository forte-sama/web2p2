package beans;

import models.Usuario;
import wrappers.GestorUsuarios;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;

@ManagedBean
@RequestScoped
public class BeanUsuarios {
	String email;
    String password;
    String nombre;
    String direccion;
    String telefono;

    public BeanUsuarios() { }

    public void validarCredenciales(ComponentSystemEvent event) {

        UIComponent components = event.getComponent();

        //get email value from component
        UIInput emailInput = (UIInput)components.findComponent("emailInput");
        String email = emailInput.getLocalValue() == null ? "" : emailInput.getLocalValue().toString();

        //get password value from component
        UIInput passInput = (UIInput)components.findComponent("passInput");
        String password = passInput.getLocalValue() == null ? "" : passInput.getLocalValue().toString();

        Usuario found = GestorUsuarios.buscarCredenciales(email,password);

        if(found == null) {
            System.out.println("NO SE ENCUENTRA");
            //no encontro nada
            FacesMessage message = new FacesMessage("Fallo en iniciar sesion.",
                    "user/password incorrecto");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);

            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null,message);

            fc.renderResponse();
        }
    }

	public String guardarUsuario() {
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setEmail(email);
        nuevoUsuario.setNombre(nombre);
        nuevoUsuario.setPassword(password);
        nuevoUsuario.setDireccion(direccion);
        nuevoUsuario.setTelefono(telefono);

        GestorUsuarios.guardar(nuevoUsuario);

        return "index?faces-redirect=true";
    }

    public String iniciarSesion() {
        //solo entra a este metodo si pasa la validacion postvalidate validarCredenciales
        return "productosDisponibles?faces-redirect=true";
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String pass) {
        this.password = pass;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
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
}