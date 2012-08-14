/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vida;

import java.util.Date;
/**
 *
 * @author SANCHEE
 * @version 1.0
 */
public class Persona implements Persistente {
   private String nombre;
   private String apellido1;
   private String apellido2;
   private Date fechaNacimiento;
   private char genero;
   private String email;
   public Historial historial;

   
   //Para implementar la interface Persistente
   public void grabaBD(Object o){
       //código
   }
   public Object leeBD(String[] params){
        return(this);
    }
   
   // Fin implementación Persistente
public Persona(){
    historial=new Historial(this);
    fechaNacimiento=new Date();
}
 /**
 * 
 * @param nom Nombre de la persona
 */
public void setNombre(String nom){
    nombre=nom;
}
public String getNombre(){
    return(nombre);
}

void setApellido1(String nom){
    apellido1=nom;
}
String getApellido1(){
    return(apellido1);
}

void setApellido2(String nom){
    apellido2=nom;
}
String getApellido2(){
    return(apellido2);
}

public void setFechaNacimiento(Date date){
    fechaNacimiento=date;
}

public Date getFechaNacimiento(){
    return(fechaNacimiento);
}

void setGenero(char c){
    genero=c;
}

char getGenero(){
    return(genero);
}

void setEmail(String em){
    email=em;
}
String getEmail(){
    return(email);
}

Historial getHistorial(){
    return(historial);
}

} //end class