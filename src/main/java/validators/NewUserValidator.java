package validators;

import models.Usuario;
import wrappers.GestorUsuarios;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * Created by forte on 20/09/16.
 */
@FacesValidator(value="newUserValidator")
public class NewUserValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value)
            throws ValidatorException {

        String email = (String)value;

        Usuario found = GestorUsuarios.getByEmail(email);

        if(found != null) {
            System.out.println("NO SE PUEDE REGISTRAR");
            //no encontro nada
            FacesMessage message = new FacesMessage("Error de registro.",
                                                    "Email existente.");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);

            throw new ValidatorException(message);
        }
    }
}
