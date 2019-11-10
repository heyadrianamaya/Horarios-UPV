package upv.poo.basededatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionConfiguration {

    private static ConnectionConfiguration instance;
    private Connection connection;

    private ConnectionConfiguration() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/horarios?serverTimezone=UTC";
        String username = "root";
        String password = "";
        this.connection = DriverManager.getConnection(url, username, password);
    }

    public Connection getConnection() {
        return connection;
    }

    public static ConnectionConfiguration getInstance() throws SQLException, ClassNotFoundException {
        if (instance == null) {
            instance = new ConnectionConfiguration();
        } else if (instance.getConnection().isClosed()) {
            instance = new ConnectionConfiguration();
        }
        return instance;
    }
}