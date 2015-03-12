/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paquete;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Ignacio
 */
public class PanelTablero extends javax.swing.JPanel {

    private static final int LARGUE     = 7;
    private static final int RADIO_BTN  = 15;
    private static final int MARGIN_BTN = 5;
    private final     String COL_NORMAL = "FF0000";
    private final     String COL_SELECC = "0000FF";
    private final        int NORMAL     = 0;
    private final        int SELECC     = 1;
    private final        int VACIO      = 2;
    private final        int OUT_PANEL  = 3;
    private int[][] tablero;
    private ArrayList<String> movimientos;
    private boolean panelActivo;

    //Constantes y Variables Para el Cronometro
    private final static int ONE_SECOND = 1000;
    private int segundos = 0;
    private int minutos = 0;
    private final int TIEMPO_MAXIMO = 2;

    /**
     * Creates new form PanelBotones
     */
    public PanelTablero() {
        initComponents();        
        inicializaTablero();
        panelActivo = false;
    }
    
    private void inicializaTablero(){
        
        tablero = new int[LARGUE][LARGUE];
        movimientos = new ArrayList();
        
        for (int x=0; x<LARGUE; x++){
            for (int y=0; y<LARGUE; y++){
                //Si Esta dentro del Tablero
                if (estaDentroTablero(x, y))
                    tablero[x][y]=NORMAL;
                //Esta fuera del Tablero
                else
                    tablero[x][y]=OUT_PANEL;
            }
        }
        //Posicion de Enmedio
        tablero[3][3]=VACIO;
    }
    
    //Reiniciamos el Tablero
    public void reiniciarTablero(){
        inicializaTablero();
        this.repaint();
        panelActivo = false;
        jLNumMovimientos.setText("0");
        
        //Reiniciamos el Cronometro
        t.restart();
        t.stop();
        segundos = 0;
        minutos = 0;
        jLTiempo.setText("00:00");
    }
    
    private boolean estaDentroTablero(int posY, int posX){
        if ((posY<2) || (posY>4))
            if ((posX<2) || (posX>4))            
                return false;

        //Esta dentro del Panel
        return true;
    }   
            
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        //Obtenemos el color en formato HSB
        float[] colorHSB_Normal = getColorHSB(COL_NORMAL);
        float[] colorHSB_Selecc = getColorHSB(COL_SELECC);
        int posX = MARGIN_BTN;
        int posY = MARGIN_BTN;
        
        for (int y=0; y<LARGUE; y++){
            
            for (int x=0; x<LARGUE; x++){
                
                if (tablero[y][x] == NORMAL)
                    pintaCirculo3D(g, posX, posY, RADIO_BTN, colorHSB_Normal);
                else if (tablero[y][x] == SELECC)
                    pintaCirculo3D(g, posX, posY, RADIO_BTN, colorHSB_Selecc);
                else if (tablero[y][x] == VACIO)
                    pintaCirculoBlanco(g, posX, posY, RADIO_BTN);
                
                posX = posX + (RADIO_BTN * 2) + MARGIN_BTN;
            }
            posX = MARGIN_BTN;
            posY = posY + (RADIO_BTN * 2) + MARGIN_BTN;
        }
    }
    
    private void pintaCirculoBlanco(Graphics g, int posX, int posY, int radio){
        int size = radio * 2;
        g.setColor(Color.WHITE);        
        g.fillOval(posX, posY, size, size);
    }
    private void pintaCirculo3D(Graphics g, int posX, int posY, int radio, float[] colorHSB){
        
        //Calculamos el Tamaño del Circulo
        int size = radio * 2;
        float resta = (float)(0.8/radio);
        float saturation = colorHSB[1];

        for (int x=size; x > 1; x=x-2){
            g.setColor(Color.getHSBColor(colorHSB[0], saturation, colorHSB[2]));
            g.fillOval(posX, posY, x, x);

            //Decrementamos Saturacion
            saturation = (float)saturation - resta;

            //Incrementamos la posicion del siguiente circulo
            if ((x % 4) == 0.0){
                posY++;
                posX++;
            }
        }
    }
    private float[] getColorHSB(String color){
        try{
            //Convertimos el Color en Formato HSB
            int red   = Integer.parseInt(color.substring(0, 2), 16);                
            int green = Integer.parseInt(color.substring(2, 4), 16);
            int blue  = Integer.parseInt(color.substring(4, 6), 16);        
            return Color.RGBtoHSB(red, green, blue, null);
        } catch (Exception ex){
            System.out.println("Error en Conversion a Hexadecimal: "+ ex.getMessage());
        }
        return  null;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLNumMovimientos = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLTiempo = new javax.swing.JLabel();

        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        jLabel1.setText("Movimientos:");

        jLNumMovimientos.setText("0");

        jLabel2.setText("Tiempo:");

        jLTiempo.setText("00:00");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(260, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLTiempo))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLNumMovimientos)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLTiempo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLNumMovimientos))
                .addContainerGap(255, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked

        //Si el Panel esta Activo
        if (panelActivo){
            gestionaCirculos(evt.getY(), evt.getX());
            this.repaint();
        }
    }//GEN-LAST:event_formMouseClicked
    private void gestionaCirculos(int coordenadaY, int coordenadaX){

        int espacioOcupado = (RADIO_BTN * 2) + MARGIN_BTN;
        
        int posY = (coordenadaY - (MARGIN_BTN / 2)) / espacioOcupado;
        int posX = (coordenadaX - (MARGIN_BTN / 2)) / espacioOcupado;
        
        if (estaDentroTablero(posY, posX))
            marcaDesmarcaSeleccionado(posY, posX);
    }
    private void marcaDesmarcaSeleccionado(int newPosY, int newPosX){
        
        boolean hayUno = false;
        
        //Si ya hay uno Seleccionado anteriormente
        if (hayUnoSeleccionado()){
            //Obtenemos la Posicion del Circulo Seleccionado
            int[] pos = getPosicionSeleccionado();
            
            //Si hemos Marcado una que ya esta Seleccionada -> Deseleccionamos
            if ((newPosY == pos[0]) && (newPosX == pos[1]))
                tablero[newPosY][newPosX] = NORMAL;
            //Si hemos Marcado uno que esta vacio -> Comprobamos si es Adyacente
            //al que se marco anteriormente
            else if (tablero[newPosY][newPosX] == VACIO){
                int difPosY = newPosY - pos[0];
                int difPosX = newPosX - pos[1];                
                
                //Si en una de las coordenadas hay 2 posiciones de diferencia
                if (((Math.abs(difPosY) == 2) && (difPosX == 0)) ||
                    ((Math.abs(difPosX) == 2) && (difPosY == 0))){
                    
                    //Inicializamos la Posicion de en medio
                    int posMedioY = newPosY;
                    int posMedioX = newPosX;
                    
                    //Calculamos la Posicion de Enmedio
                    if (difPosY > 0)
                        posMedioY--;
                    else if (difPosY < 0)
                         posMedioY++;
                    else if (difPosX > 0)
                        posMedioX--;
                    else if (difPosX < 0)
                        posMedioX++;
                    
                    //Si la Posicion de en medio contiene un circulo --> Movemos
                    if (tablero[posMedioY][posMedioX] == NORMAL){
                        //Realizamos el Movimiento
                        realizaMovimiento(pos[0], pos[1], posMedioY, posMedioX, newPosY, newPosX);
                    }
                }                
            }
        }
        //Si no hay ninguna Seleccionado y es Normal -> Seleccionamos
        else if (tablero[newPosY][newPosX] == NORMAL)
            tablero[newPosY][newPosX] = SELECC;
    }

    //Realizamos los cambios del Movimiento en la Matriz
    private void realizaMovimiento(int antPosY, int antPosX, int medPosY, int medPosX, int newPosY, int newPosX){
        //Realizamos los cambios del Movimiento en la Matriz
        tablero[newPosY][newPosX] = NORMAL;
        tablero[antPosY][antPosX] = VACIO;
        tablero[medPosY][medPosX] = VACIO;
        
        //Guarda el Movimiento Realizado
        guardaMovimiento(antPosY, antPosX, newPosY, newPosX);

        //Incrementamos el Numero de Movimientos
        jLNumMovimientos.setText(String.valueOf(Integer.valueOf(jLNumMovimientos.getText()) + 1));

        //Comprobamos si ha finalizado la Partida
        compruebaFinPartida();
    }
    //Deshacemos el ultimo Movimiento Realizado
    public void deshacerMovimiento(){
        
        //Si hay al Menos un Movimiento
        if (movimientos.size() > 0){
            //Obtenemos en un String el Ultimo Movimiento Realizado
            String mov_Anterior = movimientos.get(movimientos.size()-1);

            //Obtenemos la Posicion de los Movimientos dentro del String
            int posAntPosY = mov_Anterior.indexOf('[', 0) + 1;
            int posAntPosX = mov_Anterior.indexOf(',', posAntPosY) + 1;
            int posNewPosY = mov_Anterior.indexOf(',', posAntPosX) + 1;
            int posNewPosX = mov_Anterior.indexOf(',', posNewPosY) + 1;

            //Obtenemos los Movimientos dentro del String
            int antPosY = Integer.valueOf(mov_Anterior.substring(posAntPosY, posAntPosX - 1));
            int antPosX = Integer.valueOf(mov_Anterior.substring(posAntPosX, posNewPosY - 1));
            int newPosY = Integer.valueOf(mov_Anterior.substring(posNewPosY, posNewPosX - 1));
            int newPosX = Integer.valueOf(mov_Anterior.substring(posNewPosX, mov_Anterior.indexOf(']', posNewPosY)));

            //Calculamos la Posicion de Enmedio
            int difPosY = newPosY - antPosY;
            int difPosX = newPosX - antPosX;

            //Inicializamos la Posicion de en medio
            int posMedioY = newPosY;
            int posMedioX = newPosX;

            //Calculamos la Posicion de Enmedio
            if (difPosY > 0) {
                posMedioY--;
            } else if (difPosY < 0) {
                posMedioY++;
            } else if (difPosX > 0) {
                posMedioX--;
            } else if (difPosX < 0) {
                posMedioX++;
            }

            //Realizamos los cambios del Movimiento en la Matriz
            tablero[newPosY][newPosX] = VACIO;
            tablero[antPosY][antPosX] = NORMAL;
            tablero[posMedioY][posMedioX] = NORMAL;

            //Borramos el Ultimo Movimiento
            borraMovimiento();

            //Decrementamos el Numero de Movimientos
            jLNumMovimientos.setText(String.valueOf(Integer.valueOf(jLNumMovimientos.getText()) - 1));

            //Repintamos el Panel
            this.repaint();
        }
    }

    //Guardamos en un ArrayList las posiciones del Movimiento Realizado
    private void guardaMovimiento(int antPosY, int antPosX, int newPosY, int newPosX){
        //Realizamos una Copia del Movimiento Realizado
        movimientos.add("[" + antPosY + "," + antPosX + "," + newPosY + "," + newPosX + "]");
    }
    //Borramos del ArrayList el ultimo movimiento
    private void borraMovimiento(){
        movimientos.remove(movimientos.size()-1);
    }
    
    private void compruebaFinPartida(){

        int numNormal = 0;
        boolean perdido = true;

        for (int y=0; y<LARGUE; y++){
            for (int x=0; x<LARGUE; x++){
                //Si la Posicion contiene una bola Normal
                if (tablero[y][x] == NORMAL){
                    numNormal++;

                    //Si de momento no encuentra una Bola adyacente
                    if (perdido){
                        //Compruebo si tiene otra Normal Adyacente en Vertical
                        if ((y >= 1) && (tablero[y - 1][x] == NORMAL)){
                            //Si Delante de la Bola esta vacio o
                            //Detras de la Adyacente esta vacio
                            if ((((y + 1) < LARGUE) && (tablero[y + 1][x] == VACIO)) ||
                                (((y - 2) >= 0    ) && (tablero[y - 2][x] == VACIO)))
                                perdido = false;
                        }
                        //Compruebo si tiene otra Normal Adyacente en Horizontal
                        if ((x >= 1) && (tablero[y][x - 1] == NORMAL)){
                            //Si Arriba de la Bola esta cacio o
                            //Abajo de la Adyacente esta vacio
                            if ((((x + 1) < LARGUE) && (tablero[y][x + 1] == VACIO)) ||
                                (((x - 2) >= 0    ) && (tablero[y][x - 2] == VACIO)))
                                perdido = false;
                        }
                    }
                }
            }
        }
        //Si solo hay unoa Bola Normal -> Ha ganado la partida
        if (numNormal == 1)
            setFinPartida(true);
        //Si no ha encontrado bolas Adyacentes -> Ha perdido la partida
        else if (perdido)
            setFinPartida(false);
    }
    
    
    private boolean hayUnoSeleccionado(){
        for (int y=0; y<LARGUE; y++)
            for (int x=0; x<LARGUE; x++)
                if (tablero[y][x] == SELECC)
                    return true;
        
        return false;
    }
    private int[] getPosicionSeleccionado(){
        int[] pos = new int[2];
        for (int y=0; y<LARGUE; y++)
            for (int x=0; x<LARGUE; x++)
                if (tablero[y][x] == SELECC){
                    pos[0]=y;
                    pos[1]=x;
                }                    
        
        return pos;
    }
    
    private void setFinPartida(boolean ganada){
        
        //Elementos Comunes
        panelActivo = false;
        
        //Paramos el Cronometro
        t.stop();        
        
        //Ha ganado la Partida
        if (ganada){
            //Mostramos un Mensaje de Partida Ganada
            JOptionPane.showMessageDialog(
                    null,
                    "!!Bienn, ha ganado!! - Fin de la partida",
                    "Ha ganado",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        //Ha perdido la Partida
        else{
            //Mostramos un Mensaje de Partida Perdida
            JOptionPane.showMessageDialog(
                    null,
                    "!!Ohh, ha perdido!! - Fin de la partida",
                    "Ha perdido",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    public boolean isPanelActivo() {
        return panelActivo;
    }

    public void setPanelActivo(boolean panelActivo) {
        this.panelActivo = panelActivo;
        
        if (panelActivo){
            t.start();
        }
    }
    
    //Codigo para el Cronometro
    //Creamos el objeto Timer (gracias a la ayuda de Anabel Coronel)
    javax.swing.Timer t = new javax.swing.Timer(ONE_SECOND, new ActionListener() {
        public void actionPerformed(ActionEvent ae) {
            segundos++;

            if (segundos == 60) {
                minutos++;
                segundos = 0;
            }
            if (minutos == TIEMPO_MAXIMO) {
                //Mostramos un Mensaje de Partida Perdida
                setFinPartida(false);
                
                t.stop();
            }
            //Mostramos las Minutos y Segundos
            DecimalFormat formato = new DecimalFormat("00");
            jLTiempo.setText(formato.format(minutos) + ":"
                    + formato.format(segundos));
        }
    }); 


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLNumMovimientos;
    private javax.swing.JLabel jLTiempo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
