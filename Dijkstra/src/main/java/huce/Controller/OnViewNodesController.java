package huce.Controller;

import huce.Model.AppDB;
import huce.View.GraphView;
import huce.View.MainApp;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class OnViewNodesController extends Controller{
    public OnViewNodesController(AppDB database) {
        super(database);
    }

    @Override
    public void controll(MainApp myapp) {
        myapp.jMenuViewNodes.addActionListener(
                (ActionEvent event) -> {
                    var nodes = this.database.getNodes();
                    if (nodes == null) {
                        JOptionPane.showMessageDialog(myapp, "No data to show.");
                        return;
                    }
                    GraphView viewNodes = new GraphView(nodes);
                    viewNodes.drawGraph();
                    viewNodes.setLocationRelativeTo(myapp);
                    viewNodes.setVisible(true);
                }
        );
    }
}
