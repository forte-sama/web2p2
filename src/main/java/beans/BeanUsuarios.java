package beans;

import models.Usuario;
import wrappers.db.DBService;
import wrappers.GestorUsuarios;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.faces.validator.ValidatorException;

@ManagedBean
@RequestScoped
public class BeanUsuarios {
	String email;
    String password;

    static {
        //iniciar servicio db
        DBService.test();
        //crear admin default
        GestorUsuarios.crearAdminDefault();
    }

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
        return null;
    }

    public String iniciarSesion() {
        Usuario usuario = new Usuario();
        usuario.setEmail(email);
        usuario.setPassword(password);

        Usuario found = GestorUsuarios.buscarCredenciales(email,password);

        return "salidita";
//        if(found != null) {
//            return "salidita";
//        }
//        else {
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Contact admin."));
//            return null;
//        }
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
}