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
                System.out.println("Aceptado cliente");
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
                            Padre.MandarObjetoSocket(Padre.IpCompañero, new Object[]{1,Padre.max_piezas},Interfaz.PUERTO_CLIENTE);                            
                            Padre.crearTablero();
                            Padre.calcularContiguos();
                            Padre.jTextField1.setEnabled(true);
                            Padre.NumeroJugador=1;
                        }                                                
                    }                    
                }
                if(mensaje instanceof Object[]){
                    Object[] m=(Object[])mensaje;
                    if(m.length==2){
                        if((Integer)m[0]==1){//Se obtiene la respeusta del servidor      
                            System.out.println("Se obtuvo respuesta de servidor = "+Padre.IpCompañero);                        
                            Padre.setTitle("");  
                            Padre.max_piezas=(Integer)m[1];
                            Padre.TipoPartida=1;
                            Padre.crearTablero();
                            Padre.calcularContiguos();
                            Padre.NumeroJugador=2;
                            Padre.jButton2.setEnabled(false);
                            Padre.jButton9.setEnabled(false);
                            Padre.jTextField1.setEnabled(true);
                        }
                    }
                }
                if(mensaje instanceof String){//Se obtiene un mensaje por chat
                    Padre.list1.add("Oponente: "+mensaje.toString());
                }

                // Cierre de sockets
                cliente.close();
            } catch (Exception e){
                System.err.println(e.getMessage());
            }
        }   
    }
}
