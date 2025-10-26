package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @param <T>
 */
public abstract class DBContext<T> {

    protected Connection connection;

    public DBContext() {
        String user = "sa";
        String pass = "123";
        String url = "jdbc:sqlserver://localhost\\MSSQLSERVER:1433;databaseName=SWD;trustServerCertificate=true;";

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(url, user, pass);

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
