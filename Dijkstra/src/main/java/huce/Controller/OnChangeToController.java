package huce.Controller;

import huce.View.GraphView;
import huce.View.MainApp;

import java.awt.event.ActionEvent;

public class OnChangeToController extends Controller {
    @Override
    public void controll(MainApp myapp) {
        myapp.jListToNode.addActionListener( (ActionEvent e) -> {
            var nodes = database.getNodes().keySet().toArray();
            myapp.repaintBlockList(nodes);
            if ( myapp.graphView != null ) {
                var to = myapp.rootAndTo.getSecond();
                var newTo = (String) myapp.jListToNode.getSelectedItem();
                if ( to != null && newTo != null ) {
                    myapp.graphView.highlightNode(myapp.rootAndTo.getSecond(), null);
                    myapp.setRootAndTo(null,
                            database.getNodes().get( newTo));
                    myapp.clickReload();

                }
            }
        } );
    }
}