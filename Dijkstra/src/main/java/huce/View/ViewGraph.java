package huce.View;

import com.mindfusion.diagramming.Shape;
import com.mindfusion.diagramming.*;
import com.mindfusion.drawing.Brush;
import com.mindfusion.drawing.Brushes;
import com.mindfusion.drawing.Pens;
import huce.Algorithm.Node.Node;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.Set;
import java.util.TreeMap;

class ViewGraph extends JFrame {
    TreeMap<String, DiagramNode> diagramNodes;
    TreeMap<String, Node> nodes;
    Diagram diagram;
    static private TreeMap<String, DiagramNode> toDiagramNodes(Set<String> nodes,
                                                               Diagram diagram) {
        TreeMap<String, DiagramNode> diagramNodes = new TreeMap<>();
        Rectangle2D.Float bounds = new Rectangle2D.Float(0, 0, 15, 15);
        for (var nodeName : nodes) {
            ShapeNode shapeNode = diagram.getFactory().createShapeNode(bounds);
            shapeNode.setShape(Shape.getShapes().get(45));
            shapeNode.setText(nodeName);
            diagramNodes.put(nodeName, shapeNode);
        }
        return diagramNodes;
    }
    static private void connect(Diagram diagram, ShapeNode from, ShapeNode to,
                                int distance) {
        DiagramLink link =
                diagram.getFactory().createDiagramLink(from, to);
        link.addLabel(distance + "");
        link.setFont(new Font("monospace", Font.BOLD,10));
    }
    public void drawGraph() {
        for ( var diaNodeName : diagramNodes.keySet() ) {
            ShapeNode from = (ShapeNode) diagramNodes.get(diaNodeName);
            var adjacentNodes = nodes.get(diaNodeName).getAdjacentNodes();
            for ( var adjacentNode : adjacentNodes.keySet() ) {
                ShapeNode to = (ShapeNode) diagramNodes.get(adjacentNode.getName());
                int distance = adjacentNodes.get(adjacentNode);
                connect(diagram, from, to, distance);
            }
        }
    }
    public void highlightNode(Node src, Brush color) {
        DiagramNode from = this.diagramNodes.get(src.getName());
        from.setBrush(color);
    }
    private static void diagramSetUp(Diagram diagram, AbstractLayout layout) {
        layout.arrange(diagram);
        diagram.setShowHandlesOnDrag(true);
        diagram.setAllowLinksRepeat(false);
    }
    public void drawPath(Node dest) {
        if (dest.pre == null) {
            return;
        }
        String destName = dest.getName();
        String nextName = dest.pre.getName();
        var links = diagramNodes.get(destName).getIncomingLinks();
        for ( DiagramLink link : links ) {
            if ( link.getOrigin() == diagramNodes.get(nextName)) {
                link.setPen(Pens.Red);
                link.setHeadPen(Pens.Red);
                link.setLayerIndex(2);
                link.setTextBrush(Brushes.YellowGreen);
                break;
            }
        }
        drawPath(dest.pre);
    }
    public void view() {

    }
    public ViewGraph(TreeMap<String, Node> nodes) {
        super("Graphic illustration for Dijkstra Algorithm. Author: Mai Ngoc Doan");
        this.nodes = nodes;
        this.diagram = new Diagram();
        Rectangle2D.Float bounds = new Rectangle2D.Float(0, 0, 15, 15);
        this.diagramNodes = toDiagramNodes(nodes.keySet(), diagram);
        DiagramView diagramView = new DiagramView(diagram);
        JScrollPane scrollPane = new JScrollPane(diagramView);
        CircularLayout layout = new CircularLayout();
        diagramSetUp(diagram, layout);
        this.getContentPane().add(scrollPane);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
    }
}
