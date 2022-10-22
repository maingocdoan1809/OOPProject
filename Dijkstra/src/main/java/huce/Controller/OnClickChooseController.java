package huce.Controller;

import huce.Exception.GraphvizFileFormatException;
import huce.Model.AppDB;
import huce.View.MainApp;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;

public class OnClickChooseController extends Controller{
    public OnClickChooseController(AppDB database) {
        super(database);
    }
    @Override
    public void controll(MainApp myapp) {
        myapp.jBtnChoose.addActionListener(
                (ActionEvent event) -> {
                    DefaultTableModel tableModel =
                            (DefaultTableModel) myapp.jTestcaseTable.getModel();

                    int rowSelected = myapp.jTestcaseTable.getSelectedRow();
                    if ( rowSelected == -1 ) {
                        JOptionPane.showMessageDialog(myapp, "Please select a usecase " +
                                "from the usecase table first!");
                        return;
                    }
                    String graph = (String) tableModel.getValueAt(rowSelected, 2);
                    try {
                        super.database.toNodes(graph);

                    } catch ( GraphvizFileFormatException err) {
                        JOptionPane.showMessageDialog(myapp, err.getMessage());
                    }
                    var nodes = super.database.getNodes();
                    myapp.prepareNodes(nodes);
                }
        );
    }
}
