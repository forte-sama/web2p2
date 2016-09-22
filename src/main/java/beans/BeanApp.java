package beans;

import wrappers.GestorUsuarios;
import wrappers.db.DBService;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 * Created by forte on 22/09/16.
 */
@ManagedBean(eager = true)
@ApplicationScoped
public class BeanApp {
    public BeanApp() {
        //iniciar servicio db
        DBService.test();
        //crear admin default
        GestorUsuarios.crearAdminDefault();
    }
}
