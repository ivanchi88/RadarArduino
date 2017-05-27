
package radararduino;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Drawing { // Esta clase guarda todos los puntos que se muestran por pantalla
    
    ArrayList <PuntoDeDistancia> puntos;
    int position;
    
    public Drawing ()
    {
        puntos = new ArrayList();
    }
    
    public void addPoint(int x, int dist, int distR) // añade los puntos nuevos al dibujo y elimina los antiguos
    {
        PuntoDeDistancia p = new PuntoDeDistancia(x + 5, dist - 50, distR);
        puntos.add(p);
        if (puntos.size() > 55)
            puntos.remove(0);
    }
    
    public void draw (Graphics g, int h, int w) // dibuja los puntos
    {
        for (PuntoDeDistancia p : puntos)
        {
            if (p.dist < 825)
                p.draw(g);
                drawDist(g, p, h , w);
        }
        
    }
    
    public void drawDist(Graphics g, PuntoDeDistancia p, int h, int w){ //Muestra la distancia real del punto
       g.setColor(Color.black);
       g.fillRect(w/2 - 31, h - 70, 100, 100);
       g.setColor(Color.green);
       g.drawString("Distance: " + p.distR + " cm", w/2 - 50, h - 50);
    }
    
    
    
}
