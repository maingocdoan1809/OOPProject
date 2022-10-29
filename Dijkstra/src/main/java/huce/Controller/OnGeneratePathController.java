package huce.Controller;

import com.mindfusion.drawing.Brush;
import com.mindfusion.drawing.Brushes;
import com.mindfusion.drawing.Pen;
import com.mindfusion.drawing.Pens;
import huce.Algorithm.Dijkstra;
import huce.Algorithm.Node.Node;
import huce.Exception.PathNotFoundException;
import huce.Model.AppDB;
import huce.View.MainApp;
import huce.View.GraphView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashSet;

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
                Pen[] pens = new Pen[] {Pens.Green, Pens.Yellow,
                        Pens.OrangeRed};
                Brush[] brushes = new Brush[]{Brushes.Green, Brushes.Orange,
                        Brushes.OrangeRed};
                // no more than 3 path will be print:
                int index = 0;
                for ( var path : paths ) {
                    if (index == 3) {
                        break;
                    }
                    viewGraph.drawPath(path,pens[index], brushes[index], 4 - index );
                    index ++;
                }
                viewGraph.highlightNode(start, Brushes.BlueViolet);
                viewGraph.highlightNode(end, Brushes.BlueViolet);
                var blockedNodes = start.getBlocked();
                for ( Node blockedNode : blockedNodes ) {
                    viewGraph.highlightNode(blockedNode, Brushes.Red);
                }
                viewGraph.setVisible(true);
            } catch (PathNotFoundException err) {
                JOptionPane.showMessageDialog(myapp, err.getMessage());
            }
            Dijkstra.reset(nodes);
        } );
    }
}
