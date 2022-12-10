package huce.Controller;

import huce.Algorithm.Node.Node;
import huce.Model.AppDB;
import huce.View.MainApp;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;

public class OnChangeRootController extends Controller{

    @Override
    public void controll(MainApp myapp) {
        myapp.jListRootNode.addActionListener(
                (ActionEvent event) -> {
                    var nodes = super.database.getNodes();
                    var nodesStr = database.getNodes().keySet().toArray();
                    myapp.repaintToList(nodesStr);
                    myapp.repaintBlockList(nodesStr);
                    for (Node node : nodes.values()) {
                        node.resetBlockingNodes();
                    }
                    // clear block-table:
                    ( (DefaultTableModel) myapp.jTableBlock.getModel()).setRowCount(0);
            }
        );
    }
}
