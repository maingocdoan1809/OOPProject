package huce.View;

import com.mindfusion.diagramming.Shape;
import com.mindfusion.diagramming.*;
import com.mindfusion.diagramming.SpringLayout;
import com.mindfusion.drawing.*;
import huce.Algorithm.Node.Node;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.sql.Connection;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class GraphView extends JFrame {
    TreeMap<String, DiagramNode> diagramNodes;
    TreeMap<String, Node> nodes;
    Diagram diagram;
    private javax.swing.JButton jBtnAnelLayout;
    private javax.swing.JButton jBtnCirLayout;
    private javax.swing.JButton jBtnReload;
    private javax.swing.JButton jBtnSpringLayout;
    private javax.swing.JButton jBtnStart;
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
    synchronized public void drawPath(TreeSet<Node> path, Pen colorPen,
                                      Brush colorBrush, int layer) {
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
                if ( index < path.size() - 2  ) {
                    diagramNodes.get(preNode).setBrush(Brushes.DarkOrange);
                }
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

    private void initComponents(TreeMap<String, Node> nodes) {


        javax.swing.JPanel jPanel1 = new javax.swing.JPanel();

        this.nodes = nodes;
        this.diagram = new Diagram();
//        Rectangle2D.Float bounds = new Rectangle2D.Float(0, 0, 15, 15);
        this.diagramNodes = toDiagramNodes(nodes.keySet(), diagram);
        com.mindfusion.diagramming.DiagramView diagramView =
                new com.mindfusion.diagramming.DiagramView(diagram);
        javax.swing.JScrollPane jScrollPane1 = new javax.swing.JScrollPane(diagramView);
        CircularLayout circularLayoutlayout = new CircularLayout();
        circularLayoutlayout.setRadius(70f);
        diagramSetUp(diagram, circularLayoutlayout);
        jBtnReload = new javax.swing.JButton();
        jBtnStart = new javax.swing.JButton();
        jBtnAnelLayout = new javax.swing.JButton();
        jBtnCirLayout = new javax.swing.JButton();
        jBtnSpringLayout = new javax.swing.JButton();
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jScrollPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jScrollPane1.setMaximumSize(new java.awt.Dimension(3000, 3000));
        jScrollPane1.setName(""); // NOI18N
        jScrollPane1.setVerifyInputWhenFocusTarget(false);
        jScrollPane1.setViewportView(diagramView);

        diagramView.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        diagramView.setHorizontalResizeCursor(new java.awt.Cursor(java.awt.Cursor.E_RESIZE_CURSOR));
        diagramView.setMaximumSize(new java.awt.Dimension(3276, 3276));
        diagramView.setMinimumSize(new java.awt.Dimension(500, 500));
        diagramView.setName(""); // NOI18N
        diagramView.setPreferredSize(new java.awt.Dimension(500, 500));
        diagramView.setBehavior(Behavior.PanAndModify);
        javax.swing.GroupLayout diagramViewLayout = new javax.swing.GroupLayout(diagramView);
        diagramView.setLayout(diagramViewLayout);
        diagramViewLayout.setHorizontalGroup(
                diagramViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 3780, Short.MAX_VALUE)
        );
        diagramViewLayout.setVerticalGroup(
                diagramViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 3780, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(diagramView);

        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        jBtnReload.setText("Reload");
        jPanel1.add(jBtnReload);

        jBtnStart.setLabel("Start");
        jPanel1.add(jBtnStart);

        jBtnAnelLayout.setLabel("AnnealLayout");
        jPanel1.add(jBtnAnelLayout);

        jBtnCirLayout.setText("Circular Layout");
        jPanel1.add(jBtnCirLayout);

        jBtnSpringLayout.setLabel("Spring Layout");
        jPanel1.add(jBtnSpringLayout);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1200,
                                Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, 750,
                                        Short.MAX_VALUE)
                                .addGap(0, 0, 0)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        pack();
    }// </editor-fold>
    public GraphView(TreeMap<String, Node> nodes) {
        super("Graphic illustration for Dijkstra Algorithm. Author: Mai Ngoc Doan");
        initComponents(nodes);
        this.jBtnSpringLayout.addActionListener( (e) -> {
            SpringLayout layout = new SpringLayout();
            layout.setNodeDistance(60);
            layout.setMinimizeCrossings(true);
            layout.arrange(diagram);
        } );
        this.jBtnCirLayout.addActionListener( (e) -> {
            CircularLayout circularLayout = new CircularLayout();
            circularLayout.setRadius(60f);
            diagramSetUp(diagram, circularLayout);
        } );
        this.jBtnAnelLayout.addActionListener( (e) -> {
            AnnealLayout annealLayout = new AnnealLayout();
            annealLayout.setCrossingLinksCost(10);
            annealLayout.setNodeLinkDistFactor(10);
            annealLayout.setLinkLengthFactor(0.06);
            annealLayout.arrange(diagram);
        } );

    }
    public void addStartEvent(PriorityQueue<TreeSet<Node>> paths) {
        this.jBtnStart.addActionListener(e -> {
            Pen[] pens = new Pen[] {Pens.Green, Pens.Yellow,
                    Pens.OrangeRed};
            Brush[] brushes = new Brush[]{Brushes.Green, Brushes.Orange,
                    Brushes.OrangeRed};
            // no more than 3 paths will be printed:
            Thread drawPaths = new Thread( ()-> {
                int index = 0;
                for ( var path : paths ) {
                    if (index == 3) {
                        break;
                    }
                    drawPath(path,pens[index], brushes[index], 5 - index );
                    index ++;
                }
            } );
            drawPaths.start();
        });

    }
    public void addReloadEvent(PriorityQueue<TreeSet<Node>> paths) {
        this.jBtnReload.addActionListener(e -> {
            for (var path : paths) {
                var pathArr = path.toArray();
                for ( int index = 0; index < pathArr.length - 1; index ++ ) {
                    String currNode = ((Node) pathArr[index]).getName();
                    String preNode =  ((Node) pathArr[index + 1]).getName();
                    var currDigNode = diagramNodes.get(currNode);
                    if ( index < path.size() - 2  ) {
                        diagramNodes.get(preNode).setBrush(null);
                    }
                    var links = currDigNode.getOutgoingLinks();
                    for ( DiagramLink link : links ) {
                        if ( link.getDestination() == diagramNodes.get(preNode)) {
                            link.setPen(null);
                            link.setHeadPen(null);
                            link.setTextBrush(null);
                            break;
                        }
                    }
                }
            }
        });
    }

}
