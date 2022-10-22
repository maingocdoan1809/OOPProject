package huce.Controller;

import huce.Algorithm.Node.Node;
import huce.Model.AppDB;
import huce.View.MainApp;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;

public class OnClickBlockController extends Controller{

    public OnClickBlockController(AppDB database) {
        super(database);
    }

    @Override
    public void controll(MainApp myapp) {
        myapp.jBtnOKBlock.addActionListener( (ActionEvent event) -> {
            String srcNodeName = (String)myapp.jListRootNode.getSelectedItem();
            String blockNodeName = (String)myapp.jListToNodeBlock.getSelectedItem();
            Node srcNode = super.database.getNodes().get(srcNodeName);
            Node blockNode = super.database.getNodes().get(blockNodeName);
            if ( srcNode != blockNode ) {
                srcNode.blockNode(blockNode);
                DefaultTableModel blockTable =
                        (DefaultTableModel) myapp.jTableBlock.getModel();
                blockTable.addRow(new String[] {blockNodeName});
            } else {
                JOptionPane.showMessageDialog(myapp, "Cannot block " + srcNode + " to " +
                        "itself.");
            }
        } );
    }
}
