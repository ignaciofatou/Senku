/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paquete;

import com.sun.org.apache.xml.internal.serializer.OutputPropertiesFactory;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


/**
 *
 * @author Ignacio
 */
public class DatosXML {
    
    private final String RUT_XML = "movimientos/";
    private final String NOM_XML = "Mov_";
    private final String EXT_XML = ".xml";

    public void generaXML(ArrayList<Movimiento> movimientos){

        //Definimos el Nombre del Fichero XML
        String nombreXML = RUT_XML + NOM_XML + Fecha.getFechaHora() + EXT_XML;

        //Elementos para el XML
        try {
            DocumentBuilderFactory fabricaCreadorDocumento = DocumentBuilderFactory.newInstance();
            DocumentBuilder creadorDocumento = fabricaCreadorDocumento.newDocumentBuilder();
            //Crear un nuevo documento XML
            Document documento = creadorDocumento.newDocument();


            //Crear el nodo raíz y colgarlo del documento
            Element elementoMovimientos = documento.createElement("MOVIMIENTOS");
            documento.appendChild(elementoMovimientos);
            
            //Recorremos todos los Movimientos
            for (Movimiento movimiento:movimientos){
                elementoMovimientos.appendChild(getMovimientoXML(documento, movimiento));
            }

            //Churrro para poder generar el fichero XML
            //Generar el tranformador para obtener el documento XML en un fichero
            TransformerFactory fabricaTransformador = TransformerFactory.newInstance();
            Transformer transformador = fabricaTransformador.newTransformer();
            //Insertar saltos de línea al final de cada línea
            transformador.setOutputProperty(OutputKeys.INDENT, "yes");
            //Añadir 3 espacios delante, en función del nivel de cada nodo
            transformador.setOutputProperty(OutputPropertiesFactory.S_KEY_INDENT_AMOUNT, "3");
            Source origen = new DOMSource(documento);
            Result destino = new StreamResult(nombreXML);
            transformador.transform(origen, destino);
            
        } catch (ParserConfigurationException ex) {
            System.out.println("ERROR: No se ha podido crear el generador de documentos XML\n"+ex.getMessage());
            ex.printStackTrace();
        } catch (TransformerConfigurationException ex) {
            System.out.println("ERROR: No se ha podido crear el transformador del documento XML\n"+ex.getMessage());
            ex.printStackTrace();
        } catch (TransformerException ex) {
            System.out.println("ERROR: No se ha podido crear la salida del documento XML\n"+ex.getMessage());
            ex.printStackTrace();
        }        
    }
    
    private Element getMovimientoXML(Document documento, Movimiento movimiento){
        //Creamos el Elemento MOVIMIENTO
        Element elementoMovimiento = documento.createElement("MOVIMIENTO");

        //Coordenadas para el Movimiento Anterior
        elementoMovimiento.appendChild(getCoordenadaXML(documento, "ANTERIOR", movimiento.getOldY(), movimiento.getOldX()));
        //Coordenadas para el Movimiento Nuevo
        elementoMovimiento.appendChild(getCoordenadaXML(documento, "NUEVO", movimiento.getNewY(), movimiento.getNewX()));

        //Retornamos el Element
        return elementoMovimiento;
    }
    public Element getCoordenadaXML(Document documento, String momento, int posY, int posX){
        
        //Padre de las Coordenadas
        Element elementoMomento = documento.createElement(momento);

        //Coordenada Y
        Element elementoY = documento.createElement("Y");
        elementoY.setTextContent(String.valueOf(posY));

        //Coordenada X
        Element elementoX = documento.createElement("X");
        elementoX.setTextContent(String.valueOf(posX));
        
        //Le establecemos las Coordenadas
        elementoMomento.appendChild(elementoY);
        elementoMomento.appendChild(elementoX);
        
        //Coordenadas
        return elementoMomento;
    }

}
