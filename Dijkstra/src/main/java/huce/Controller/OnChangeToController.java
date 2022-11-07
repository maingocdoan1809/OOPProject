package huce.Controller;

import huce.Model.AppDB;
import huce.View.MainApp;

import java.awt.event.ActionEvent;

public class OnChangeToController extends Controller {
    public OnChangeToController(AppDB database) {
        super(database);
    }

    @Override
    public void controll(MainApp myapp) {
        myapp.jListToNode.addActionListener( (ActionEvent e) -> {
            var nodes = database.getNodes().keySet().toArray();
            myapp.repaintBlockList(nodes);
        } );
    }
}
