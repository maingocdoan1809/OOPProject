package huce.Controller;

import huce.Algorithm.Node.Node;
import huce.View.GraphView;
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
                    if ( myapp.graphView != null ) {
                        var root = myapp.rootAndTo.getFirst();
                        for (var blockingNode : root.getBlocked()) {
                            myapp.graphView.highlightNode( blockingNode, null);
                        }
                        myapp.graphView.highlightNode(myapp.rootAndTo.getFirst(), null);
                        myapp.setRootAndTo(database.getNodes().get( (String) myapp.jListRootNode.getSelectedItem() ),
                                database.getNodes().get( (String) myapp.jListToNode.getSelectedItem() ));
                        myapp.clickReload();

                    }
                    for (Node node : nodes.values()) {
                        node.resetBlockingNodes();
                    }

                    // clear block-table:
                    ( (DefaultTableModel) myapp.jTableBlock.getModel()).setRowCount(0);
            }
        );
    }
}