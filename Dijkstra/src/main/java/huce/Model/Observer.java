package huce.Model;

public interface Observer {
    void observe(Subject subject);
    void notifySubject();
}
