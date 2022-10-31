import huce.Graphviz.Parser;

import java.io.FileInputStream;
import java.nio.file.Path;

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