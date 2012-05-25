/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package damaschinas;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JOptionPane;

/**
 *
 * @author Jose
 */
public class HiloServer extends Thread{
        
    ServerSocket socketServidor;
    Socket so;
    DataOutputStream salida;
    String mensajeRecibido;
    boolean Encendido;
    Interfaz Padre;
    
    public HiloServer(boolean encendido, Interfaz p, int puerto){
        Padre=p;
        if(encendido)
            EncenderServer(puerto);
        
    }
    
    public void EncenderServer(int puerto){        
        try {          
            socketServidor = new ServerSocket(puerto);
            Encendido=true;
            System.out.println("Servidor Encendido");            
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog( null,
                "Ha ocurrido un error, y el servido no ha podido encenderse.",
                "Iniciar Servidor",
                JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
    }  
    
    @Override
    public void run(){
        while(true && Encendido){
            try{            
                Socket cliente = socketServidor.accept();
                System.out.println("Conexión entrante");
                cliente.setSoLinger(true, 10);            
                ObjectInputStream ois = new ObjectInputStream(cliente
                        .getInputStream());

                Object mensaje = ois.readObject();                
                if (mensaje instanceof String[])
                {
                    String[] m=(String[])mensaje;
                    if(m.length==2){
                        if(m[0].compareTo("0")==0){//Se recibe la IP del cliente
                            Padre.IpCompañero=m[1];
                            System.out.println("Conexión con cliente IP = "+m[1]);
                            Padre.setTitle("");  
                            //Se envian datos de la partida
                            Padre.MandarObjetoSocket(Padre.IpCompañero, new Object[]{0,Padre.max_piezas,Padre.TipoPartida},Interfaz.PUERTO_CLIENTE);                            
                            Padre.crearTablero();
                            Padre.calcularContiguos();
                            Padre.jTextField1.setEnabled(true);
                            Padre.NumeroJugador=1;
                            Padre.jButton1.setEnabled(true);
                            
                            
                            switch(Padre.TipoPartida){
                                case 1:{
                                    Padre.P1_Enabled=true;
                                    Padre.P2_Enabled=false;
                                    break;
                                }
                                case 2:{
                                    Padre.P1_Enabled=true;
                                    Padre.P2_Enabled=false;
                                    break;
                                }
                                case 3:{
                                    Padre.P1_Enabled=false;
                                    Padre.P2_Enabled=false;
                                    break;
                                }
                            }                                                   
                        }                                                
                    }                    
                }
                if(mensaje instanceof Object[]){
                    Object[] m=(Object[])mensaje;                    
                    if(m.length==3){
                        switch((Integer)m[0]){
                            case 0:{//Se obtiene respuesta del servidor
                                System.out.println("Se obtuvo respuesta de servidor = "+Padre.IpCompañero);                        
                                Padre.setTitle("");  
                                Padre.max_piezas=(Integer)m[1];
                                Padre.TipoPartida=(Integer)m[2];
                                Padre.crearTablero();
                                Padre.calcularContiguos();
                                Padre.NumeroJugador=2;
                                Padre.jButton1.setEnabled(false);
                                Padre.jButton2.setEnabled(false);
                                Padre.jButton9.setEnabled(false);
                                Padre.jTextField1.setEnabled(true);

                                switch(Padre.TipoPartida){
                                    case 1:{
                                        Padre.P1_Enabled=false;
                                        Padre.P2_Enabled=true;
                                        break;
                                    }
                                    case 2:{
                                        Padre.P1_Enabled=false;
                                        Padre.P2_Enabled=false;
                                        break;
                                    }
                                    case 3:{
                                        Padre.P1_Enabled=false;
                                        Padre.P2_Enabled=false;
                                        break;
                                    }
                                }
                                break;
                            }
                        }
                    }
                    if(m.length==5){
                        switch((Integer)m[0]){                            
                            case 0:{//Se obtiene movimiento de oponente                                                                
                                System.out.println("Se obtiene movimiento oponente.");
                                Padre.Mover(Padre.encontrarCasilla((Integer)m[1], (Integer)m[2]), 
                                        Padre.encontrarCasilla((Integer)m[3], (Integer)m[4]),false);
                                
                            }
                        }
                    }
                }
                if(mensaje instanceof String){//Se obtiene un mensaje por chat
                    Padre.list1.add("Oponente: "+mensaje.toString());
                }
                if(mensaje instanceof Integer){//Se obtiene un mensaje por chat
                    int m=(Integer)mensaje;
                    if(m==0){
                        System.out.println("El oponente ha finalizado su turno.");
                        Padre.TerminarTurno(false);
                    }
                }

                // Cierre de sockets
                cliente.close();
            } catch (Exception e){
                System.err.println(e.getMessage());
            }
        }   
    }
}
