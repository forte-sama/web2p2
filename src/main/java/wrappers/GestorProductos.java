package wrappers;

import models.Producto;
import models.Usuario;
import org.hibernate.Query;
import org.hibernate.Session;
import wrappers.db.HibernateUtil;

import java.util.List;

/**
 * Created by forte on 22/09/16.
 */
public class GestorProductos {
    private static Session newSession() {
        return HibernateUtil.getSessionFactory().openSession();
    }

    public static Producto getById(Long id) {
        Session session = newSession();

        Query query = session.createQuery("from Producto as pro where id = :id");
        query.setLong("id",id);

        List<Producto> res = query.list();

        return res.size() > 0 ? res.get(0) : null;
    }

    public static void guardar(Producto producto) {
        Session session = newSession();

        session.beginTransaction();
        //update si ya tiene un id (objeto existe)
        //insert si no tiene un id (objeto nuevo)
        if(producto.getId() != null) {
            session.merge(producto);
        }
        else {
            session.save(producto);
        }
        session.getTransaction().commit();
        session.close();
    }
}
