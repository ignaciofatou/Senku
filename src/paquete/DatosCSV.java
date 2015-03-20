/*
 * En un fichero de texto con formato CSV se almacenará la fecha y hora actual, 
 * nombre de la variante del tablero, el número de fichas que han quedado 
 * en el tablero y el tiempo empleado en la partida. 
 * El interfaz de usuario debe disponer de un botón para Reiniciar 
 * el tablero que al pulsarlo volverá el tablero a su estado inicial 
 * y se almacenarán los datos que se acaban de indicar.
 */



package paquete;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Ignacio
 */
public class DatosCSV {

    //Constantes
    private final String RUT_CSV   = "datos/";
    private final String NOM_CSV   = "Partidas";
    private final String EXT_CSV   = ".csv";
    private final String SEPARADOR = ";";
    private final String CABECERA  = "FECHA" + SEPARADOR + "HORA" + SEPARADOR + "TABLERO" + SEPARADOR + "FICHAS RESTANTES" + SEPARADOR + "TIEMPO";

    //Atributos
    BufferedWriter  bw;
    private String  nombFichero;    
    private String  fechaInicial;
    private String  horaInicial;
    private String  nombreTablero;
    private boolean partidaEnMarcha = false;
    
    public DatosCSV(){

        //Generamos el Nombre del Fichero datos/Partidas.csv
        this.nombFichero = RUT_CSV + NOM_CSV + EXT_CSV;
        
        try {
            //Si el Fichero ya Existe
            if (new File(this.nombFichero).exists()){
                //Crear un objeto BufferedWriter. Si ya existe el fichero, 
                //añade automáticamente su contenido anterior.
                bw = new BufferedWriter(new FileWriter(this.nombFichero, true));
            }
            //Si el Fichero no Existe
            else{
                //Crear un objeto BufferedWriter. Si ya existe el fichero, 
                //  se borra automáticamente su contenido anterior.
                bw = new BufferedWriter(new FileWriter(this.nombFichero));
                
                //Guardamos la Cabecera
                bw.write(CABECERA + "\n");
            }
        }
        catch (IOException e) {
           System.out.println("Error de escritura del fichero");
           System.out.println(e.getMessage());
        }
    }
    //Guardamos los Datos de la Nueva Partida
    public void setNuevaPartida(String nomTablero){
        this.fechaInicial    = Fecha.getFecha();
        this.horaInicial     = Fecha.getHora();
        this.nombreTablero   = nomTablero;
        this.partidaEnMarcha = true;
    }
    //Guardamos los Datos en el CSV
    public void setFinPartida(int fichasRestantes, String tiempoTotal){
        
        //Si la Partida esta en Marcha
        if (partidaEnMarcha == true){
            try {
                //Escribimos la Fecha, Hora, Tablero, Fichas y Tiempo
                bw.write(fechaInicial + SEPARADOR + horaInicial + SEPARADOR + nombreTablero + SEPARADOR + fichasRestantes + SEPARADOR + tiempoTotal + "\n");
            }catch (IOException e) {
               System.out.println("Error de escritura del fichero");
               System.out.println(e.getMessage());
            }
            //Desactivamos la Partida en Marcha
            this.partidaEnMarcha = false;
        }
    }
    //Cerramos el Fichero
    public void setCloseFichero(){
        try {
            //Cerrar el buffer
            if (bw != null) {
                bw.close();
            }
        } catch (Exception e) {
            System.out.println("Error al cerrar el fichero");
            System.out.println(e.getMessage());
        }
    }

    

    public void generaFicheroCSV(){
        
        //Generamos el Nombre del Fichero datos/Partida_2015319_211341.csv
        this.nombFichero = RUT_CSV + NOM_CSV + Fecha.getFechaHora() + EXT_CSV;
        String texto     = "Texto de prueba";
        BufferedWriter bw = null;
        try {
            //Crear un objeto BufferedWriter. Si ya existe el fichero, 
            //  se borra automáticamente su contenido anterior.
            bw = new BufferedWriter(new FileWriter(this.nombFichero));
            //Escrbir en el fichero el texto con un salto de línea
            bw.write(texto + "\n");
        }
        catch(Exception e) {
           System.out.println("Error de escritura del fichero");
           System.out.println(e.getMessage());
        }
        finally {
            try {
                //Cerrar el buffer
                if(bw != null)
                    bw.close();
            }
            catch (Exception e) {
                System.out.println("Error al cerrar el fichero");
                System.out.println(e.getMessage());
            }
        }
    }
    
}
