package huce.View;

import com.mindfusion.diagramming.Shape;
import com.mindfusion.diagramming.SpringLayout;
import com.mindfusion.diagramming.*;
import com.mindfusion.drawing.*;
import huce.Algorithm.Node.Node;

import javax.swing.*;
import javax.swing.Action;
import java.awt.*;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.util.*;

public class GraphView extends JFrame {
    private TreeMap<String, DiagramNode> diagramNodes;
    private TreeMap<String, Node> nodes;
    private Diagram diagram;
    private DiagramView diagramView;
    private javax.swing.JButton jBtnAnelLayout;
    private javax.swing.JButton jBtnCirLayout;
    private javax.swing.JButton jBtnReload;
    private javax.swing.JButton jBtnSpringLayout;
    private javax.swing.JButton jBtnStart;
    private JComboBox<String> jBoxColorStart;
    private JComboBox<String> jBoxColorTo;
    private JComboBox<String> jBoxColorBlock;
    public static HashMap<String, Brush> brushes = new HashMap<String, Brush>( Map.of(
            "Default", Brushes.BlueViolet,
            "Red", Brushes.Red,
            "Yellow", Brushes.Yellow,
            "Yellow Green", Brushes.YellowGreen,
            "Grey", Brushes.Gray,
            "Blue", Brushes.Blue,
            "Snow", Brushes.Snow,
            "ForestGreen", Brushes.ForestGreen,
            "Light Pink", Brushes.LightPink
    ) );
    static private TreeMap<String, DiagramNode> toDiagramNodes(Set<String> nodes,
                                                               Diagram diagram) {
        TreeMap<String, DiagramNode> diagramNodes = new TreeMap<>();
        Rectangle2D.Float bounds = new Rectangle2D.Float(0, 0, 13, 13);
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
        highlightNode( src.getName() , color );
    }
    public void highlightNode(String srcName, Brush color) {
        DiagramNode from = this.diagramNodes.get(srcName);
        if ( from != null ) {
            from.setBrush(color);
        }
    }
    private static void diagramSetUp(Diagram diagram, AbstractLayout layout) {
        layout.arrange(diagram);
        diagram.setShowHandlesOnDrag(true);
        diagram.setAllowLinksRepeat(false);
    }
    synchronized public void drawPath(TreeSet<Node> path, Pen colorPen,
                                      Brush colorBrush, int layer) {
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
    }

    private void initComponents() {
        javax.swing.JPanel jPanel1 = new javax.swing.JPanel();
        JPanel jMainPanel = new JPanel();
        jMainPanel.setLayout(new GridLayout(2, 0));
        JPanel jBoxPanel = new JPanel();
        jMainPanel.add(jPanel1);
        jMainPanel.add(jBoxPanel);
        javax.swing.JScrollPane jScrollPane1 = new javax.swing.JScrollPane(diagramView);
        CircularLayout circularLayoutlayout = new CircularLayout();
        circularLayoutlayout.setRadius(45f);
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
        diagramView.setMaximumSize(new java.awt.Dimension(3000, 3000));
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
        jBoxPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));
        jBtnReload.setText("Reload");
        jPanel1.add(jBtnReload);
        jBtnReload.setBackground(new java.awt.Color(224, 20, 76));
        jBtnReload.setForeground(new Color(255, 255, 255));
        jBtnStart.setLabel("Start");
        jBtnStart.setBackground(new java.awt.Color(224, 20, 76));
        jBtnStart.setForeground(new Color(255, 255, 255));
        jPanel1.add(jBtnStart);

        jBtnAnelLayout.setLabel("AnnealLayout");
        jBtnAnelLayout.setBackground(new java.awt.Color(224, 20, 76));
        jBtnAnelLayout.setForeground(new Color(255, 255, 255));
        jPanel1.add(jBtnAnelLayout);

        jBtnCirLayout.setText("Circular Layout");
        jBtnCirLayout.setBackground(new java.awt.Color(224, 20, 76));
        jBtnCirLayout.setForeground(new Color(255, 255, 255));
        jPanel1.add(jBtnCirLayout);

        jBtnSpringLayout.setLabel("Spring Layout");
        jBtnSpringLayout.setBackground(new java.awt.Color(224, 20, 76));
        jBtnSpringLayout.setForeground(new Color(255, 255, 255));
        jPanel1.add(jBtnSpringLayout);


        JButton jBtnZoomIn = new JButton("Zoom in");
        JButton jBtnZoomOut = new JButton("Zoom out");
        jBtnZoomIn.setBackground(new java.awt.Color(224, 20, 76));
        jBtnZoomIn.setForeground(new Color(255, 255, 255));
        jBtnZoomOut.setBackground(new java.awt.Color(224, 20, 76));
        jBtnZoomOut.setForeground(new Color(255, 255, 255));
        jBtnZoomIn.addActionListener((evt) -> {
            this.diagramView.setZoomFactor( diagramView.getZoomFactor() + 10 );
        });
        jBtnZoomOut.addActionListener((evt) -> {
            this.diagramView.setZoomFactor( diagramView.getZoomFactor() - 10 );
        });
        jPanel1.add(jBtnZoomIn);
        jPanel1.add(jBtnZoomOut);
        jBoxPanel.add(new JLabel("From Color: "));


        this.jBoxColorStart = new JComboBox<>(new DefaultComboBoxModel<>(
                new String[]{"Default","Red","Yellow","Yellow Green","Grey","Blue","Snow","ForestGreen","Light Pink"}
        ));
        jBoxPanel.add(this.jBoxColorStart);
        jBoxPanel.add(new JLabel("To Color: "));
        this.jBoxColorTo = new JComboBox<>(new DefaultComboBoxModel<>(
                new String[]{"Default","Red","Yellow","Yellow Green","Grey","Blue","Snow","ForestGreen","Light Pink"}
        ));
        jBoxPanel.add(this.jBoxColorTo);
        jBoxPanel.add(new JLabel("Block Color: "));
        this.jBoxColorBlock = new JComboBox<>(new DefaultComboBoxModel<>(
                new String[]{"Default","Red","Yellow","Yellow Green","Grey","Blue","Snow","ForestGreen","Light Pink"}
        ));
        jBoxPanel.add(this.jBoxColorBlock);
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(jScrollPane1, BorderLayout.CENTER);
        getContentPane().add(jMainPanel, BorderLayout.SOUTH);
        diagramView.setZoomFactor(110f);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
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
        diagram.setAutoSnapLinks(true);
        diagram.setAllowSplitLinks(false);
        diagramView.setAllowLinkCursor(new Cursor(Cursor.MOVE_CURSOR));
        this.setLocationRelativeTo(null);
        pack();
    }// </editor-fold>
    public void setZoomFactor(float factor) {
        this.diagramView.setZoomFactor(factor);
    }
    public GraphView(TreeMap<String, Node> nodes) {
        this.nodes = nodes;
        this.diagram = new Diagram();
        this.diagramNodes = toDiagramNodes(nodes.keySet(), diagram);
        this.diagramView = new DiagramView(diagram);
        initComponents();
    }
    public void addStartEvent(ActionListener event) {
        this.jBtnStart.addActionListener(event);

    }
    public void clickReload() {

        this.jBtnReload.doClick(0);
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
    public void addColorChooser(JComboBox<String> src, JComboBox<String> to) {
        this.jBoxColorStart.addActionListener((evt) -> {
            String srcNode = (String) src.getSelectedItem();
            highlightNode(srcNode,
                    GraphView.brushes.get( (String) this.jBoxColorStart.getSelectedItem()));
        });
        this.jBoxColorTo.addActionListener((evt) -> {
            String toNode = (String) to.getSelectedItem();
            highlightNode(toNode,
                    GraphView.brushes.get( (String) this.jBoxColorTo.getSelectedItem()));
        });
        this.jBoxColorBlock.addActionListener((evt) -> {
            String srcNode = (String) src.getSelectedItem();
            for ( var blockingNode : nodes.get(srcNode).getBlocked() ) {
                highlightNode(blockingNode,
                        GraphView.brushes.get( (String) this.jBoxColorBlock.getSelectedItem()));
            }
        });
    }
    public String getRootColor() {
        return (String) this.jBoxColorStart.getSelectedItem();
    }
    public String getToColor() {
        return (String) this.jBoxColorTo.getSelectedItem();
    }
    public String getBlockColor() {
        return (String) this.jBoxColorBlock.getSelectedItem();
    }
    public DiagramView getDiagramView() {
        return this.diagramView;
    }
}