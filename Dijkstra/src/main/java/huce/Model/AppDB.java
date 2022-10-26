package huce.Model;

import com.sun.source.tree.Tree;
import huce.Algorithm.Dijkstra;
import huce.Algorithm.Node.Node;
import huce.Exception.GraphvizFileFormatException;
import huce.Exception.NoDataException;
import huce.Graphviz.Parser;
import huce.View.MainApp;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

public class AppDB implements AutoCloseable {
    private Connection connection;
    private TreeMap<String, Node> nodes;
    static AppDB database = null;
    // singleton design pattern
    public static AppDB getModel(String connectionString) {
        if (database == null) {
            database = new AppDB(connectionString);
        }
        return  database;
    }
    public static String getConnectionString(String username, String password,
                                             String dbName) {
        return ("jdbc:sqlserver://localhost\\SQLEXPRESS;database=%s;encrypt=false;" +
                "user=%s;password=%s;trustServerCertificate=true").formatted(dbName,
                username, password);
    }
    private AppDB(String connectionString) {
        try {
            this.connection = DriverManager.getConnection(connectionString);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void toNodes(String graph) throws GraphvizFileFormatException, NoDataException {
            this.nodes = Parser.toNodes(graph);
    }
    public ResultSet getUseCases() {
        try {
            Statement statement = connection.createStatement();
            return statement.executeQuery(
            """
                Select * from TESTCASE
                """);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public TreeMap<String, Node> getNodes() {
        return this.nodes;
    }
    @Override
    public void close() throws Exception {
        connection.close();
    }
}
