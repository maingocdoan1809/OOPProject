package huce.View;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

public class FileSelectorView extends JFrame{
    private static File file = null;
    public  static Boolean isCancel = false;
    synchronized public static File getFile() {
        return file;
    }
    public FileSelectorView() {
        super("Select a .dot file");
        JPanel mainPanel = new JPanel();
        isCancel = false;
        JFileChooser fileChooser = new JFileChooser() {
            @Override
            public void approveSelection() {
                FileSelectorView.file = this.getSelectedFile();
                FileSelectorView.super.dispose();
            }

            @Override
            public void cancelSelection() {
                file = null;
                isCancel = true;
                FileSelectorView.super.dispose();
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
        this.addWindowListener(
                new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        isCancel = true;
                        file = null;
                    }
                }
        );
        this.setLocationRelativeTo(null);
    }

}
