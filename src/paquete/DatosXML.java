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
    
    private final String NOMBRE_XML = "Champions.xml";

    private void generaXML(ArrayList<String> movimientos){

        //Definimos los Equipos de la Semifinal 1

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
            for(String movimiento:movimientos){
                elementoMovimientos.appendChild(getMovimientoXML(documento, movimiento));
            }

            //Crear un elemento FINAL y metemos el elemento PARTIDO
            Element elementoMovimiento = documento.createElement("MOVIMIENTO");
            elementoMovimiento.appendChild(finalisima.getPartidoXML(documento));
            
            //El elemento FINAL lo colgamos del elemento principal CHAMPION
            elementoMovimientos.appendChild(elementoMovimiento);

            //Crear un elemento SEMIFINAL y metemos los elementos PARTIDOS
            Element elementoSemifinal = documento.createElement("SEMIFINAL");
            elementoSemifinal.appendChild(semifinal1.getPartidoXML(documento));
            elementoSemifinal.appendChild(semifinal2.getPartidoXML(documento));
            //El elemento SEMIFINAL lo colgamos del elemento principal CHAMPION
            elementoMovimientos.appendChild(elementoSemifinal);
 

            //Churrro para poder generar el fichero XML
            //Generar el tranformador para obtener el documento XML en un fichero
            TransformerFactory fabricaTransformador = TransformerFactory.newInstance();
            Transformer transformador = fabricaTransformador.newTransformer();
            //Insertar saltos de línea al final de cada línea
            transformador.setOutputProperty(OutputKeys.INDENT, "yes");
            //Añadir 3 espacios delante, en función del nivel de cada nodo
            transformador.setOutputProperty(OutputPropertiesFactory.S_KEY_INDENT_AMOUNT, "3");
            Source origen = new DOMSource(documento);
            Result destino = new StreamResult(NOMBRE_XML);
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
    
    public Element getMovimientoXML(Document documento, String movimiento){
        //Creamos el Elemento PARTIDO
        Element elementoPatido = documento.createElement("PARTIDO");

        //Crear un elemento EQUIPO 1
        Element elementoEquipo1 = documento.createElement("EQUIPO");
        //elementoEquipo1.setTextContent(equipo1);
        
        //Crear un elemento EQUIPO 2
        Element elementoEquipo2 = documento.createElement("EQUIPO");
        //elementoEquipo2.setTextContent(equipo2);
        
        //Colgar los equipos en la etiqueta PARTIDO
        elementoPatido.appendChild(elementoEquipo1);
        elementoPatido.appendChild(elementoEquipo2);

        //Retornamos el Element
        return elementoPatido;
    }

}
