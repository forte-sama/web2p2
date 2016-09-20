package wrappers.db;

/**
 * Created by forte on 20/09/16.
 */

import org.h2.jdbc.JdbcSQLException;
import org.h2.tools.Server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBService {
    private static final String URL_CON = "jdbc:h2:tcp://localhost/~/web2p2";
    private static final String DB_USER = "sa";
    private static final String DB_PASS = "";

    static {
        //iniciar servidor H2
        try {
            System.out.println("Inicializando servidor H2...");
            Server.createTcpServer("-tcpPort","9092","-tcpAllowOthers").start();
            System.out.println("Servidor H2... Estado: OK!");
        }

        catch (JdbcSQLException e) {
            System.out.println("Puede que el Servidor H2 ya este Up...");
        } catch (SQLException e) {
            //TODO CAMBIAR MENSAJE DE EXCEPCION
            e.printStackTrace();
        }
    }

    private static Connection getConnection () throws SQLException {
        return DriverManager.getConnection(URL_CON,DB_USER,DB_PASS);
    }

    public static void test() {
        try {
            Connection con = getConnection();

            con.close();

            System.out.println("Base de datos... Estado: OK!");
        } catch (Exception e) {
            //TODO CAMBIAR MENSAJE DE EXCEPCION
            e.printStackTrace();
        }
    }
}

