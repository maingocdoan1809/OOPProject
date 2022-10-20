package huce.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class FileSelectorView extends JFrame {
    JFileChooser fileChooser = new JFileChooser();
    public FileSelectorView() {
        super("Select a .dot file");
        JPanel mainPanel = new JPanel();
        this.fileChooser = new JFileChooser() {
            @Override
            public void approveSelection() {
                System.out.println(this.getSelectedFile());
            }
            @Override
            public void cancelSelection() {

            }
        };
        fileChooser.changeToParentDirectory();
        mainPanel.setSize(500, 500);
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(fileChooser, BorderLayout.CENTER);
        this.add(mainPanel);
        this.pack();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }
    public File getFile() {
        return fileChooser.getSelectedFile();
    }

    public static void main(String[] args) {
        FileSelectorView file = new FileSelectorView();
    }
}
