/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paquete;

/**
 *
 * @author Ignacio
 */
public class Movimiento {
    
    //Atributos para Almacenar las posiciones
    private int oldY;
    private int oldX;
    private int newY;
    private int newX;
    private int medY;
    private int medX;
    
    public Movimiento(int p_oldY, int p_oldX, int p_newY, int p_newX){
        this.oldY = p_oldY;
        this.oldX = p_oldX;
        this.newY = p_newY;
        this.newX = p_newX;
        this.medY = getPosMedio(p_oldY, p_newY);
        this.medX = getPosMedio(p_oldX, p_newX);
    }
    //Obtiene la Posicion de Enmedio
    public int getPosMedio(int posA, int posB){
        //Si la Posicion B es Mayor  -> La Posicion de enmedio es A + 1
        if ((posA - posB) < 0)
            return (posA + 1);
        //Si la Posicion B es Menor  -> La Posicion de enmedio es A - 1
        else if ((posA - posB) > 0)
            return (posA - 1);
        //Si Posicion A es igual a B -> La Posicion de enmedio es A o B
        else
            return posA;
    }
    //Comprueba si el Movimiento es Correcto
    //Solo en una de las dos coordenadas debe haber dos Posiciones de diferencia
    public boolean isMovimientoValido(){
        //Si en una de las coordenadas hay 2 posiciones de diferencia -> Correcto
        if ((Math.abs(this.oldY - this.newY) == 2) && (this.oldX - this.newX == 0))
            return true;
        else if ((this.oldY - this.newY == 0) && (Math.abs(this.oldX - this.newX) == 2))
            return true;
        //En Caso contratio -> Movimiento Erroneo
        else
            return false;
    }

    public int getOldY() {
        return oldY;
    }
    public int getOldX() {
        return oldX;
    }
    public int getNewY() {
        return newY;
    }
    public int getNewX() {
        return newX;
    }
    public int getMedY() {
        return medY;
    }
    public int getMedX() {
        return medX;
    }
}
