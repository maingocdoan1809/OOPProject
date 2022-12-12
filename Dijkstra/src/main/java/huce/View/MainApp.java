package huce.View;/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

import com.mindfusion.common.Pair;
import com.mindfusion.diagramming.DiagramView;
import huce.Algorithm.Node.Node;
import huce.Controller.*;
import huce.Exception.GraphvizFileFormatException;
import huce.Exception.NoDataException;
import huce.Model.AppDB;
import huce.Model.Observer;
import huce.Model.Subject;

import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;

/**
 *
 * @author Admin
 */
public class MainApp extends javax.swing.JFrame implements Subject {

    /**
     * Creates new form MainApp
     */
    public MainApp() {
        initComponents();
        new OnloadController().controll(this);
        new OnClickChooseController().controll(this);
        new OnChooseFileController().controll(this);
        new OnChangeToController().controll(this);
        new OnChangeRootController().controll(this);
        new OnClickBlockController().controll(this);
        new OnRemoveBlockController().controll(this);
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        javax.swing.JPanel jPanel1 = new javax.swing.JPanel();
        javax.swing.JPanel JPanelMain = new javax.swing.JPanel();
        javax.swing.JPanel jPanel2 = new javax.swing.JPanel();
        javax.swing.JPanel jPanel6 = new javax.swing.JPanel();
        javax.swing.JPanel jPanel5 = new javax.swing.JPanel();
        javax.swing.JLabel jLabelTo = new javax.swing.JLabel();
        jListToNode = new javax.swing.JComboBox<>();
        javax.swing.JPanel jPanel3 = new javax.swing.JPanel();
        javax.swing.JLabel jLabelRoot = new javax.swing.JLabel();
        jListRootNode = new javax.swing.JComboBox<>();
        javax.swing.JPanel jPanel7 = new javax.swing.JPanel();
        javax.swing.JLabel jLabelTo1 = new javax.swing.JLabel();
        jListToNodeBlock = new javax.swing.JComboBox<>();
        javax.swing.JPanel jPanel12 = new javax.swing.JPanel();
        javax.swing.JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        jTableBlock = new javax.swing.JTable();
        javax.swing.JPanel jPanel13 = new javax.swing.JPanel();
        jBtnOKBlock = new javax.swing.JButton();
        jBtnRemove = new javax.swing.JButton();
        javax.swing.JPanel jPanel4 = new javax.swing.JPanel();
        javax.swing.JPanel jPanel8 = new javax.swing.JPanel();
        javax.swing.JPanel jPanel10 = new javax.swing.JPanel();
        jBtnChoose = new javax.swing.JButton();
        javax.swing.JScrollPane jScrollPane2 = new javax.swing.JScrollPane();
        jTestcaseTable = new javax.swing.JTable();
        javax.swing.JLabel jLabel2 = new javax.swing.JLabel();
        javax.swing.JPanel jPanel9 = new javax.swing.JPanel();
        javax.swing.JLabel jLabel1 = new javax.swing.JLabel();
        jPanelPreview = new javax.swing.JPanel();
        jMenuBar = new javax.swing.JMenuBar();
        jMenu = new javax.swing.JMenu();
        jMenuImport = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        JPanelMain.setBackground(new java.awt.Color(178, 178, 178));

        jPanel2.setBackground(new java.awt.Color(234, 234, 234));

        jPanel6.setBackground(new java.awt.Color(60, 64, 72));

        jPanel5.setBackground(new java.awt.Color(60, 64, 72));

        jLabelTo.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTo.setText("To");

        jListToNode.setBorder(null);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
                jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel5Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabelTo, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(50, 50, 50)
                                .addComponent(jListToNode, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel5Layout.setVerticalGroup(
                jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabelTo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jListToNode, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        jPanel3.setBackground(new java.awt.Color(60, 64, 72));

        jLabelRoot.setForeground(new java.awt.Color(255, 255, 255));
        jLabelRoot.setText("From/Root");

        jListRootNode.setBorder(null);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabelRoot, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(50, 50, 50)
                                .addComponent(jListRootNode, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabelRoot, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jListRootNode, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        jPanel7.setBackground(new java.awt.Color(60, 64, 72));

        jLabelTo1.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTo1.setText("Block");

        jListToNodeBlock.setBorder(null);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
                jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel7Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabelTo1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(50, 50, 50)
                                .addComponent(jListToNodeBlock, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel7Layout.setVerticalGroup(
                jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabelTo1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jListToNodeBlock, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        jPanel12.setLayout(new java.awt.BorderLayout());

        jScrollPane1.setBackground(new java.awt.Color(60, 64, 72));
        jScrollPane1.setBorder(null);
        jScrollPane1.setForeground(new java.awt.Color(60, 64, 72));

        jTableBlock.setBackground(new java.awt.Color(60, 64, 72));
        jTableBlock.setForeground(new java.awt.Color(255, 255, 255));
        jTableBlock.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                        "Name"
                }
        ) {
            final boolean[] canEdit = new boolean [] {
                    false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableBlock.setGridColor(new java.awt.Color(60, 64, 72));
        jTableBlock.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jTableBlock.setShowGrid(false);
        jTableBlock.setShowHorizontalLines(true);
        jTableBlock.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTableBlock);

        jPanel12.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel13.setBackground(new java.awt.Color(60, 64, 72));

        jBtnOKBlock.setBackground(new java.awt.Color(224, 20, 76));
        jBtnOKBlock.setForeground(new java.awt.Color(255, 255, 255));
        jBtnOKBlock.setText("Block");
        jPanel13.add(jBtnOKBlock);

        jBtnRemove.setBackground(new java.awt.Color(224, 20, 76));
        jBtnRemove.setForeground(new java.awt.Color(255, 255, 255));
        jBtnRemove.setText("Remove");
        jPanel13.add(jBtnRemove);

        jPanel12.add(jPanel13, java.awt.BorderLayout.PAGE_END);


        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
                jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                                        ))
                                        .addGroup(jPanel6Layout.createSequentialGroup()
                                                .addGap(15, 15, 15)
                                                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
                jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(76, 76, 76)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(82, 82, 82)
                                .addContainerGap(151, Short.MAX_VALUE))
        );
        jPanel4.setLayout(new CardLayout());

        JSplitPane slider = new JSplitPane();
        jPanel4.add(slider);

        jPanel8.setLayout(new java.awt.BorderLayout());

        jPanel10.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 10, 10));

        jBtnChoose.setBackground(new java.awt.Color(224, 20, 76));
        jBtnChoose.setForeground(new java.awt.Color(255, 255, 255));
        jBtnChoose.setText("Choose");
        jPanel10.add(jBtnChoose);

        jPanel8.add(jPanel10, java.awt.BorderLayout.PAGE_END);

        jTestcaseTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                        "ID", "Name", "Graph"
                }
        ) {
            final boolean[] canEdit = new boolean [] {
                    false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTestcaseTable.setGridColor(new java.awt.Color(57, 62, 70));
        jTestcaseTable.setRowHeight(30);
        jTestcaseTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTestcaseTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTestcaseTable.setShowGrid(true);
        jTestcaseTable.setSurrendersFocusOnKeystroke(true);
        jTestcaseTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(jTestcaseTable);

        jPanel8.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jLabel2.setFont(new java.awt.Font("Cascadia Mono", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(60, 64, 72));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Available testcases");
        jLabel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        jPanel8.add(jLabel2, java.awt.BorderLayout.PAGE_START);

        slider.add(jPanel8, JSplitPane.LEFT);

        jPanel9.setLayout(new java.awt.BorderLayout());

        jLabel1.setFont(new java.awt.Font("Cascadia Mono", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(60, 64, 72));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Preview");
        jLabel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        jPanel9.add(jLabel1, java.awt.BorderLayout.PAGE_START);

        jPanelPreview.setLayout(new java.awt.CardLayout());
        jPanel9.add(jPanelPreview, java.awt.BorderLayout.CENTER);

        slider.add(jPanel9, JSplitPane.RIGHT);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 726, Short.MAX_VALUE)
                                .addGap(0, 0, 0))
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout JPanelMainLayout = new javax.swing.GroupLayout(JPanelMain);
        JPanelMain.setLayout(JPanelMainLayout);
        JPanelMainLayout.setHorizontalGroup(
                JPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        JPanelMainLayout.setVerticalGroup(
                JPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(JPanelMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(JPanelMain, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        jMenuBar.setBackground(new java.awt.Color(255, 255, 255));
        jMenuBar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jMenuBar.setForeground(new java.awt.Color(0, 0, 0));
        jMenuBar.setMargin(new java.awt.Insets(5, 0, 5, 0));

        jMenu.setText("Menu");
        jMenu.setToolTipText("");
        jMenu.setMargin(new java.awt.Insets(3, 16, 3, 16));

        jMenuImport.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuImport.setText("Import graph");
        jMenu.add(jMenuImport);

        jMenuBar.add(jMenu);

        setJMenuBar(jMenuBar);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>

    public void repaintRoot(Object[] nodeName) {
        this.jListRootNode.removeAllItems();
        for ( var node : nodeName ) {
              this.jListRootNode.addItem( (String) node);
        }

    }
    public  void repaintToList(Object[] nodeName) {
        this.jListToNode.removeAllItems();
        for (var node: nodeName) {
            this.jListToNode.addItem((String) node);
        }
        this.jListToNode.removeItem(this.jListRootNode.getSelectedItem());
    }
    public  void repaintBlockList(Object[] nodeName) {
        this.jListToNodeBlock.removeAllItems();
        for (var node: nodeName) {
            this.jListToNodeBlock.addItem( (String) node);
        }
        this.jListToNodeBlock.removeItem(this.jListRootNode.getSelectedItem());
        this.jListToNodeBlock.removeItem(this.jListToNode.getSelectedItem());
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException |
                 IllegalAccessException | UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainApp().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify
    public javax.swing.JButton jBtnChoose;
    public javax.swing.JButton jBtnOKBlock;
    public javax.swing.JButton jBtnRemove;
    public javax.swing.JComboBox<String> jListRootNode;
    public javax.swing.JComboBox<String> jListToNode;
    public javax.swing.JComboBox<String> jListToNodeBlock;
    private javax.swing.JMenu jMenu;
    private javax.swing.JMenuBar jMenuBar;
    public javax.swing.JMenuItem jMenuImport;
    public javax.swing.JPanel jPanelPreview;
    public javax.swing.JTable jTableBlock;
    public javax.swing.JTable jTestcaseTable;
    public GraphView graphView = null;
    public Pair<Node, Node> rootAndTo = new Pair<>(null, null);
    @Override
    public void update(Observer observer) {
        try (FileInputStream fi =
                     new FileInputStream( ((FileSelectorView) observer).getSelectedFile() )) {
            createGraph(new String(fi.readAllBytes()) );
        } catch (IOException | NoDataException | GraphvizFileFormatException e) {
            throw new RuntimeException(e);
        }
    }
    public void setRootAndTo(Node root, Node to) {
        if ( root != null ) {
            rootAndTo.setFirst(root);
        }
        if ( to != null ) {
            rootAndTo.setSecond(to);
        }
    }
    public void createGraph(String graphData) throws NoDataException, GraphvizFileFormatException {
        var database = AppDB.getModel("");
        database.toNodes( graphData);
        var nodes = database.getNodes().keySet().toArray();

        GraphView viewNodes = new GraphView(database.getNodes());
        viewNodes.setZoomFactor(75f);
        viewNodes.drawGraph();
        this.jPanelPreview.removeAll();
        this.jPanelPreview.revalidate();
        this.jPanelPreview.repaint();
        this.jPanelPreview.add(viewNodes.getContentPane());
        this.repaintRoot(nodes);
        this.repaintToList(nodes);
        this.repaintBlockList(nodes);
        String selectedRoot = (String) this.jListRootNode.getSelectedItem();
        String selectedTo = (String) this.jListToNode.getSelectedItem();
        setRootAndTo(database.getNodes().get( selectedRoot ),
                database.getNodes().get( selectedTo ) );
        this.graphView = viewNodes;
        this.graphView.highlightNode(selectedRoot, GraphView.brushes.get("Default"));
        this.graphView.highlightNode(selectedTo, GraphView.brushes.get("Default"));
        this.graphView.addColorChooser(this.jListRootNode, jListToNode);
        new OnGeneratePathController().controll(this);
    }
    // End of variables declaration
}