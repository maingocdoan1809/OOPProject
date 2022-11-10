package huce.Controller;

import huce.Exception.GraphvizFileFormatException;
import huce.Exception.NoDataException;
import huce.Model.AppDB;
import huce.View.FileSelectorView;
import huce.View.MainApp;

import java.awt.event.ActionEvent;
import java.io.FileInputStream;
import java.io.IOException;

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
                        System.out.println("Start...");
                        while (FileSelectorView.getFile() == null) {
                            if ( FileSelectorView.isCancel ) {
                                FileSelectorView.isCancel = false;
                                System.out.println("Stop");
                                return;
                            }
                        }

                        try (FileInputStream fi =
                                     new FileInputStream(FileSelectorView.getFile())) {
                            database.toNodes( new String(fi.readAllBytes()) );

                            var nodes = database.getNodes().keySet().toArray();
                            myapp.repaintRoot(nodes);
                            myapp.repaintToList(nodes);
                            myapp.repaintBlockList(nodes);
                        } catch (IOException | NoDataException |
                                 GraphvizFileFormatException e) {
                            throw new RuntimeException(e);
                        }
                        System.out.println("stop");
                    });
                    t.start();
                }

        );


    }
}
