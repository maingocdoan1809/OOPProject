package huce.Controller;

import com.mindfusion.drawing.Brush;
import com.mindfusion.drawing.Brushes;
import com.mindfusion.drawing.Pen;
import com.mindfusion.drawing.Pens;
import huce.Algorithm.Dijkstra;
import huce.Algorithm.Node.Node;
import huce.Exception.PathNotFoundException;
import huce.View.GraphView;
import huce.View.MainApp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;

public class OnGeneratePathController extends Controller{

    @Override
    public void controll(MainApp myapp) {
        myapp.graphView.addStartEvent( (evt) -> {
            String selectedStartNode = (String) myapp.jListRootNode.getSelectedItem();
            String selectedEndNode = (String) myapp.jListToNode.getSelectedItem();
            var nodes = super.database.getNodes();
            Node start = nodes.get( selectedStartNode );
            Node end = nodes.get( selectedEndNode );
            Dijkstra.setAsRoot(start);
            try {
                Dijkstra.travel( start, end );
                GraphView viewGraph = myapp.graphView;
                myapp.clickReload();
                viewGraph.drawGraph();
                var paths = Dijkstra.extractPaths(end);
                Thread drawBlockNodes = new Thread(()-> {
                    var blockedNodes = start.getBlocked();
                    for ( Node blockedNode : blockedNodes ) {
                        viewGraph.highlightNode(blockedNode, Brushes.Red);
                    }
                });
                drawBlockNodes.start();


                // no more than 3 paths will be printed:
                Thread drawPaths = new Thread( ()-> {
                    Pen[] pens = new Pen[] {Pens.Green, Pens.Yellow,
                            Pens.OrangeRed};
                    Brush[] brushes = new Brush[]{Brushes.Green, Brushes.Orange,
                            Brushes.OrangeRed};
                    int index = 0;
                    myapp.jResultText.setText("Các cung đường có thể đi: ");
                    for ( var path : paths ) {
                        if (index == 3) {
                            break;
                        }
                        viewGraph.drawPath(path,pens[index], brushes[index], 5 - index );
                        index ++;
                        myapp.jResultText.setText( myapp.jResultText.getText() +
                                "\nGraph[" + index +"]: " + Dijkstra.getPathString(path) +
                                "\n" );
                    }
                    myapp.jResultText.setText( myapp.jResultText.getText() + "\nTổng " +
                            "chi " +
                            "phí: " + end.getEstimate());
                    Dijkstra.reset(nodes);
                } );

                viewGraph.addReloadEvent(paths);
                drawPaths.start();

            } catch (PathNotFoundException err) {
                JOptionPane.showMessageDialog(myapp, err.getMessage());
                Dijkstra.reset(nodes);

            }
        } );
    }
}