package huce.Controller;

import huce.Exception.GraphvizFileFormatException;
import huce.Model.AppDB;
import huce.View.MainApp;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class OnClickOkController extends Controller{
    public OnClickOkController(AppDB database) {
        super(database);
    }
    @Override
    public void controll(MainApp myapp) {
        myapp.jButtonOk.addActionListener(
                (ActionEvent event) -> {
                    String graph = myapp.jTextEditer.getText();
                    if (graph.equals("")) {
                        JOptionPane.showMessageDialog(myapp, "You need to type your graph first");
                    } else {
                        System.out.println(graph);
                        try {
                            super.database.toNodes(graph);

                        } catch ( GraphvizFileFormatException err) {
                            JOptionPane.showMessageDialog(myapp, err.getMessage());
                        }
                        var nodes = super.database.getNodes();
                        myapp.prepareNodes(nodes);
                    }
                }
        );
    }
}
