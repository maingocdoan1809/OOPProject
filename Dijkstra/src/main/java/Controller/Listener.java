package Controller;

import huce.Model.AppDB;
import huce.View.App;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Listener {
    App myApp;
    AppDB myDatabase;
    public Listener(App myApp, AppDB myDatabase) {
        this.myApp = myApp;
        this.myDatabase = myDatabase;
    }
    public ActionListener userClickBtnGenerate() {
        return  new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        };
    }
}
