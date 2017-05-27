/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package radararduino;

import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import java.io.BufferedReader;
import java.io.OutputStream;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import gnu.io.UnsupportedCommOperationException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Scanner;
import java.util.TooManyListenersException;

/**
 *
 * @author ivanm
 */
public class SerialReceiver implements SerialPortEventListener {

    SerialPort serialPort;

    RadarDibujo rd;

    private BufferedReader input;
    private OutputStream output;
    private static final int TIME_OUT = 2000;
    private static final int DATA_RATE = 9600;
    private static final int MAX_Y = 80;
    private static final int MAX_X = 180;

    public SerialReceiver(RadarDibujo rd) {
        this.rd = rd;
        try {
            choosePort();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public void choosePort() throws PortInUseException, UnsupportedCommOperationException, IOException, TooManyListenersException {
        CommPortIdentifier serialPortId;
        Enumeration enumComm;
        enumComm = CommPortIdentifier.getPortIdentifiers();
        ArrayList<CommPortIdentifier> serialPorts = new ArrayList();
        //Se obtiene la lista de puertos serial 
        while (enumComm.hasMoreElements()) {
            serialPortId = (CommPortIdentifier) enumComm.nextElement();
            if (serialPortId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
                serialPorts.add(serialPortId);
                System.out.println(serialPorts.size() - 1 + ") " + serialPorts.get(serialPorts.size() - 1).getName());
            }
        }
        //Lee por consola el puerto elegido
        Scanner s = new Scanner(System.in);
        boolean existePuerto = false;
        //se asegura de que el puerto existe
        while (!existePuerto) {
            System.out.println("Elige el puerto");
            int n = s.nextInt();
            if ((n >= 0) && (n < serialPorts.size())) {
                serialPort = (SerialPort) serialPorts.get(n).open(this.getClass().getName(), TIME_OUT);
                serialPort.setSerialPortParams(DATA_RATE, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);

                input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
                output = serialPort.getOutputStream();

                serialPort.addEventListener(this);
                serialPort.notifyOnDataAvailable(true);
                int i = 0;
                while (i != 1) {
                    if (input.ready()) {
                        i = input.read();
                        if (i == 1) {
                            output.write(1);
                        } else {
                            output.write(0);
                        }
                    }
                }
                existePuerto = true;
            }
        }

    }

    @Override
    public void serialEvent(SerialPortEvent oEvent) {
        if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
            try {
                if (input.ready()) {

                    int x = Integer.parseInt(input.readLine());
                    int dist = Integer.parseInt(input.readLine());
                    rd.getDrawing().addPoint(distanciaX(x), distanciaY(dist), dist); //añade un punto modificado para cuadrar con el tamaño de la ventana
                    rd.getRadarEditor().repaint();

                }
            } catch (IOException | NumberFormatException e) {
                System.err.println(e.toString());
            }
        }
    }

    public synchronized void close() { // cierra el puerto
        if (serialPort != null) {
            serialPort.removeEventListener();
        }
        serialPort.close();
        System.out.println("Closed");
    }

    public int distanciaY(int dist) {
        int y = dist * rd.getRadarEditor().getSize().height / MAX_Y;
        return rd.getRadarEditor().getSize().height - y - 120;
    }

    public int distanciaX(int x) {
        return x * rd.getRadarEditor().getSize().width / MAX_X;
    }

}
