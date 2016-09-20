package validators;

import models.Usuario;
import wrappers.GestorUsuarios;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.xml.bind.ValidationException;

/**
 * Created by forte on 20/09/16.
 */
@FacesValidator(value="loginValidator")
public class LoginValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value)
            throws ValidatorException {

        String email = (String)value;

        Usuario found = GestorUsuarios.getByEmail(email);

        if(found == null) {
            System.out.println("NO SE ENCUENTRA");
            //no encontro nada
            FacesMessage message = new FacesMessage("Fallo en iniciar sesion.",
                                                    "user/password incorrecto");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);

            throw new ValidatorException(message);
        }
    }
}
