package paquete;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Fecha {
    
    private static final String YYYYMMDD = "YMd";
    private static final String HH24MISS = "Hmmss";
    
    //Retorna la Fecha y Hora Actual YYYYMMDD_HH24MISS -> 2015320_215625
    public static String getFechaHora(){
        //Obtener la fecha y hora actual
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat formatoFechaPersonal;
        formatoFechaPersonal = new SimpleDateFormat(YYYYMMDD + "_" + HH24MISS);
        return formatoFechaPersonal.format(cal.getTime());
    }
    //Retorna la Fecha Actual YYYYMMDD -> 2015320
    public static String getFecha(){
        //Obtener la fecha y hora actual
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat formatoFechaPersonal;
        formatoFechaPersonal = new SimpleDateFormat(YYYYMMDD);
        return formatoFechaPersonal.format(cal.getTime());
    }
    //Retorna la Hora Actual HH24MISS -> 215625
    public static String getHora(){
        //Obtener la fecha y hora actual
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat formatoFechaPersonal;
        formatoFechaPersonal = new SimpleDateFormat(HH24MISS);
        return formatoFechaPersonal.format(cal.getTime());
    }
}
