package huce.Controller;

import huce.Algorithm.Dijkstra;
import huce.Algorithm.Node.Node;
import huce.Exception.PathNotFoundException;
import huce.Model.AppDB;
import huce.View.MainApp;
import huce.View.ViewGraph;

import java.awt.event.ActionEvent;

public class GeneratePathController extends Controller{

    public GeneratePathController(AppDB database) {
        super(database);
    }
    @Override
    public void controll(MainApp myapp) {
        myapp.jBtnGenerate.addActionListener( (ActionEvent event) -> {
            String selectedStartNode = (String) myapp.jListRootNode.getSelectedItem();
            String selectedEndNode = (String) myapp.jListToNode.getSelectedItem();
            var nodes = super.database.getNodes();
            Node start = nodes.get( selectedStartNode );
            Node end = nodes.get( selectedEndNode );
            for ( var nodeName : nodes.keySet() ) {
                Node crr = nodes.get(nodeName);
                Dijkstra.resetRootState( crr);
                crr.pre = null;
            }
            Dijkstra.setAsRoot(start);
            try {
                Dijkstra.travel( start, end );
                ViewGraph viewGraph = new ViewGraph(nodes);
                viewGraph.drawGraph();
                viewGraph.drawPath(end);
                viewGraph.setVisible(true);
            } catch (PathNotFoundException err) {
                err.printStackTrace();
            }

        } );
    }
}
