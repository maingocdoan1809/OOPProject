package huce.Controller;

import huce.Exception.GraphvizFileFormatException;
import huce.Exception.NoDataException;
import huce.Model.AppDB;
import huce.Model.Observer;
import huce.View.FileSelectorView;
import huce.View.GraphView;
import huce.View.MainApp;

import java.awt.event.ActionEvent;
import java.io.FileInputStream;
import java.io.IOException;

public class OnChooseFileController extends Controller{

    @Override
    public void controll(MainApp myapp) {

        myapp.jMenuImport.addActionListener(
                (ActionEvent event) -> {
                    FileSelectorView fileChooser = new FileSelectorView();
                    fileChooser.observe(myapp);
                    fileChooser.setVisible(true);
                }
        );


    }
}
