package huce.Controller;

import com.sun.tools.javac.Main;
import huce.Graphviz.Parser;
import huce.Model.AppDB;
import huce.View.MainApp;

import javax.swing.*;

public abstract class Controller implements AutoCloseable {
    protected AppDB database;
    public Controller(AppDB database) {
        this.database = database;
    }
    abstract public void controll(MainApp myapp);
    @Override
    public void close() throws Exception {
        database.close();
    }
}
