
package radararduino;

import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 * @author ivanm
 */
public class RadarEditor extends JFrame { // Tan solo es el contenedor
    
    RadarDibujo rd;
    
    public RadarEditor()
    {
        this.setTitle("Radar");
        this.setPreferredSize(new Dimension(1000, 1000));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        rd = new RadarDibujo();        
        rd.setRadarEditor(this);
        add(rd);
        
        this.setVisible(true);
        this.pack();
    }    
    
    public RadarDibujo getRadarDibujo()
    {
        return rd;
    }
    
    public void setRadarDibujo( RadarDibujo m)
    {
       rd = m;
       this.setContentPane(rd);
    }
    
}
