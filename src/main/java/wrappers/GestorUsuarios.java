package wrappers;

import models.Usuario;
import org.hibernate.Query;
import org.hibernate.Session;
import wrappers.db.HibernateUtil;

import java.util.List;

public class GestorUsuarios {
    private static Session newSession() {
        return HibernateUtil.getSessionFactory().openSession();
    }

	public static void save(Usuario usuario) {
        Session session = newSession();

		session.beginTransaction();
		session.save(usuario);
		session.getTransaction().commit();
		session.close();
	}

    public static void crearAdminDefault() {
        Session session = newSession();

        Usuario admin = new Usuario();
        admin.setEmail("admin");
        admin.setNombre("admin");
        admin.setPassword("admin");
        admin.setAdministrador(true);

        session.beginTransaction();
        session.merge(admin);
        session.getTransaction().commit();
        session.close();
    }

    public static Usuario buscarCredenciales(String email, String password) {
        Session session = newSession();
        Query query = session.createQuery("from Usuario as us where email = :email and password = :pass");
        query.setString("email",email);
        query.setString("pass",password);

        List<Usuario> res = query.list();

        return res.size() > 0 ? res.get(0) : null;
    }

    public static Usuario getByEmail(String email) {
        Session session = newSession();

        Query query = session.createQuery("from Usuario as us where email = :email");
        query.setString("email",email);

        List<Usuario> res = query.list();

        return res.size() > 0 ? res.get(0) : null;
    }
}
