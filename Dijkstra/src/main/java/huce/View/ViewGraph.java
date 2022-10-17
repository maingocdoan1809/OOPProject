package huce.View;

import com.mindfusion.diagramming.*;
import com.mindfusion.diagramming.GridLayout;
import com.mindfusion.diagramming.Shape;
import com.mindfusion.diagramming.SpringLayout;
import com.mindfusion.diagramming.jlayout.Direction;
import com.mindfusion.drawing.Brushes;
import com.mindfusion.drawing.Pens;
import huce.Algorithm.Node.Node;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.Set;
import java.util.TreeMap;

public class ViewGraph extends JFrame {
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
    private void drawPath(TreeMap<String, DiagramNode> diagramNodes, Node dest) {
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
                break;
            }
        }
        drawPath(diagramNodes, dest.pre);
    }
    public ViewGraph(TreeMap<String, Node> nodes, Node src, Node dest) {
        super("Graphic illustration for Dijkstra Algorithm. Author: Mai Ngoc Doan");
        Diagram diagram = new Diagram();
        Rectangle2D.Float bounds = new Rectangle2D.Float(0, 0, 15, 15);
        var diagramNodes = toDiagramNodes(nodes.keySet(), diagram);

        for ( var diaNodeName : diagramNodes.keySet() ) {
            ShapeNode from = (ShapeNode) diagramNodes.get(diaNodeName);
            if (diaNodeName.equals(src.getName())) {
                from.setBrush(Brushes.BlueViolet);
            }
            var adjacentNodes = nodes.get(diaNodeName).getAdjacentNodes();
            for ( var adjacentNode : adjacentNodes.keySet() ) {
                ShapeNode to = (ShapeNode) diagramNodes.get(adjacentNode.getName());
                if (adjacentNode.getName().equals(dest.getName())) {
                    to.setBrush(Brushes.BlueViolet);
                }
                int distance = adjacentNodes.get(adjacentNode);
                connect(diagram, from, to, distance);
            }
        }
        drawPath(diagramNodes, dest);

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
