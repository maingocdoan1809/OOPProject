package huce.Controller;

import huce.Algorithm.Node.Node;
import huce.Model.AppDB;
import huce.View.MainApp;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashSet;

public class OnChangeRootController extends Controller{
    public OnChangeRootController(AppDB database) {
        super(database);
    }


    @Override
    public void controll(MainApp myapp) {
        myapp.jListRootNode.addActionListener(
                (ActionEvent event) -> {
                    var nodes = super.database.getNodes();
                    for (Node node : nodes.values()) {
                        node.resetBlockingNodes();
                    }
                    // clear block-table:
                    ( (DefaultTableModel) myapp.jTableBlock.getModel()).setRowCount(0);
            }
        );
    }
}
