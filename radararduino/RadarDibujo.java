/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package radararduino;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author ivanm
 */
public class RadarDibujo extends JPanel {

    RadarEditor re;
    Drawing drawing;
    SerialReceiver receiver;
    DibujoInterfaz interfaz;

    public RadarDibujo() {
        receiver = new SerialReceiver(this); // SerialReceiver se encarga de traer los datos de arduino
    }

    public RadarEditor getRadarEditor() {
        return re;
    }

    public Drawing getDrawing() {
        return drawing;
    }

    public void setRadarEditor(RadarEditor r) {
        re = r;
    }

    public void setDrawing(Drawing d) {
        drawing = d;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.black);
        g.fillRect(0, 0, re.getSize().height * 10, re.getSize().width * 10); //pinta el fondo de negro
        interfaz.draw(g, re.getSize().height, re.getSize().width);  //pinta la interfaz
        drawing.draw(g, re.getSize().height, re.getSize().width);   //encima de la interfaz dibuja los puntos
    }

    void setDibujoInerfaz(DibujoInterfaz interfaz) {
        this.interfaz = interfaz;
    }

}
