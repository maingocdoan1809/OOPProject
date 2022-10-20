package huce.Model;

import java.sql.Connection;
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
        System.out.println("Ok dey");
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
