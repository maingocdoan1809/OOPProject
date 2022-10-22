package huce.Controller;

import huce.Algorithm.Dijkstra;
import huce.Graphviz.Parser;
import huce.Model.AppDB;
import huce.View.MainApp;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;

public class OnloadController extends Controller {
    public OnloadController(AppDB database) {
        super(database);
    }


    @Override
    public void controll(MainApp myapp) {
            myapp.addWindowListener(new WindowAdapter() {
                @Override
                public void windowOpened(WindowEvent e) {
                    try {
                        OnloadController.super.database.toNodes(myapp);
                        var nodes =
                                OnloadController.super.database.getNodes();
                        myapp.prepareNodes(nodes);
                        DefaultTableModel tableModel =
                                (DefaultTableModel) myapp.jTestcaseTable.getModel();
                        var data = OnloadController.super.database.getUseCases();
                        for ( var d : data ) {
                            tableModel.addRow(d);
                        }
                    } catch (Exception err) {
                        err.printStackTrace();
                        JOptionPane.showMessageDialog(myapp, err.getMessage());
                    }
                }
            });
    }
}
