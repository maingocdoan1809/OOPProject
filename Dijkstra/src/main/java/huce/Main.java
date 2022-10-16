package huce;

import com.mindfusion.diagramming.*;
import com.mindfusion.drawing.Align;
import com.mindfusion.drawing.Brushes;
import com.mindfusion.drawing.FontStyle;
import com.mindfusion.drawing.Pens;
import com.sun.source.tree.Tree;
import huce.Algorithm.Dijkstra;
import huce.Algorithm.Node.Node;
import huce.Graphviz.Parser;
import org.checkerframework.checker.units.qual.C;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeMap;
class View extends JFrame {
    static private TreeMap<String, DiagramNode> toDiagramNodes(Set<String> nodes,
                                                               Diagram diagram) {
        TreeMap<String, DiagramNode> diagramNodes = new TreeMap<>();
        Rectangle2D.Float bounds = new Rectangle2D.Float(0, 0, 15, 15);
        for (var nodeName : nodes) {
            ShapeNode shapeNode = diagram.getFactory().createShapeNode(bounds);
            shapeNode.setText(nodeName);
            diagramNodes.put(nodeName, shapeNode);
        }
        return diagramNodes;
    }
    public View(TreeMap<String, Node> nodes) {

        Diagram diagram = new Diagram();
        Rectangle2D.Float bounds = new Rectangle2D.Float(0, 0, 15, 15);
        var diagramNodes = toDiagramNodes(nodes.keySet(), diagram);

        for ( var diaNodeName : diagramNodes.keySet() ) {
            ShapeNode from = (ShapeNode) diagramNodes.get(diaNodeName);
            var adjacentNodes = nodes.get(diaNodeName).getAdjacentNodes();
            for ( var adjacentNode : adjacentNodes.keySet() ) {
                ShapeNode to = (ShapeNode) diagramNodes.get(adjacentNode.getName());
                DiagramLink link =
                        diagram.getFactory().createDiagramLink(from, to);
                String distance = adjacentNodes.get(adjacentNode) + "";
                link.addLabel(distance);
                link.setFont(new Font("monospace", Font.BOLD,10));
                link.setToolTip(String.format("the cost from %s to %s is %s",
                        diaNodeName, adjacentNode.getName(), distance));
            }
        }
        DiagramView diagramView = new DiagramView(diagram);
        JScrollPane scrollPane = new JScrollPane(diagramView);
        CircularLayout layout = new CircularLayout();
        layout.arrange(diagram);
        diagram.setAllowMultipleResize(true);
        diagram.setAutoAlignNodes(true);
        diagram.setAllowLinksRepeat(false);
        diagramView.setAllowInplaceEdit(false);
        this.getContentPane().add(scrollPane);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }
}
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
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                View view = new View(nodes);
            }
        });
    }
}