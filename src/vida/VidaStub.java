/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vida;

import java.util.*;
/**
 *
 * @author SANCHEE
 */
public class VidaStub {
    public static List<Persona> creaDatos() throws Exception {
        List<Persona> lp=new ArrayList<>();
        
        Persona eva=new Persona();
        eva.setNombre("Eva");
        //System.out.println("Soy VidaStub, he creado a la persona: " + eva.getNombre());
        eva.setFechaNacimiento(VidaConst.stringToFecha("1972-04-03"));
        //System.out.println("Soy VidaStub, fecha nacimiento: " + VidaConst.fechaToString(eva.getFechaNacimiento()));
        Date fe=VidaConst.stringToFecha("2012-07-24");
        Peso pe=new Peso(eva,fe,VidaConst.MANUAL,57);
        Peso pe1=new Peso(eva,VidaConst.stringToFecha("2012-07-23"),VidaConst.MANUAL,56.8f);
        Peso pe2=new Peso(eva,VidaConst.stringToFecha("2012-07-25"),VidaConst.MANUAL,56.8f);
        Peso pe3=new Peso(eva,VidaConst.stringToFecha("2012-07-26"),VidaConst.MANUAL,56.4f);
        Peso pe6=new Peso(eva,VidaConst.stringToFecha("2012-07-27"),VidaConst.MANUAL,56.4f);
        eva.historial.getListaMedida(VidaConst.PESO).anadeElemento(pe3);
        eva.historial.getListaMedida(VidaConst.PESO).anadeElemento(pe);
        eva.historial.getListaMedida(VidaConst.PESO).anadeElemento(pe2);
        eva.historial.getListaMedida(VidaConst.PESO).anadeElemento(pe1);
        eva.historial.getListaMedida(VidaConst.PESO).anadeElemento(pe6);
        
        
        Altura[] arrayAlturasEva = {
            new Altura(eva,VidaConst.stringToFecha("1995-01-01"),VidaConst.MANUAL,1.50f),
            new Altura(eva,VidaConst.stringToFecha("2000-01-01"),VidaConst.MANUAL,1.51f)
        };
        for (int i=0;i<arrayAlturasEva.length;i++){
            if (arrayAlturasEva[i]!=null){
                eva.historial.getListaMedida(VidaConst.ALTURA).anadeElemento(arrayAlturasEva[i]);
            }
            //System.out.println("VidaStub, añadiendo alturas de Eva, iteración: " + i);
        }         
        lp.add(eva);
        
        //System.out.println("Soy VidaStub, voy a iterar la lista de PESO de Eva");
        //lp.get(0).getHistorial().getListaMedida(VidaConst.ALTURA).iteraListaMedida();
        //eva.getHistorial().getListaMedida(VidaConst.PESO).iteraListaMedida();
        Persona br=new Persona();
        br.setNombre("Blanca Rosa");
        br.setFechaNacimiento(VidaConst.stringToFecha("1966-10-24"));
        
        Peso[] arrayPesosBR = { 
            new Peso(br,VidaConst.stringToFecha("2012-05-20"),VidaConst.MANUAL,53.8f),
            new Peso(br,VidaConst.stringToFecha("2012-05-30"),VidaConst.MANUAL,54.2f),
            new Peso(br,VidaConst.stringToFecha("2012-06-15"),VidaConst.MANUAL,53.9f),
            new Peso(br,VidaConst.stringToFecha("2012-06-29"),VidaConst.MANUAL,53.5f),
            new Peso(br,VidaConst.stringToFecha("2012-07-07"),VidaConst.MANUAL,53.1f),
            new Peso(br,VidaConst.stringToFecha("2012-07-23"),VidaConst.MANUAL,52.5f)
        };
        
        Altura[] arrayAlturasBR = {
            new Altura(br,VidaConst.stringToFecha("1995-01-01"),VidaConst.MANUAL,1.51f),
            new Altura(br,VidaConst.stringToFecha("2000-01-01"),VidaConst.MANUAL,1.54f)
        };
        
        for (int i=0;i<arrayPesosBR.length;i++){
            if (arrayPesosBR[i]!=null){
                br.historial.getListaMedida(VidaConst.PESO).anadeElemento(arrayPesosBR[i]);
            }
        }
        for (int i=0;i<arrayAlturasBR.length;i++){
            if (arrayAlturasBR[i]!=null){
                br.historial.getListaMedida(VidaConst.ALTURA).anadeElemento(arrayAlturasBR[i]);
            }
        }        
        lp.add(br);
        return(lp);
    }
}