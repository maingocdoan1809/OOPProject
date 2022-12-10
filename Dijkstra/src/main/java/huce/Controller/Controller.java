package huce.Controller;

import huce.Model.AppDB;
import huce.View.MainApp;

public abstract class Controller implements AutoCloseable {
    protected AppDB database;
    public Controller() {
        this.database = AppDB.getModel(AppDB.getConnectionString("maingocdoan", "1234",
                "OOPPROJECT"));
    }
    abstract public void controll(MainApp myapp);
    @Override
    public void close() throws Exception {
        database.close();
    }
}
