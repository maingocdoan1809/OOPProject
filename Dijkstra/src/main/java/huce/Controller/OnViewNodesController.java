package huce.Controller;

import huce.Model.AppDB;
import huce.View.MainApp;
import huce.View.ViewGraph;

import java.awt.event.ActionEvent;

public class OnViewNodesController extends Controller{
    public OnViewNodesController(AppDB database) {
        super(database);
    }

    @Override
    public void controll(MainApp myapp) {
        myapp.jMenuViewNodes.addActionListener(
                (ActionEvent event) -> {
                    ViewGraph viewNodes = new ViewGraph(this.database.getNodes());
                    viewNodes.drawGraph();
                    viewNodes.setVisible(true);
                }
        );
    }
}
