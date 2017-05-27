/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package radararduino;
/**
 *
 * @author ivanm
 */
public class RadarArduino {
    public static void main(String[] args) {
        RadarDibujo vista; 
        Drawing d = new Drawing(); 
        DibujoInterfaz interfaz = new DibujoInterfaz();
        RadarEditor re = new RadarEditor();
        vista = re.getRadarDibujo();
        vista.setDibujoInerfaz(interfaz);
        vista.setDrawing(d);
    }
    
}
