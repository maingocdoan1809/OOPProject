package huce.Controller;

import huce.Algorithm.Dijkstra;
import huce.Graphviz.Parser;
import huce.Model.AppDB;
import huce.View.MainApp;
import javax.swing.*;
import java.awt.event.*;

public class OnloadController extends Controller {
    public OnloadController(AppDB database) {
        super(database);
    }


    @Override
    public void controll(MainApp myapp) {
        {
            myapp.addWindowListener(new WindowAdapter() {
                @Override
                public void windowOpened(WindowEvent e) {
                    try {
                        OnloadController.super.database.toNodes(myapp);
                        var nodes =
                                OnloadController.super.database.getNodes();
                        for (String nodeName : nodes.keySet()) {
                            myapp.jListRootNode.addItem(nodeName);
                            myapp.jListToNode.addItem(nodeName);
                        }
                    } catch (Exception err) {
                        err.printStackTrace();
                        JOptionPane.showMessageDialog(myapp, err.getMessage());
                    }
                }
            });

        }

    }
}
