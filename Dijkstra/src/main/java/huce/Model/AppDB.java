package huce.Model;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AppDB implements AutoCloseable {
    private Connection connection;
    public AppDB(String connectionString) {
        try {
            this.connection = DriverManager.getConnection(connectionString);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public String getUseCase(int id) {
        return "";
    }
    public void createUseCase(int id, String usecase) {

    }
    @Override
    public void close() throws Exception {
        connection.close();
    }
}
