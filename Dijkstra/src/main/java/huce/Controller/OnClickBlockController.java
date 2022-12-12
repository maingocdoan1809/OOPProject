package huce.Controller;

import huce.Algorithm.Node.Node;
import huce.View.GraphView;
import huce.View.MainApp;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;

public class OnClickBlockController extends Controller{

    @Override
    public void controll(MainApp myapp) {
        myapp.jBtnOKBlock.addActionListener( (ActionEvent event) -> {
            String srcNodeName = (String)myapp.jListRootNode.getSelectedItem();
            String blockNodeName = (String)myapp.jListToNodeBlock.getSelectedItem();
            assert blockNodeName != null;
            if (blockNodeName.equals("None")) {
                return;
            }
            Node srcNode = super.database.getNodes().get(srcNodeName);
            Node blockNode = super.database.getNodes().get(blockNodeName);
            if ( srcNode != blockNode ) {
                DefaultTableModel blockTable =
                        (DefaultTableModel) myapp.jTableBlock.getModel();
                if ( srcNode.getBlocked().contains(blockNode) ) {
                    return;
                }
                srcNode.blockNode(blockNode);
                blockTable.addRow(new String[] {blockNodeName});
                String selectedBlock =
                        (String) myapp.jListToNodeBlock.getSelectedItem();
                var colorBlock =
                        GraphView.brushes.get( myapp.graphView.getBlockColor() );
                myapp.graphView.highlightNode( selectedBlock,
                        colorBlock);
                myapp.jBtnRemove.setEnabled(true);
            } else {
                JOptionPane.showMessageDialog(myapp, "Cannot block " + srcNode + " to " +
                        "itself.");
            }
        } );
    }
}