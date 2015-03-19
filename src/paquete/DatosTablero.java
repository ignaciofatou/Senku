/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paquete;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Ignacio
 */
public class DatosTablero {
    
    private final String FICHERO_TABLEROS = "Tableros.txt";
    private final String RUTA_TABLEROS    = "/config/";
    
    //Busca en el Fichero de configuracion todos los Nombres de Tableros
    //y los Retorna en un ArrayList
    public ArrayList<String> getNombresTableros(){

        //ArrayList para Guardar todos los Nombres de los Tableros
        ArrayList<String> nombreTableros = new ArrayList();
        
        //Ruta y Nombre del Fichero de Configuracion de los Tableros
        String nombreFichero = new File("").getAbsolutePath() + RUTA_TABLEROS + FICHERO_TABLEROS;
        String linea;

        //Declarar una variable BufferedReader
        BufferedReader br = null;
        try {
            //Crear un objeto BufferedReader al que se le pasa 
            //   un objeto FileReader con el nombre del fichero
            br = new BufferedReader(new FileReader(nombreFichero));

            //Repetir mientras no se llegue al final del fichero
            while ((linea = br.readLine()) != null) {

                //Si encuentra un nuevo nombre de Tablero -> Lo guardamos
                if (linea.contains("["))
                    nombreTableros.add(linea.substring(1, linea.indexOf("]")));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: Fichero no encontrado");
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println("Error de lectura del fichero");
            System.out.println(e.getMessage());
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                System.out.println("Error al cerrar el fichero");
                System.out.println(e.getMessage());
            }
        }
        //Retornamos los Nombres de los Tableros
        return nombreTableros;
    }

    //Retorna en una Matriz el Tablero seleccionado
    public int[][] getTablero(String nombreTablero){

        //Para guardar la Matriz del Tablero Seleccionado
        int[][] tablero = new int[10][10];
        
        //Ruta y Nombre del Fichero de Configuracion de los Tableros
        String nombreFichero = new File("").getAbsolutePath() + RUTA_TABLEROS + FICHERO_TABLEROS;

        //Declarar una variable BufferedReader
        BufferedReader br = null;
        try {
            //Crear un objeto BufferedReader al que se le pasa 
            //   un objeto FileReader con el nombre del fichero
            br = new BufferedReader(new FileReader(nombreFichero));
            //br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(RUTA_TABLEROS + FICHERO_TABLEROS)));

            //Variable para almacenar cada linea
            String texto;
            //Variable para indicar si ha encontrado nuestro tablero
            boolean miTablero = false;
            //Variable de Contador de Posicion
            int contador = 0;

            //Repetir mientras no se llegue al final del fichero
            while ((texto = br.readLine()) != null) {
                
                //Si no hemos encontrado nuestro tablero
                if (!miTablero){
                    //Comprobamos si hemos llegado a Nuestro Tablero
                    if (texto.contains(nombreTablero))
                        miTablero = true;
                }
                //Si hemos encontrado nuestro tablero
                else{                    
                    //Si el Texto Contiene Algo
                    if (!texto.isEmpty()){
                        //Si es la Primera Linea -> Asignamos el Tamaño al Tablero
                        if (contador == 0){
                            tablero = new int[texto.length()][texto.length()];
                        }
                        tablero[contador]=getCadenaTablero(texto);
                    }
                    //Si esta vacio -> Salimos del Bucle
                    else
                        break;
                    
                    //Incrementamos el Contador de Posicion
                    contador++;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: Fichero no encontrado");
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println("Error de lectura del fichero");
            System.out.println(e.getMessage());
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                System.out.println("Error al cerrar el fichero");
                System.out.println(e.getMessage());
            }
        }
        //Retornamos el Tablero
        return tablero;
    }
    

    
    private int[] getCadenaTablero(String cadena){
        //Longitud de la Cadena
        int size = cadena.length();
        
        //Variable para Guardar los Numeros
        int [] cadenaNumeros = new int[size];
        
        //Recorremos la cadena caracter a caracter
        for (int x=0; x<size; x++){
            cadenaNumeros[x]=Integer.valueOf("" + cadena.charAt(x));
        }
        //Retornamos la Cadena con todos los Numeros
        return cadenaNumeros;        
    }
}
