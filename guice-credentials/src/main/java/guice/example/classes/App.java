package guice.example.classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class App {

    private static final String URL = "some_url";

    private final String userName;
    private final String password;

    public App(final String username, final String password) {
        this.userName = username;
        this.password = password;
    }

    public void run() {
        // warn here because TestCredentialsModule provides hard-coded credentials.
        try (Connection connection = DriverManager.getConnection(URL, userName, password)) {
            connection.beginRequest();
        } catch (SQLException e) {
            // ignore the exception since the DB doesn't exist.
        }
    }
}
