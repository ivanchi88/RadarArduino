
package radararduino;

import java.awt.Color;
import java.awt.Graphics;

public class PuntoDeDistancia {
    
    int x, dist, distR;
       
    public PuntoDeDistancia(int x, int d, int r)
    {
        this.x = x;
        this.dist = d;
        this.distR = r; // distancia real en cm
    }
    
    public void draw (Graphics g)
    {
        g.setColor(Color.green);
        g.fillOval(x, dist, 10 ,10); // dibuja el punto donde esta el objeto
        
    }
    
        
    
    
}
