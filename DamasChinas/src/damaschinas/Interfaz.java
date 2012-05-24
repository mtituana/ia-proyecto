/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package damaschinas;

import java.net.URL;
import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Victor
 */
public class Interfaz extends javax.swing.JFrame {
    ArrayList <Casilla>mapa=new ArrayList();
    ArrayList <Casilla>yasaltados=new ArrayList();
    JLabel labels[][]=  new JLabel[18][14];
    Casilla de=null;
    int turn=1;
    int max_piezas=10;
    boolean AlgoritmoMax=true;//Si se usa algoritmo, true=max; false=min;
    Icon icon = new ImageIcon("Red-circle.png");
    Icon icon2 = new ImageIcon("Blue-circle.png");
    Icon icon3 = new ImageIcon("Gray-circle.png");
    Icon icon_l = new ImageIcon("Light-Red-circle.png");
    Icon icon2_l = new ImageIcon("Light-Blue-circle.png");    
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
                            mover(encontrarCasilla(cosox,cosoy));
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
    
    public void mover(Casilla obj){
        if(de==null){           
            if(obj.valor==(turn+1)){                            
                de=obj;
                if(turn==1){
                    labels[de.idx][de.idy].setIcon(icon_l);
                }
                else{
                    labels[de.idx][de.idy].setIcon(icon2_l);
                }
            } 
        }
        else{
            if(movimientoPosible(de,obj)){
                yasaltados.add(de);                
                labels[de.idx][de.idy].setIcon(icon3);
                encontrarCasilla(de.idx,de.idy).valor=1;
                 if(turn==1){
                   labels[obj.idx][obj.idy].setIcon(icon);
                   encontrarCasilla(obj.idx,obj.idy).valor=2;
                }
                else{
                    labels[obj.idx][obj.idy].setIcon(icon2);
                    encontrarCasilla(obj.idx,obj.idy).valor=3;
                }
                 de=obj;
                 if(!existenSaltos()){
                    de=null;                 
                    yasaltados.clear();
                    if(turn==1)
                        turn=2;
                    else
                        turn=1;
                     
                 }
                 else{
                     if(turn==1){
                        labels[de.idx][de.idy].setIcon(icon_l);
                    }
                    else{
                        labels[de.idx][de.idy].setIcon(icon2_l);
                    }
                     
                 }
            }
            else{
                System.out.println("nel vos");                
            }
            
        }
        if(hasWon()!=0){
            System.out.println("Gano el jugador "+hasWon());
        }
    }
    
    public boolean existenSaltos(){
        boolean posible=false;
            
            if(saltoPosible(de,encontrarCasilla(de.idx+2,de.idy-1))&&!yasaltados.contains(encontrarCasilla(de.idx+2,de.idy-1))){
                System.out.println("}:");
                return true;
            }
            if(saltoPosible(de,encontrarCasilla(de.idx+2,de.idy+1))&&!yasaltados.contains(encontrarCasilla(de.idx+2,de.idy+1)))
                {
                System.out.println("dafuq");
                return true;
            }
            if(saltoPosible(de,encontrarCasilla(de.idx-2,de.idy-1))&&!yasaltados.contains(encontrarCasilla(de.idx-2,de.idy-1)))
                {
                System.out.println("omg");
                return true;
            }
            if(saltoPosible(de,encontrarCasilla(de.idx-2,de.idy+1))&&!yasaltados.contains(encontrarCasilla(de.idx-2,de.idy-1)))
                {
                System.out.println("so op");
                return true;
            }
            if(saltoPosible(de,encontrarCasilla(de.idx,de.idy-1))&&!yasaltados.contains(encontrarCasilla(de.idx,de.idy-1)))
                {
                System.out.println("im literally crying");
                return true;
            }
            if(saltoPosible(de,encontrarCasilla(de.idx,de.idx+1))&&!yasaltados.contains(encontrarCasilla(de.idx,de.idx-1)))
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
        crearTablero();
        calcularContiguos();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

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
            .addGap(0, 639, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Chat"));

        jTextField1.setText("jTextField1");
        jTextField1.setEnabled(false);

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
                .addComponent(list1, javax.swing.GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE)
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

        jRadioButton4.setSelected(true);
        jRadioButton4.setText("Humano vs Humano");
        jRadioButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton4ActionPerformed(evt);
            }
        });

        jRadioButton5.setText("Humano vs PC");

        jRadioButton6.setText("PC vs PC");

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Minimix - Max", "Minimix - Min" }));
        jComboBox4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox4ActionPerformed(evt);
            }
        });

        jButton9.setText("Iniciar");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton9, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
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
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRadioButton5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRadioButton6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton9)
                .addContainerGap(18, Short.MAX_VALUE))
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
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        
        if(turn==1)
            turn=2;
        else
            turn=1;
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
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ActionPerformed
        // TODO add your handling code here:
        max_piezas=(jComboBox3.getSelectedIndex()+1)*3;
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
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton9;
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
    private javax.swing.JTextField jTextField1;
    private java.awt.List list1;
    // End of variables declaration//GEN-END:variables
}