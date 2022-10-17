import com.mindfusion.diagramming.Diagram;
import com.mindfusion.diagramming.DiagramView;
import com.mindfusion.diagramming.LayeredLayout;
import com.mindfusion.diagramming.ShapeNode;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;


public class Test
        extends JFrame
{
    // example for Jchart
    public Test()
    {
        diagram = new Diagram();
        diagramView = new DiagramView();
        diagramView.setDiagram(diagram);
        char star = 'A';
            Rectangle2D.Float bounds = new Rectangle2D.Float(0, 0, 15, 8);
        for (int i = 0; i < 10; i++) {

            ShapeNode shapeNode = diagram.getFactory().createShapeNode(bounds);
            shapeNode.setText(star + "");
            star++;
        }

        // layout
        LayeredLayout layout = new LayeredLayout();
        layout.setLayerDistance(50);
        layout.arrange(diagram);
        JScrollPane scrollPane = new JScrollPane(diagramView);
        getContentPane().add(scrollPane);
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(size);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args)
    {
        Test window = new Test();
        window.setVisible(true);
    }


    private DiagramView diagramView;
    private Diagram diagram;
}
