/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vida;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import vidaproject.VidaProject;

/**
 *
 * @author SANCHEE
 */
public class VidaConst {
    
    public static final int MANUAL = 1;
    public static final int CALCULADO = 2;
    public static final int WEBSERVICE = 3; 
    
    public static final int PESO=1;
    public static final int ALTURA=2;
    public static final int BMI=3;
    public static final int SP02=4;
    
    public static final Map<Integer,TipoMedida> vidaTipoMedida=new HashMap<Integer,TipoMedida>();
    static {
        vidaTipoMedida.put(VidaConst.PESO,new TipoMedida("Peso", "Kg"));
        vidaTipoMedida.put(VidaConst.ALTURA, new TipoMedida("Altura", "m"));
        vidaTipoMedida.put(VidaConst.BMI, new TipoMedida("Indice de masa corporal", "Kg/m^2"));
        vidaTipoMedida.put(VidaConst.SP02, new TipoMedida("Saturación de oxígeno en sangre","%"));
    }
    
    private static final Map<Integer, String> myMap = new HashMap<Integer, String>();
    static {
        myMap.put(1, "one");
        myMap.put(2, "two");
    }

    
    public static Date stringToFecha(String feSt) {
        // formato fecha: "YYYY-MM-dd
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date fe=new Date();
        try {
            fe=sdf.parse(feSt);
        } catch (ParseException ex) {
            Logger.getLogger(VidaProject.class.getName()).log(Level.SEVERE, null, ex);
        }
        return(fe);
    }
    
    public static String fechaToString(Date da) {
        //todo
        SimpleDateFormat sdf=new SimpleDateFormat("yyy-MM-dd");
        String str=sdf.format(da);
        return(str);
    }
    
    public static int restaFechas(Date d1, Date d2){
        //d1 menos d2
        GregorianCalendar c1 = new GregorianCalendar();
        GregorianCalendar c2 = new GregorianCalendar();
        //c1.set(2000, 1, 1);
        c1.setTime(d2);
        //c2.set(2010,1, 1);
        c2.setTime(d1);
        long span = c2.getTimeInMillis() - c1.getTimeInMillis();
        GregorianCalendar c3 = new GregorianCalendar();
        c3.setTimeInMillis(span);
        long numberOfMSInADay = 1000*60*60*24;
        //System.out.println(c3.getTimeInMillis() / numberOfMSInADay);
        return((int)(c3.getTimeInMillis() / numberOfMSInADay));
    }
}