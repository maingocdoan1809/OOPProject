package huce;

import com.mindfusion.diagramming.*;
import huce.Algorithm.Dijkstra;
import huce.Algorithm.Node.Node;
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
            digraph mygraph {
                "A" -> "C" [label="3"]
                "A" -> "F" [label="2"]
                "C" -> "A" [label="3"]
                "C" -> "F" [label="2"]
                "C" -> "E" [label="1"]
                "C" -> "D" [label="4"]
                "B" -> "D" [label="1"]
                "B" -> "E" [label="2"]
                "B" -> "F" [label="6"]
                "B" -> "G" [label="2"]
                "D" -> "C" [label="4"]
                "D" -> "B" [label="1"]
                "E" -> "C" [label="1"]
                "E" -> "F" [label="3"]
                "E" -> "B" [label="2"]
                "F" -> "A" [label="2"]
                "F" -> "C" [label="2"]
                "F" -> "E" [label="3"]
                "F" -> "B" [label="6"]
                "F" -> "G" [label="5"]
                "G" -> "F" [label="5"]
                "G" -> "B" [label="2"]
                "K" -> "K" [label="0"]

            }
            """;
    public static void main(String[] args) {
        var edges = Parser.toEdges(graphFile);
        var nodes = Parser.toNodes( (TreeMap<String, Integer>) edges);
        Node src = nodes.get("A");
//        src.blockNode(nodes.get("C"));
        Dijkstra.setAsRoot(src);
        Node dest = nodes.get("B");
        try {
            Dijkstra.travel(src, dest);
            System.out.println( Dijkstra.getPath(dest.pre, "" + dest) );
        } catch (PathNotFoundException e) {
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ViewGraph view = new ViewGraph(nodes, src, dest);
            }
        });
    }
}