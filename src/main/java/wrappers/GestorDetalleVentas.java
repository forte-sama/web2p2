package wrappers;

import models.DetalleVenta;
import models.Producto;
import models.Usuario;
import models.Venta;
import org.hibernate.Query;
import org.hibernate.Session;
import wrappers.db.HibernateUtil;

import java.util.List;

public class GestorDetalleVentas {
    private static Session newSession() {
        return HibernateUtil.getSessionFactory().openSession();
    }

	public static void guardar(DetalleVenta nuevoDV) {
        Session session = newSession();
		session.beginTransaction();
		session.save(nuevoDV);
		session.getTransaction().commit();
		session.close();
	}

	public static boolean loHaComprado(Producto target, Usuario usuario) {
		Session session = newSession();

		Query query = session.createQuery("from DetalleVenta as dv " +
										  "where dv.venta.usuario = :user " +
										  "and dv.producto = :target");
		query.setEntity("user",usuario);
		query.setEntity("target",target);

		return query.list().size() > 0;
	}

	public static List<DetalleVenta> historialVentas(Producto target) {
		Session session = newSession();

		Query query = session.createQuery("from DetalleVenta as dv " +
										  "where dv.producto = :target");
		query.setEntity("target",target);

		return query.list();
	}

	public static int cantidadProductosVendidos() {
		Session session = newSession();

		Query query = session.createQuery("from DetalleVenta as dv");

		int total = 0;
		List<DetalleVenta> detallesVentas = query.list();

		for(DetalleVenta dv : detallesVentas) {
			total += dv.getCantidad();
		}

		return total;
	}

	public static int cantidadConsumida(Producto target) {
		Session session = newSession();

		Query query = session.createQuery("from DetalleVenta as dv " +
										  "where dv.producto = :target");
		query.setEntity("target",target);

		int total = 0;
		List<DetalleVenta> detallesVentas = query.list();

		for(DetalleVenta dv : detallesVentas) {
			total += dv.getCantidad();
		}

		return total;
	}
}
