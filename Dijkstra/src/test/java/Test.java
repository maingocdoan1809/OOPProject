import huce.Algorithm.Dijkstra;
import huce.Algorithm.Node.Node;
import huce.Exception.GraphvizFileFormatException;
import huce.Exception.PathNotFoundException;
import huce.Graphviz.Parser;

import javax.swing.*;
import java.io.FileInputStream;
import java.nio.file.Path;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test{

    public static void main(String[] args) {
        var p = Path.of("D:\\ObjectOrientedThesis" +
                "\\OOPProject\\Documents\\Testcase\\graph1.dot");
        try (FileInputStream fs = new FileInputStream(p.toFile())) {
            String graph = new String(fs.readAllBytes());
            Parser.toNodes(graph);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}