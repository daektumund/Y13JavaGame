import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
public class Canvas extends JFrame {
    public Canvas(){
        this.setTitle("Poot Poot");
        this.getContentPane().setPreferredSize(new Dimension(1000,980));
        this.getContentPane().setLayout(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        //this.setContentPane(new panel());
        this.pack();
    }
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawRect(50,50,50,50);
    }
}