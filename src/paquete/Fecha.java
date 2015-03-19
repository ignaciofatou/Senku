package paquete;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Fecha {
    
    private static final String PATRON = "YMd_Hmmss";
    
    //Retorna la Fecha y Hora Actual
    public static String getFechaHora(){
        //Obtener la fecha y hora actual
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat formatoFechaPersonal = new SimpleDateFormat(PATRON);
        return formatoFechaPersonal.format(cal.getTime());
    }
}
