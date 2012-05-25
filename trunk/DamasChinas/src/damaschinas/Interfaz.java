/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package damaschinas;

import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.*;

/**
 *
 * @author Victor
 */
public class Interfaz extends javax.swing.JFrame {
    public static final int PUERTO_SERVER=5000;
    public static final int PUERTO_CLIENTE=6000;
    ArrayList <Casilla>mapa=new ArrayList();
    ArrayList <Casilla>yasaltados=new ArrayList();
    JLabel labels[][]=  new JLabel[18][14];
    Casilla de=null;
    int turn=1;
    int max_piezas=10;
    boolean AlgoritmoMax=true;//Si se usa algoritmo, true=max; false=min;
    int TipoPartida=1;//1=human vs human; 2=human vs pc; 3=pc vs pc;    
    int NumeroJugador=1;//En partidas human vs human indica el número.
    boolean P1_Enabled=true,P2_Enabled=true;//Indica si se puede o no hacer clic sobre las fichas
    String IpCompañero="";
    Icon icon = new ImageIcon("Red-circle.png");
    Icon icon2 = new ImageIcon("Blue-circle.png");
    Icon icon3 = new ImageIcon("Gray-circle.png");
    Icon icon_l = new ImageIcon("Light-Red-circle.png");
    Icon icon2_l = new ImageIcon("Light-Blue-circle.png");    
    
    HiloServer Servidor;
    static public byte[][] board_shape2 ={{0,2},{0,2}};
    static public byte[][] board_shape =
	{
            { 0,  0,  0,  0,  0,  0,  2,  0,  0,  0,  0,  0,  0},
               {0,  0,  0,  0,  0,  2,  2,  0,  0,  0,  0,  0,  0},
             {0,  0,  0,  0,  0,  2,  2,  2,  0,  0,  0,  0,  0},
               {0,  0,  0,  0,  2,  2,  2,  2,  0,  0,  0,  0,  0},
             {1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1},
               {1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  0},
             {0,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  0},
               {0,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  0,  0},
             {0,  0,  1,  1,  1,  1,  1,  1,  1,  1,  1,  0,  0},
               {0,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  0,  0},
             {0,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  0},
               {1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  0},
             {1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1},
               {0,  0,  0,  0,  3,  3,  3,  3,  0,  0,  0,  0,  0},
             {0,  0,  0,  0,  0,  3,  3,  3,  0,  0,  0,  0,  0},
               {0,  0,  0,  0,  0,  3,  3,  0,  0,  0,  0,  0,  0},
             {0,  0,  0,  0,  0,  0,  3,  0,  0,  0,  0,  0,  0},
	};
    
    static public byte[][] board_shape20 =
	{
            { 0,  0,  0,  0,  0,  0,  2,  0,  0,  0,  0,  0,  0},
               {0,  0,  0,  0,  0,  2,  2,  0,  0,  0,  0,  0,  0},
             {0,  0,  0,  0,  0,  2,  2,  2,  0,  0,  0,  0,  0},
               {0,  0,  0,  0,  2,  2,  2,  2,  0,  0,  0,  0,  0},
             {1,  1,  1,  1,  2,  2,  2,  2,  2,  1,  1,  1,  1},
               {1,  1,  1,  1,  2,  2,  2,  2,  2,  1,  1,  1,  0},
             {0,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  0},
               {0,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  0,  0},
             {0,  0,  1,  1,  1,  1,  1,  1,  1,  1,  1,  0,  0},
               {0,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  0,  0},
             {0,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  0},
               {1,  1,  1,  1,  3,  3,  3,  3,  3,  1,  1,  1,  0},
             {1,  1,  1,  1,  3,  3,  3,  3,  3,  1,  1,  1,  1},
               {0,  0,  0,  0,  3,  3,  3,  3,  0,  0,  0,  0,  0},
             {0,  0,  0,  0,  0,  3,  3,  3,  0,  0,  0,  0,  0},
               {0,  0,  0,  0,  0,  3,  3,  0,  0,  0,  0,  0,  0},
             {0,  0,  0,  0,  0,  0,  3,  0,  0,  0,  0,  0,  0},
	};
    
    static public byte[][] board_shape30 =
	{
            { 0,  0,  0,  0,  0,  0,  2,  0,  0,  0,  0,  0,  0},
               {0,  0,  0,  0,  0,  2,  2,  0,  0,  0,  0,  0,  0},
             {0,  0,  0,  0,  0,  2,  2,  2,  0,  0,  0,  0,  0},
               {0,  0,  0,  0,  2,  2,  2,  2,  0,  0,  0,  0,  0},
             {1,  1,  1,  1,  2,  2,  2,  2,  2,  1,  1,  1,  1},
               {1,  1,  1,  2,  2,  2,  2,  2,  2,  1,  1,  1,  0},
             {0,  1,  1,  2,  2,  2,  2,  2,  2,  2,  1,  1,  0},
               {0,  1,  1,  1,  1,  1,  1,  1,  2,  2,  1,  0,  0},
             {0,  0,  1,  1,  1,  1,  1,  1,  1,  1,  1,  0,  0},
               {0,  1,  1,  1,  1,  1,  1,  1,  3,  3,  1,  0,  0},
             {0,  1,  1,  3,  3,  3,  3,  3,  3,  3,  1,  1,  0},
               {1,  1,  1,  3,  3,  3,  3,  3,  3,  1,  1,  1,  0},
             {1,  1,  1,  1,  3,  3,  3,  3,  3,  1,  1,  1,  1},
               {0,  0,  0,  0,  3,  3,  3,  3,  0,  0,  0,  0,  0},
             {0,  0,  0,  0,  0,  3,  3,  3,  0,  0,  0,  0,  0},
               {0,  0,  0,  0,  0,  3,  3,  0,  0,  0,  0,  0,  0},
             {0,  0,  0,  0,  0,  0,  3,  0,  0,  0,  0,  0,  0},
	};
    
    static public byte[][] winstate_p2 =
	{
            { 0,  0,  0,  0,  0,  0,  3,  0,  0,  0,  0,  0,  0},
               {0,  0,  0,  0,  0,  3,  3,  0,  0,  0,  0,  0,  0},
             {0,  0,  0,  0,  0,  3,  3,  3,  0,  0,  0,  0,  0},
               {0,  0,  0,  0,  3,  3,  3,  3,  0,  0,  0,  0,  0},
	};

        static public byte[][] winstate_p1 =
	{
           {0,  0,  0,  0,  2,  2,  2,  2,  0,  0,  0,  0,  0},
             {0,  0,  0,  0,  0,  2,  2,  2,  0,  0,  0,  0,  0},
               {0,  0,  0,  0,  0,  2,  2,  0,  0,  0,  0,  0,  0},
             {0,  0,  0,  0,  0,  0,  2,  0,  0,  0,  0,  0,  0},
	};
    
    
    public void crearTablero(){        
        byte board_shape_use[][]=board_shape;
        switch(max_piezas){
            case 20:
                    board_shape_use=board_shape20;
                break;
            case 30:
                    board_shape_use=board_shape30;
                break;
        }
        for(int i=0;i<17;i++){
            for(int j=0;j<13;j++){
                if(board_shape_use[i][j]!=0){                    
                    Icon icontemp = loadIcon("Gray-circle.png");
                    Casilla temp=new Casilla(i,j,board_shape_use[i][j],this);     
                    switch(board_shape_use[i][j]){
                        case 1:
                            icontemp=icon3;
                            break;
                        case 2:
                            icontemp=icon;
                            break;
                        case 3:
                            icontemp=icon2;
                            break;
                    }
                    JLabel label = new JLabel("", icontemp, JLabel.CENTER);                    
                    labels[i][j]=label;
                    jPanel2.add(label);
                    label.setVisible(true);
                    
                    
                    if(i%2==0){
                        label.setBounds(30+50*j, 15+(40*i), 40, 40);                    
                    }
                    else{
                        label.setBounds(50+50*j, 15+(40*i), 40, 40);
                    }                    
                    final int cosox=i;
                    final int cosoy=j;
                    
                    label.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                            Mover(encontrarCasilla(cosox,cosoy));
                        }
                    });
                    mapa.add(temp);
                    
                }            
            }
        }
                
        

    }
    
    /**
     * Creates new form Interfaz
     */
    public Interfaz() {
        initComponents();
        icon = loadIcon("Red-circle.png");
        icon2 = loadIcon("Blue-circle.png");
        icon3 = loadIcon("Gray-circle.png");
        icon_l = loadIcon("Light-Red-circle.png");
        icon2_l = loadIcon("Light-Blue-circle.png");   
        
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }       

    public Casilla encontrarCasilla(int x,int y){
        Casilla cas=null;
        for(int i=0;i<mapa.size();i++){
            if(mapa.get(i).idx==x && mapa.get(i).idy==y)
                return mapa.get(i);
        }
        return cas;
    }
    
    /**
     * 
     * @param origen Casilla de origen
     * @param destino Casilla de destino
     * @param enviar True si se debe enviar por socket, False si no
     */
    public void Mover(Casilla origen, Casilla destino,boolean enviar){
        if(origen!=null && destino !=null){
            if(origen.valor==(turn+1) || !enviar){
                //Verifica si la casilla puede ser seleccionada por un humano.
                if((origen.valor==2 && P1_Enabled)||(origen.valor==3 && P2_Enabled)|| !enviar){
                    if(turn==1){
                        labels[origen.idx][origen.idy].setIcon(icon_l);                      
                    }
                    else{
                        labels[origen.idx][origen.idy].setIcon(icon2_l);                    
                    }
                    if(movimientoPosible(origen,destino)){
                        yasaltados.add(origen);                
                        labels[origen.idx][origen.idy].setIcon(icon3);
                        encontrarCasilla(origen.idx,origen.idy).valor=1;
                        if(turn==1){
                        labels[destino.idx][destino.idy].setIcon(icon);
                        encontrarCasilla(destino.idx,destino.idy).valor=2;
                        }
                        else{
                            labels[destino.idx][destino.idy].setIcon(icon2);
                            encontrarCasilla(destino.idx,destino.idy).valor=3;
                        }                    
                        if(!existenSaltos(destino)){
                            de=null;                 
                            yasaltados.clear();
                            TerminarTurno(false);

                        }
                        else{
                            if(turn==1){
                                labels[destino.idx][destino.idy].setIcon(icon_l);
                            }
                            else{
                                labels[destino.idx][destino.idy].setIcon(icon2_l);
                            }

                        }
                        
                        int puerto=PUERTO_SERVER;
                        if(NumeroJugador==1){
                            puerto=PUERTO_CLIENTE;
                        }
                        
                        if(enviar){
                            MandarObjetoSocket(IpCompañero,new Object[]{0,origen.idx,origen.idy,
                                destino.idx,destino.idy},puerto);
                        }                        
                    }
                    else{
                        System.err.println("Movimiento no permitido.");                
                    } 
                }                
            }
        }
        
        int aa=hasWon();
        if(aa!=0){
            JOptionPane.showMessageDialog(this, "Gano el jugador "+aa, "Fin del Juego", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
    }
    
    public void Mover(Casilla obj){
        if(de==null){           
            if(obj.valor==(turn+1)){
                //Verifica si la casilla puede ser seleccionada por un humano.
                if((obj.valor==2 && P1_Enabled)||(obj.valor==3 && P2_Enabled)){
                    de=obj;
                    if(turn==1){
                        labels[de.idx][de.idy].setIcon(icon_l);                      
                    }
                    else{
                        labels[de.idx][de.idy].setIcon(icon2_l);                    
                    }
                }                                
            } 
        }
        else{
            Mover(de,obj,true);            
        }        
    }
    
    public boolean existenSaltos(Casilla d){
        boolean posible=false;
            
            if(saltoPosible(d,encontrarCasilla(d.idx+2,d.idy-1))&&!yasaltados.contains(encontrarCasilla(d.idx+2,d.idy-1))){
                System.out.println("}:");
                return true;
            }
            if(saltoPosible(d,encontrarCasilla(d.idx+2,d.idy+1))&&!yasaltados.contains(encontrarCasilla(d.idx+2,d.idy+1)))
                {
                System.out.println("dafuq");
                return true;
            }
            if(saltoPosible(d,encontrarCasilla(d.idx-2,d.idy-1))&&!yasaltados.contains(encontrarCasilla(d.idx-2,d.idy-1)))
                {
                System.out.println("omg");
                return true;
            }
            if(saltoPosible(d,encontrarCasilla(d.idx-2,d.idy+1))&&!yasaltados.contains(encontrarCasilla(d.idx-2,d.idy-1)))
                {
                System.out.println("so op");
                return true;
            }
            if(saltoPosible(d,encontrarCasilla(d.idx,d.idy-1))&&!yasaltados.contains(encontrarCasilla(d.idx,d.idy-1)))
                {
                System.out.println("im literally crying");
                return true;
            }
            if(saltoPosible(d,encontrarCasilla(d.idx,d.idx+1))&&!yasaltados.contains(encontrarCasilla(d.idx,d.idx-1)))
                return true;
        
        return posible;
        
    }
    
    public boolean saltoPosible(Casilla from, Casilla to){        
        boolean posible=false;
        if(to==null){
            return false;
        }
        if(to.valor!=1)
            return false;
        if(yasaltados.contains(to))
            return false;
        
        if(from.idx%2==0){                                    
            if((to.idx==from.idx+2)&&(to.idy==from.idy+1)&&(encontrarCasilla(from.idx+1,from.idy).valor!=1)){                
              return true;   
            }            
            if((to.idx==from.idx+2)&&(to.idy==from.idy-1)&&(encontrarCasilla(from.idx+1,from.idy-1).valor!=1)){                
              return true;   
            }            
            
            if((to.idx==from.idx)&&(to.idy==from.idy+2)&&(encontrarCasilla(from.idx,from.idy+1).valor!=1)){                
              return true;   
            }            
            if((to.idx==from.idx)&&(to.idy==from.idy-2)&&(encontrarCasilla(from.idx,from.idy-1).valor!=1)){                
              return true;   
            }            
            if((to.idx==from.idx-2)&&(to.idy==from.idy+1)&&(encontrarCasilla(from.idx-1,from.idy).valor!=1)){                
              return true;   
            }            
            if((to.idx==from.idx-2)&&(to.idy==from.idy-1)&&(encontrarCasilla(from.idx-1,from.idy-1).valor!=1)){                
              return true;   
            }            
            
        }
        else{
            if((to.idx==from.idx+2)&&(to.idy==from.idy+1)&&(encontrarCasilla(from.idx+1,from.idy+1).valor!=1)){                
              return true;   
            }            
            if((to.idx==from.idx+2)&&(to.idy==from.idy-1)&&(encontrarCasilla(from.idx+1,from.idy).valor!=1)){                
              return true;   
            }            
            
            if((to.idx==from.idx)&&(to.idy==from.idy+2)&&(encontrarCasilla(from.idx,from.idy+1).valor!=1)){                
              return true;   
            }            
            if((to.idx==from.idx)&&(to.idy==from.idy-2)&&(encontrarCasilla(from.idx,from.idy-1).valor!=1)){                
              return true;   
            }            
            if((to.idx==from.idx-2)&&(to.idy==from.idy+1)&&(encontrarCasilla(from.idx-1,from.idy+1).valor!=1)){                
              return true;   
            }            
            if((to.idx==from.idx-2)&&(to.idy==from.idy-1)&&(encontrarCasilla(from.idx-1,from.idy).valor!=1)){                
              return true;   
            }            
        }
        return posible;
    }
    
    public boolean movimientoPosible(Casilla from, Casilla to){        
        boolean posible=false;
        if(from.contiguas.contains(to)&&(to.valor==1)){         //si esta a la par, y no esta ocupada siempre se puede saltar
            return true;
        }
        if(saltoPosible(from,to)){
            return true;
        }
        return posible;
    }
    
    
    public void calcularContiguos(){
        for(int i=0;i<mapa.size();i++){
            int tempx=mapa.get(i).idx;
            int tempy=mapa.get(i).idy;                        

            if(tempx%2==0){
                mapa.get(i).agregarContigua(encontrarCasilla(tempx,tempy-1));
                mapa.get(i).agregarContigua(encontrarCasilla(tempx,tempy+1));
                mapa.get(i).agregarContigua(encontrarCasilla(tempx-1,tempy-1));
                mapa.get(i).agregarContigua(encontrarCasilla(tempx-1,tempy));
                mapa.get(i).agregarContigua(encontrarCasilla(tempx+1,tempy-1));
                mapa.get(i).agregarContigua(encontrarCasilla(tempx+1,tempy));
            }
            else{
                mapa.get(i).agregarContigua(encontrarCasilla(tempx,tempy-1));
                mapa.get(i).agregarContigua(encontrarCasilla(tempx,tempy+1));
                mapa.get(i).agregarContigua(encontrarCasilla(tempx-1,tempy+1));                
                mapa.get(i).agregarContigua(encontrarCasilla(tempx-1,tempy));
                mapa.get(i).agregarContigua(encontrarCasilla(tempx+1,tempy+1));
                mapa.get(i).agregarContigua(encontrarCasilla(tempx+1,tempy));
            }
        }
    }
    
    private ImageIcon loadIcon(String nombre) {
        nombre="damaschinas/"+nombre;
        ImageIcon icono;
        URL url = null;
        try {
            url = getClass().getClassLoader().getResource(nombre);
            icono = new ImageIcon(url);
            if (icono == null) {
                System.err.println("ITEM NO ENCONTRADO");
            }
            return icono;
        } catch (Exception e) {            
            return null;
        }
    }
    
    public void IniciarPartida(){
        if(jRadioButton4.isSelected()){            
            TipoPartida=1;                                    
        }
        if(jRadioButton5.isSelected()){                
            TipoPartida=2;            
        }
        if(jRadioButton6.isSelected()){            
            TipoPartida=3;            
        }        
        
        Servidor=new HiloServer(true,this,PUERTO_SERVER);
        Servidor.start();
        setTitle("En espera de conexión...");
                
        jButton2.setEnabled(false);
        jButton9.setEnabled(false);                                
    }
    
    public void UnirsePartida(){        
        
        IpCompañero="";
        IpCompañero=JOptionPane.showInputDialog(this, "Ingrese la IP del servidor:", "Server IP", JOptionPane.INFORMATION_MESSAGE);
        if(IpCompañero!=null && !IpCompañero.isEmpty()){
            String MiIp=JOptionPane.showInputDialog(this, "Ingrese su ip:", "Client IP", JOptionPane.INFORMATION_MESSAGE);
            if(MiIp!=null && !MiIp.isEmpty()){
                Servidor=new HiloServer(true,this,PUERTO_CLIENTE); 
                Servidor.start();
                setTitle("Esperando respuesta del servidor...");
                MandarObjetoSocket(IpCompañero,new String[]{"0",MiIp},PUERTO_SERVER);
                
            }
        }
    }
    
    /**
     * Manda un objeto a través de socket.
     * @param informacion Estructura de datos con la informacion de las carpetas compartidas.
     */
     public  void MandarObjetoSocket(String ip,Object informacion, int puerto) {
        try{
            Socket skCliente = new Socket(ip, puerto);

            // Se env�a un mensaje de petici�n de fichero.
            ObjectOutputStream oos = new ObjectOutputStream(skCliente
                    .getOutputStream());            
            oos.writeObject(informacion);            
            System.out.println("SE MANDO LA INFORMACION");
            skCliente.close();

        } catch( Exception e ) {            
            JOptionPane.showMessageDialog( null,
                "La conexión con el servidor no ha sido posible. Verifique su conectividad a la red \n"+
                "y si las especificaciones de conexión son válidas.",
                "Conexión Incompleta",
                JOptionPane.ERROR_MESSAGE);
        }

    }
     
    public void EnviarChat(){
        if(jTextField1.isEnabled() && !jTextField1.getText().isEmpty()){
            int puerto=PUERTO_SERVER;
            if(NumeroJugador==1)
                puerto=PUERTO_CLIENTE;
            
            MandarObjetoSocket(IpCompañero,jTextField1.getText().trim(),puerto);
            list1.add("Yo: "+jTextField1.getText().trim());
            jTextField1.setText("");            
            
        }
    }
    
    /**
     * 
     * @param enviar True si se desea enviar por socket (En caso de presionar el botón TerminarTurno). False
     * si termina el turno a causa de un movimiento.
     */
    public void TerminarTurno(boolean enviar){
        if(turn==1){
            turn=2;
            jButton1.setEnabled(P2_Enabled);
        }else{
            turn=1;
            jButton1.setEnabled(P1_Enabled);
        }
        if(de!=null){
            if(turn==2){
                labels[de.idx][de.idy].setIcon(icon);
            }
            else{
                labels[de.idx][de.idy].setIcon(icon2);
            }
        }
        de=null;                 
        yasaltados.clear();
        
        if(enviar){
            int puerto=PUERTO_SERVER;
            if(NumeroJugador==1)
                puerto=PUERTO_CLIENTE;
            MandarObjetoSocket(IpCompañero,0,puerto);
        }        
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        list1 = new java.awt.List();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jRadioButton4 = new javax.swing.JRadioButton();
        jRadioButton5 = new javax.swing.JRadioButton();
        jRadioButton6 = new javax.swing.JRadioButton();
        jComboBox4 = new javax.swing.JComboBox();
        jButton9 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Controles"));

        jButton1.setText("Terminar turno");
        jButton1.setEnabled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Tablero"));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 597, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Chat"));

        jTextField1.setText("jTextField1");
        jTextField1.setEnabled(false);
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(list1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField1))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(list1, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Iniciar Partida"));

        jLabel3.setText("Número de Fichas:");

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "10", "20", "30" }));
        jComboBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox3ActionPerformed(evt);
            }
        });

        jLabel4.setText("Tipo de Juego:");

        buttonGroup1.add(jRadioButton4);
        jRadioButton4.setSelected(true);
        jRadioButton4.setText("Humano vs Humano");
        jRadioButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton4ActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButton5);
        jRadioButton5.setText("Humano vs PC");

        buttonGroup1.add(jRadioButton6);
        jRadioButton6.setText("PC vs PC");

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Minimix - Max", "Minimix - Min" }));
        jComboBox4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox4ActionPerformed(evt);
            }
        });

        jButton9.setText("Iniciar Partida");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton2.setText("Conectarse a Partida");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jRadioButton6)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel4)
                            .addComponent(jRadioButton4)
                            .addComponent(jRadioButton5))
                        .addGap(0, 42, Short.MAX_VALUE))
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRadioButton5)
                .addGap(3, 3, 3)
                .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRadioButton6)
                .addGap(18, 18, 18)
                .addComponent(jButton9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public int hasWon(){
        int si=2;
         for(int i=0;i<4;i++){
            for(int j=0;j<13;j++){
                if(encontrarCasilla(i,j)!=null){
                    if(encontrarCasilla(i,j).valor==winstate_p2[i][j]){

                    }
                    else{
                        si=0;                    
                    }
                }
            }
        }
        if(si==0)
            si=1;
        
        for(int i=13;i<17;i++){
            for(int j=0;j<13;j++){
                if(encontrarCasilla(i,j)!=null){
                    if(encontrarCasilla(i,j).valor==winstate_p1[i-13][j]){

                    }
                    else{
                        si=0;                    
                    }
                }
            }
        }
        if(si!=0){
            turn=0;
        }
        return si;
    }
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        TerminarTurno(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ActionPerformed
        // TODO add your handling code here:
        max_piezas=(jComboBox3.getSelectedIndex()+1)*10;
    }//GEN-LAST:event_jComboBox3ActionPerformed

    private void jRadioButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton4ActionPerformed

    private void jComboBox4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox4ActionPerformed
        // TODO add your handling code here:
        AlgoritmoMax=jComboBox4.getSelectedIndex()==0;
    }//GEN-LAST:event_jComboBox4ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        IniciarPartida();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        UnirsePartida();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
        EnviarChat();
    }//GEN-LAST:event_jTextField1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new Interfaz().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    public javax.swing.JButton jButton1;
    public javax.swing.JButton jButton2;
    public javax.swing.JButton jButton9;
    private javax.swing.JComboBox jComboBox3;
    private javax.swing.JComboBox jComboBox4;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JRadioButton jRadioButton5;
    private javax.swing.JRadioButton jRadioButton6;
    public javax.swing.JTextField jTextField1;
    public java.awt.List list1;
    // End of variables declaration//GEN-END:variables
}
