package huce;

import huce.Algorithm.Dijkstra;
import huce.Algorithm.Node.Node;
import huce.Graphviz.Parser;
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
            }
            """;
    public static void main(String[] args) {
        var edges = Parser.toEdges(graphFile);
        var nodes = Parser.toNodes( (TreeMap<String, Integer>) edges);
        Node src = nodes.get("E");
        Dijkstra.setAsRoot(src);
        Node dest = nodes.get("G");
        Dijkstra.travel(src, dest);
        System.out.println( Dijkstra.getPath(dest.pre, "" + dest) );
    }
}