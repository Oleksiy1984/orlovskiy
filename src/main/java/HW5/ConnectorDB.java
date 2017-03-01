package HW5;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * @author Alexey.
 */
public class ConnectorDB {
    public static Connection getConnection() throws SQLException {
        //get data from resourses/database.properties
        ResourceBundle resource = ResourceBundle.getBundle("database");
        String url = resource.getString("db.url");
        String user = resource.getString("db.user");
        String pass = resource.getString("db.password");
        String remote=resource.getString("db.url1");
        //return DriverManager.getConnection(remote);
        return DriverManager.getConnection(url, user, pass);
    }
}
