package huce.View;/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

import huce.Algorithm.Node.Node;
import huce.Controller.GeneratePathController;
import huce.Controller.OnClickOkController;
import huce.Controller.OnloadController;
import huce.Model.AppDB;

import java.util.TreeMap;

/**
 *
 * @author Admin
 */
public class MainApp extends javax.swing.JFrame {

    /**
     * Creates new form MainApp
     */
    public MainApp() {
        initComponents();
        new OnloadController(AppDB.getModel("")).controll(this);
        new GeneratePathController(AppDB.getModel("")).controll(this);
        new OnClickOkController(AppDB.getModel("")).controll(this);
    }
    private void initComponents() {

        javax.swing.JPanel jPanel1 = new javax.swing.JPanel();
        javax.swing.JPanel JPanelMain = new javax.swing.JPanel();
        javax.swing.JPanel jPanel2 = new javax.swing.JPanel();
        javax.swing.JPanel jPanel3 = new javax.swing.JPanel();
        javax.swing.JLabel jLabelRoot = new javax.swing.JLabel();
        jListRootNode = new javax.swing.JComboBox<>();
        javax.swing.JPanel jPanel4 = new javax.swing.JPanel();
        javax.swing.JLabel jLabelBlock = new javax.swing.JLabel();
        javax.swing.JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        jListBlockNode = new javax.swing.JList<>();
        javax.swing.JPanel jPanel5 = new javax.swing.JPanel();
        javax.swing.JLabel jLabelTo = new javax.swing.JLabel();
        jListToNode = new javax.swing.JComboBox<>();
        javax.swing.JScrollPane jScrollPane3 = new javax.swing.JScrollPane();
        jTableHistory = new javax.swing.JTable();
        jBtnGetUseCase = new javax.swing.JButton();
        javax.swing.JPanel jPanelTextEditer = new javax.swing.JPanel();
        javax.swing.JLabel jLabel1 = new javax.swing.JLabel();
        javax.swing.JScrollPane jScrollPane4 = new javax.swing.JScrollPane();
        jTextEditer = new javax.swing.JTextPane();
        jButtonOk = new javax.swing.JButton();
        jBtnGenerate = new javax.swing.JButton();
        javax.swing.JPanel jPanel7 = new javax.swing.JPanel();
        javax.swing.JLabel jLabel2 = new javax.swing.JLabel();
        jMenuBar = new javax.swing.JMenuBar();
        jMenu = new javax.swing.JMenu();
        jMenuViewNodes = new javax.swing.JMenuItem();
        jMenuImport = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        JPanelMain.setBackground(new java.awt.Color(76, 103, 147));

        jPanel2.setBackground(new java.awt.Color(76, 103, 147));

        jLabelRoot.setText("From/Root");

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
                        .addComponent(jListRootNode, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
        );

        jLabelBlock.setText("Block");

        jScrollPane1.setViewportView(jListBlockNode);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabelBlock, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(50, 50, 50)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel4Layout.setVerticalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabelBlock, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        jLabelTo.setText("To");

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
                        .addComponent(jListToNode, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
        );

        jTableHistory.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                        "Time/Date", "Testcase", "Result"
                }
        ) {
            Class[] types = new Class [] {
                    java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                    false, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(jTableHistory);

        jBtnGetUseCase.setText("Get graph");

        jPanelTextEditer.setBackground(new java.awt.Color(234, 234, 234));

        jLabel1.setText("Or type your graph");

        jScrollPane4.setViewportView(jTextEditer);

        jButtonOk.setText("Ok");

        javax.swing.GroupLayout jPanelTextEditerLayout = new javax.swing.GroupLayout(jPanelTextEditer);
        jPanelTextEditer.setLayout(jPanelTextEditerLayout);
        jPanelTextEditerLayout.setHorizontalGroup(
                jPanelTextEditerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelTextEditerLayout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(182, 182, 182))
                        .addGroup(jPanelTextEditerLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButtonOk, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(12, Short.MAX_VALUE))
        );
        jPanelTextEditerLayout.setVerticalGroup(
                jPanelTextEditerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanelTextEditerLayout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanelTextEditerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanelTextEditerLayout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jScrollPane4)
                                                .addGap(35, 35, 35))
                                        .addGroup(jPanelTextEditerLayout.createSequentialGroup()
                                                .addGap(61, 61, 61)
                                                .addComponent(jButtonOk, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jBtnGenerate.setText("Generate Path");

        jPanel7.setBackground(new java.awt.Color(55, 41, 72));

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setText("@nhom1");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
                jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel7Layout.setVerticalGroup(
                jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap(113, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(jBtnGenerate, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jBtnGetUseCase, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGap(55, 55, 55)
                                                .addComponent(jPanelTextEditer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addContainerGap(114, Short.MAX_VALUE))
                        .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap(155, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jPanelTextEditer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(63, 63, 63)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jBtnGetUseCase, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jBtnGenerate, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(62, 62, 62)
                                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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

        jMenuViewNodes.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuViewNodes.setText("View Nodes");
        jMenu.add(jMenuViewNodes);

        jMenuImport.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuImport.setText("Import graph");
        jMenu.add(jMenuImport);

        jMenuBar.add(jMenu);

        setJMenuBar(jMenuBar);

        pack();
        setLocationRelativeTo(null);
    }
    public void prepareNodes(TreeMap<String, Node> nodes) {
        this.jListRootNode.removeAllItems();
        this.jListToNode.removeAllItems();
        for (String nodeName : nodes.keySet()) {
            this.jListRootNode.addItem(nodeName);
            this.jListToNode.addItem(nodeName);
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainApp().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify
    public javax.swing.JButton jBtnGenerate;
    public javax.swing.JButton jBtnGetUseCase;
    public javax.swing.JButton jButtonOk;
    private javax.swing.JList<String> jListBlockNode;
    public javax.swing.JComboBox<String> jListRootNode;
    public javax.swing.JComboBox<String> jListToNode;
    private javax.swing.JMenu jMenu;
    private javax.swing.JMenuBar jMenuBar;
    private javax.swing.JMenuItem jMenuImport;
    private javax.swing.JMenuItem jMenuViewNodes;
    private javax.swing.JTable jTableHistory;
    public javax.swing.JTextPane jTextEditer;
    // End of variables declaration
}
