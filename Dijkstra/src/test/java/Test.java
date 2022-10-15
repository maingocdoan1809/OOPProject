import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import com.mindfusion.diagramming.*;


public class Test
        extends JFrame
{
    public Test()
    {
        diagram = new Diagram();
        diagramView = new DiagramView();
        diagramView.setDiagram(diagram);

        JScrollPane scrollPane = new JScrollPane(diagramView);
        getContentPane().add(scrollPane);

        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(size);
    }

    public static void main(String[] args)
    {
        Test window = new Test();
        window.setVisible(true);
    }


    private DiagramView diagramView;
    private Diagram diagram;
}
