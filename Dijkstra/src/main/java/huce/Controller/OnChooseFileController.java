package huce.Controller;

import huce.Exception.GraphvizFileFormatException;
import huce.Exception.NoDataException;
import huce.Model.AppDB;
import huce.View.FileSelectorView;
import huce.View.MainApp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.nio.file.Path;
import java.util.Scanner;

public class OnChooseFileController extends Controller{
    public OnChooseFileController(AppDB database) {
        super(database);
    }

    @Override
    public void controll(MainApp myapp) {

        myapp.jMenuImport.addActionListener(
                (ActionEvent event) -> {
                    FileSelectorView fileChooser = new FileSelectorView();
                    fileChooser.setVisible(true);
                    Thread t = new Thread(() -> {
                        while ( FileSelectorView.getFile() == null);
                        try (FileInputStream fi =
                                     new FileInputStream(FileSelectorView.getFile())) {
                            database.toNodes( new String(fi.readAllBytes()) );
                            myapp.prepareNodes(database.getNodes());
                        } catch (IOException | NoDataException |
                                 GraphvizFileFormatException e) {
                            throw new RuntimeException(e);
                        }

                    });
                    t.start();
                }

        );


    }
}
