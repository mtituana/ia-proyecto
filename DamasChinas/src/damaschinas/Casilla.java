/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package damaschinas;

import java.util.ArrayList;



/**
 *
 * @author Victor
 */
public class Casilla {
    
    ArrayList <Casilla>contiguas=new ArrayList();
    int idx,idy;
    int valor; //1 es vacía 2 es el jugador 1 y 3 es el jugador 2.
    Interfaz padre;
    public Casilla(int x,int y,int v,Interfaz p){        
        idx=x;
        idy=y;
        valor=v;
        padre=p;
    }
    
    public void agregarContigua(Casilla c){
        if(c!=null)
        contiguas.add(c);
    }
    
    
    
}
