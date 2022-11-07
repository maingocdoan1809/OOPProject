package huce.View;

import com.mindfusion.diagramming.Shape;
import com.mindfusion.diagramming.*;
import com.mindfusion.drawing.*;
import huce.Algorithm.Node.Node;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class GraphView extends JFrame {
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
        var linkLabel = link.addLabel(distance + "");
        linkLabel.setHorizontalAlign(Align.Far);
        link.setFont(new Font("monospace", Font.ITALIC,5));
        link.setShadowOffsetX(0);
        link.setShadowOffsetY(0);
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
    synchronized public void drawPath(TreeSet<Node> path, Pen colorPen, Brush colorBrush,
                           int layer) {
        Thread thread = new Thread(() -> {
            var pathArr = path.toArray();
            for ( int index = 0; index < pathArr.length - 1; index ++ ) {
                try {
                    Thread.sleep(800);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                String currNode = ((Node) pathArr[index]).getName();
                String preNode =  ((Node) pathArr[index + 1]).getName();
                var currDigNode = diagramNodes.get(currNode);
                diagramNodes.get(preNode).setBrush(Brushes.DarkOrange);
                var links = currDigNode.getOutgoingLinks();
                for ( DiagramLink link : links ) {
                    if ( link.getDestination() == diagramNodes.get(preNode)) {
                        if ( link.getPen() != null ) {
                            link.setPen(Pens.YellowGreen);
                            link.setHeadPen(Pens.YellowGreen);
                        } else {
                            link.setPen(colorPen);
                            link.setHeadPen(colorPen);
                        }
                        link.setLayerIndex(layer);
                        link.setTextBrush(colorBrush);
                        break;
                    }
                }
            }
        });
        thread.start();
    }
    public GraphView(TreeMap<String, Node> nodes) {
        super("Graphic illustration for Dijkstra Algorithm. Author: Mai Ngoc Doan");
        this.nodes = nodes;
        this.diagram = new Diagram();
        Rectangle2D.Float bounds = new Rectangle2D.Float(0, 0, 15, 15);
        this.diagramNodes = toDiagramNodes(nodes.keySet(), diagram);
        DiagramView diagramView = new DiagramView(diagram);
        JScrollPane scrollPane = new JScrollPane(diagramView);
        CircularLayout layout = new CircularLayout();
        diagramSetUp(diagram, layout);
        var groupLayout = new GroupLayout(this.getContentPane());
        diagramView.setAllowLinkCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        diagramView.setBehavior(Behavior.PanAndModify);
        diagramView.setCounterDiagonalResizeCursor(new java.awt.Cursor(java.awt.Cursor.SW_RESIZE_CURSOR));
        diagramView.setHorizontalResizeCursor(new java.awt.Cursor(java.awt.Cursor.MOVE_CURSOR));
        groupLayout.setHorizontalGroup(
                groupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(scrollPane,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, 1000,
                                        Short.MAX_VALUE)
                                .addContainerGap())
        );
        groupLayout.setVerticalGroup(
                groupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(scrollPane,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, 700,
                                        Short.MAX_VALUE)
                                .addContainerGap())
        );
        this.getContentPane().setLayout(groupLayout);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.pack();
    }
}
