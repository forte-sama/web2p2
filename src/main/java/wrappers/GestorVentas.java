package wrappers;

import models.Producto;
import models.Usuario;
import models.Venta;
import org.hibernate.Query;
import org.hibernate.Session;
import wrappers.db.HibernateUtil;

import java.util.List;

public class GestorVentas {
    private static Session newSession() {
        return HibernateUtil.getSessionFactory().openSession();
    }

	public static void guardar(Venta nuevaVenta) {
        Session session = newSession();
		session.beginTransaction();
		session.save(nuevaVenta);
		session.getTransaction().commit();
		session.close();
	}

	public static List<Venta> getAll() {
		Session session = newSession();
		Query query = session.createQuery("from Venta as v");

		return query.list();
	}
}
