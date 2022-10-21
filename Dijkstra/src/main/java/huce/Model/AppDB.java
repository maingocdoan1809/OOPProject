package huce.Model;

import com.sun.source.tree.Tree;
import huce.Algorithm.Dijkstra;
import huce.Algorithm.Node.Node;
import huce.Exception.GraphvizFileFormatException;
import huce.Graphviz.Parser;
import huce.View.MainApp;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.TreeMap;

public class AppDB implements AutoCloseable {
    private Connection connection;
    private TreeMap<String, Node> nodes;
    static AppDB database = null;
    public static AppDB getModel(String connectionString) {
        if (database == null) {
            database = new AppDB(connectionString);
        }
        return  database;
    }
    private AppDB(String connectionString) {
        try {
            this.connection = DriverManager.getConnection(connectionString);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Ok dey");
    }
    public void toNodes(MainApp myapp) {
        String option = JOptionPane.showInputDialog(myapp, "Choose an graph's ID " +
                        "from " +
                        "your " +
                        "database.",
                0);
        try {
//            this.nodes =   Parser.toNodes(this.get(Integer.parseInt(option)));

            this.nodes =   Parser.toNodes(
                    """
                    graph G {
                    "A" -- "B" [label="30"]
                    "A" -- "C" [label="20"]
                    "C" -- "G" [label="10"]
                    "G" -- "B" [label="5"]
                    "B" -- "C" [label="1"]
                    }
                    """);
        } catch (GraphvizFileFormatException err) {
            err.printStackTrace();
        }
    }
    private String get(int id) {
        return "";
    }
    public TreeMap<String, Node> getNodes() {
        return this.nodes;
    }
    @Override
    public void close() throws Exception {
        connection.close();
    }
}
