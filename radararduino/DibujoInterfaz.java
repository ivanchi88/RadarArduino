package radararduino;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author ivanm
 */
public class DibujoInterfaz { //Esta clase se encarga del dibujo del fondo
    
    RadarEditor re;

    public void setRadarEditor (RadarEditor r)
    {
        re = r;
    }

    public void draw(Graphics g, int h, int w) {
        g.setColor(Color.green);
        g.drawLine(5, (int) (h - h*0.165), w-25, (int)(h - h*0.165)); // linea de abajo
        //lineas transversales en orden de izq a dch
        //izq
        g.drawLine(0, h/2, w/2, (int)(h - h*0.165));
        g.drawLine(w/12, 0, w/2, (int)(h - h*0.165));
        //medio
        g.drawLine(w/2, 0, w/2, (int)(h - h*0.165));
        //dch
        g.drawLine(w - w/12, 0, w/2, (int)(h - h*0.165));
        g.drawLine(w, h/2, w/2, (int)(h - h*0.165));     
        //arcos     
        g.drawArc(w/3, 2*h/3 ,  w/3, h - 2 *h/3, 0, 180); // primerArco
        g.drawArc(w/2 - w/3, h/2 ,  2*w/3,2*( h - 2 *h/3), 0, 180); // segundoArco
        g.drawArc(5, h - 2*h/3 ,  w - 30, h , 0, 180); // tercerArco
        g.drawArc(-w/3, h/7  ,  5*w/3 , 2*h + h/5, 0, 180); // tercerArco
        
    }

}
