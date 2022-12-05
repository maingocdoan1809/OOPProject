package huce.Controller;

import com.mindfusion.drawing.Brushes;
import huce.Algorithm.Dijkstra;
import huce.Algorithm.Node.Node;
import huce.Exception.PathNotFoundException;
import huce.Model.AppDB;
import huce.View.GraphView;
import huce.View.MainApp;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class OnGeneratePathController extends Controller{

    public OnGeneratePathController(AppDB database) {
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
            Dijkstra.setAsRoot(start);
            try {
                Dijkstra.travel( start, end );
                GraphView viewGraph = new GraphView(nodes);
                viewGraph.drawGraph();
                var paths = Dijkstra.extractPaths(end);
                viewGraph.highlightNode(start, Brushes.BlueViolet);
                viewGraph.highlightNode(end, Brushes.BlueViolet);
                Thread drawBlockNodes = new Thread(()-> {
                    var blockedNodes = start.getBlocked();
                    for ( Node blockedNode : blockedNodes ) {
                        viewGraph.highlightNode(blockedNode, Brushes.Red);
                        try {
                            Thread.sleep(500);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                drawBlockNodes.start();
                viewGraph.addStartEvent(paths);
                viewGraph.addReloadEvent(paths);
                viewGraph.setLocationRelativeTo(myapp);
                viewGraph.setVisible(true);
            } catch (PathNotFoundException err) {
                JOptionPane.showMessageDialog(myapp, err.getMessage());
            }
            finally {
                Dijkstra.reset(nodes);
            }
        } );
    }
}
