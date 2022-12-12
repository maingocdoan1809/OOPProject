package huce.Controller;

import huce.View.FileSelectorView;
import huce.View.MainApp;

import java.awt.event.ActionEvent;

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