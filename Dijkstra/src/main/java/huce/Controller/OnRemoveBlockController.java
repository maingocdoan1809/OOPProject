package huce.Controller;

import huce.Algorithm.Node.Node;
import huce.Model.AppDB;
import huce.View.MainApp;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;

public class OnRemoveBlockController extends Controller{

    public OnRemoveBlockController(AppDB database) {
        super(database);
    }

    @Override
    public void controll(MainApp myapp) {
        myapp.jBtnRemove.addActionListener(
                (ActionEvent e)  -> {
                    int rowSelected = myapp.jTableBlock.getSelectedRow();
                    if ( rowSelected == -1 ) {
                        JOptionPane.showMessageDialog(myapp, "Please choose a node from" +
                                " the block table to remove");
                    } else {
                        DefaultTableModel tableModel =
                                (DefaultTableModel) myapp.jTableBlock.getModel();
                        // reserve blockNode name:
                        String blockName = (String) tableModel.getValueAt(rowSelected, 0);
                        // delete selected row:
                        tableModel.removeRow(rowSelected);
                        // remove block Node from srcNode's block list:
                        String srcName = (String) myapp.jListRootNode.getSelectedItem();
                        var nodes= super.database.getNodes();
                        Node src = nodes.get(srcName);
                        Node blockedNode = nodes.get(blockName);
                        src.removeBlock(blockedNode);
                        JOptionPane.showMessageDialog(myapp, "Removed " + blockName +
                                " from " + srcName + "'s block list");
                        if ( tableModel.getRowCount() == 0 ) {
                            myapp.jBtnRemove.setEnabled(false);
                        }
                    }
                }
        );
    }
}
