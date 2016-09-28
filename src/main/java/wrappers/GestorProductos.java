package wrappers;

import models.Producto;
import models.Usuario;
import org.hibernate.Query;
import org.hibernate.Session;
import wrappers.db.HibernateUtil;

import java.util.ArrayList;
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

    public static List<Producto> getAll() {
        Session session = newSession();
        Query query = session.createQuery("from Producto as pro");

        return query.list();
    }

    public static List<Producto> getAll(int pageNumber, int pageSize) {
        Session session = newSession();
        Query query = session.createQuery("from Producto as pro");

        query.setFirstResult(pageNumber);
        query.setMaxResults(pageSize);

        return query.list();
    }

    public static Long obtenerCantidadProductos() {
        Session session = newSession();
        Query query = session.createQuery("select COUNT(*) from Producto");

        List<Long> res = query.list();

        return res.size() > 0 ? res.get(0) : null;
    }

    public static Long getCantidadById(Long id) {
        Session session = newSession();

        Query query = session.createQuery("select pro.cantidad from Producto as pro where id = :id");
        query.setLong("id",id);

        List<Long> res = query.list();

        return res.size() > 0 ? res.get(0) : null;
    }

    public static void reduceExistence(Producto target, Long value) {
        Session session = newSession();

        session.beginTransaction();
        //reducir cantidad
        target.setCantidad(target.getCantidad() - value);
        //persistir cambios
        session.merge(target);
        session.getTransaction().commit();
        session.close();
    }
}
