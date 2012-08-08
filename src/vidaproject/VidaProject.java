/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vidaproject;

import vida.*;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;
import vidaSwing.*;

/**
 *
 * @author SANCHEE
 */
public class VidaProject {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        
        List<Persona> lp=VidaStub.creaDatos();
        Persona eva=new Persona();
        eva=lp.get(0);
        
        eva.historial.ordenaHistorial();
        eva.historial.getListaMedida(VidaConst.PESO).iteraListaMedida();
        Peso pe4=(Peso)eva.historial.getListaMedida(VidaConst.PESO).getMedidaAnterior(VidaConst.stringToFecha("2012-07-28"),VidaConst.PESO);
        if (pe4!=null) {
            System.out.println("Fecha anterior es " + VidaConst.fechaToString(pe4.getFechaMedida()) + " y peso " + pe4.getValor());
        }
        eva.historial.getListaMedida(VidaConst.PESO).iteraListaMedida();
        Peso pe5=(Peso)eva.historial.getListaMedida(VidaConst.PESO).getMedidaMasReciente(VidaConst.PESO);
        if (pe5!=null){
            System.out.println("Tu último peso tomado es " + pe5.getValor() + " tomado el " + VidaConst.fechaToString(pe5.getFechaMedida()));
        }
        //List<Medida> lis=eva.historial.getMedidaIntervalo(VidaConst.stringToFecha("2012-07-23"),
        //        VidaConst.stringToFecha("2012-07-23"), VidaConst.PESO);
        ListaMedida lis=eva.historial.getListaMedida(VidaConst.PESO).getMedidaIntervaloHoy(VidaConst.stringToFecha("2012-07-24"),VidaConst.PESO);
        System.out.println("Resultado getMedidaIntervalo...");
        lis.iteraListaMedida();
        
        System.out.println("El peso máximo ha sido " + ((Peso)eva.historial.getListaMedida(VidaConst.PESO).maximoValor(VidaConst.PESO)).getValor());
        System.out.println("El peso mínimo ha sido " + ((Peso)eva.historial.getListaMedida(VidaConst.PESO).minimoValor(VidaConst.PESO)).getValor());
        
        Persona br=new Persona();
        br=lp.get(1);
        System.out.println("voy a hacer cálculos de BMI...");
        BMI bmi=new BMI(eva);
        System.out.println("CALCULO BMI para Evita hoy: " + bmi.getValor());
        BMI bmi2=new BMI(br);
        System.out.println("CALCULO BMI para BR hoy: " + bmi2.getValor());
        VidaSwingMain ventana = new VidaSwingMain();
        ventana.go();
    }
}