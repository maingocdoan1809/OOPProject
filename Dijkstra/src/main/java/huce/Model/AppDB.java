package huce.Model;

import huce.Algorithm.Node.Node;
import huce.Exception.GraphvizFileFormatException;
import huce.Exception.NoDataException;
import huce.Graphviz.Parser;

import java.sql.*;
import java.util.PriorityQueue;
import java.util.TreeMap;
import java.util.TreeSet;

public class AppDB implements AutoCloseable {
    static AppDB database = null;
    private Connection connection;
    private TreeMap<String, Node> nodes;
    private PriorityQueue<TreeSet<Node>> paths;

    private AppDB(String connectionString) {
        try {
            this.connection = DriverManager.getConnection(connectionString);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // singleton design pattern
    public static AppDB getModel(String connectionString) {
        if (database == null) {
            database = new AppDB(connectionString);
        }
        return database;
    }

    public static String getConnectionString(String username, String password,
                                             String dbName) {
        return ("jdbc:sqlserver://localhost;database=%s;encrypt=false;" +
                "user=%s;password=%s;trustServerCertificate=true").formatted(dbName,
                username, password);
    }

    public PriorityQueue<TreeSet<Node>> getPaths() {
        return this.paths;
    }

    public void setPaths(PriorityQueue<TreeSet<Node>> paths) {
        this.paths = paths;
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