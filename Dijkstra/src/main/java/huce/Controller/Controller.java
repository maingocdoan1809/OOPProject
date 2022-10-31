package huce.Controller;

import huce.Model.AppDB;
import huce.View.MainApp;

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
