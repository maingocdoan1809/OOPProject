package huce.Controller;

import huce.Algorithm.Dijkstra;
import huce.Graphviz.Parser;
import huce.Model.AppDB;
import huce.View.MainApp;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OnloadController {
    public OnloadController(MainApp myapp, AppDB database) {
        String option = JOptionPane.showInputDialog(myapp, "Choose an graph's ID " +
                        "from " +
                        "your " +
                        "database.",
                0);

        String graph = """
                graph G {
                  "A" -- "B" [label="20"]
                  "A" -- "C" [label="50"]
                  "C" -- "B" [label="90"]
                """;
        try {
            var nodes = Parser.toNodes(graph);
            for (String nodeName : nodes.keySet()) {
                myapp.jListRootNode.addItem(nodeName);
                myapp.jListToNode.addItem(nodeName);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(myapp, e.getMessage());
        }
    }
}
