package huce;

import com.mindfusion.diagramming.*;
import com.mindfusion.drawing.Brushes;
import huce.Algorithm.Dijkstra;
import huce.Algorithm.Node.Node;
import huce.Exception.GraphvizFileFormatException;
import huce.Exception.PathNotFoundException;
import huce.Graphviz.Parser;
import huce.View.ViewGraph;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.Set;
import java.util.TreeMap;
public class Main {
    public static final String graphFile =
            """
            graph mygraph {
                "A" -- "C" [label="3"]
                "A" -- "F" [label="2"]
                "C" -- "F" [label="2"]
                "C" -- "E" [label="1"]
                "C" -- "D" [label="4"]
                "B" -- "D" [label="1"]
                "B" -- "E" [label="2"]
                "B" -- "F" [label="6"]
                "B" -- "G" [label="2"]
                "E" -- "F" [label="3"]
                "F" -- "G" [label="5"]
                "K" -- "K" [label="0"]
            }
            """;
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            try {
                var nodes = Parser.toNodes( graphFile);
                Node src = nodes.get("A");
//                src.blockNode(nodes.get("C"));
                Dijkstra.setAsRoot(src);
                Node dest = nodes.get("B");
                Dijkstra.travel(src, dest);
                System.out.println( Dijkstra.getPath(dest.pre, "" + dest) );

                ViewGraph view = new ViewGraph(nodes);
                view.drawGraph();
                view.setVisible(true);
            } catch (PathNotFoundException |  GraphvizFileFormatException e) {
                e.printStackTrace();
            }
        });
    }
}