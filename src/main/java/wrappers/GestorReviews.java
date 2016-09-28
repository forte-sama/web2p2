package wrappers;

import models.Producto;
import models.Review;
import models.Usuario;
import models.Venta;
import org.hibernate.Query;
import org.hibernate.Session;
import wrappers.db.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

public class GestorReviews {
    private static Session newSession() {
        return HibernateUtil.getSessionFactory().openSession();
    }

	public static void guardar(Review nuevoReview) {
        Session session = newSession();
		session.beginTransaction();
		session.save(nuevoReview);
		session.getTransaction().commit();
		session.close();
	}

	public static boolean yaHaPublicado(Producto targetProduct, Usuario targetUser) {
		if(targetUser == null || targetProduct == null) {
			return false;
		}

		Session session = newSession();

		Query query = session.createQuery("from Review as rev " +
										  "where rev.usuario = :user " +
										  "and rev.producto = :target");
		query.setEntity("user",targetUser);
		query.setEntity("target",targetProduct);

		return query.list().size() > 0;
	}

	public static List<Review> getByProduct(Producto target) {

		if(target == null) {
			return new ArrayList<>();
		}

		Session session = newSession();

		Query query = session.createQuery("from Review as rev where rev.producto = :prod");
		query.setEntity("prod",target);

		return query.list();
	}
}
