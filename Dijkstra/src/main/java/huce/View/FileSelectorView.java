package huce.View;

import huce.Model.Observer;
import huce.Model.Subject;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

public class FileSelectorView extends JFrame implements Observer {
    private Subject subject;
    JFileChooser fileChooser;
    public FileSelectorView() {
        super("Select a .dot file");
        JPanel mainPanel = new JPanel();
        fileChooser = new JFileChooser() {
            @Override
            public void approveSelection() {
                FileSelectorView.super.dispose();
                notifySubject();
            }
            @Override
            public void cancelSelection() {
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
        mainPanel.setSize(500, 500);
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(fileChooser, BorderLayout.CENTER);
        this.add(mainPanel);
        this.pack();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }
    public File getSelectedFile() {
        return this.fileChooser.getSelectedFile();
    }
    @Override
    public void observe(Subject subject) {
        this.subject = subject;
    }

    @Override
    public void notifySubject() {
        subject.update(this);
    }
}
