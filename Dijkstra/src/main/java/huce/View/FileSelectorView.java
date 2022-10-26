package huce.View;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.io.File;

public class FileSelectorView extends JFrame{
    private static File file = null;
    synchronized public static File getFile() {
        return file;
    }
    public FileSelectorView() {
        super("Select a .dot file");
        JPanel mainPanel = new JPanel();
        JFileChooser fileChooser = new JFileChooser() {
            @Override
            public void approveSelection() {
                FileSelectorView.file = this.getSelectedFile();
                FileSelectorView.super.dispose();
            }

            @Override
            public void cancelSelection() {
                FileSelectorView.super.dispose();
                file = null;
            }
        };
        fileChooser.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                return f.toString().endsWith(".dot") || f.toString().endsWith(".txt") || f.isDirectory();
            }
            @Override
            public String getDescription() {
                return null;
            }
        });
        fileChooser.changeToParentDirectory();
        mainPanel.setSize(500, 500);
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(fileChooser, BorderLayout.CENTER);
        this.add(mainPanel);
        this.pack();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }

}
