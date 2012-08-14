/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vida;

/**
 *
 * @author SANCHEE
 */
public class TipoMedida {
    public String nombre;
    public String unidadMedida;
    
    public TipoMedida(String n, String um) {
        nombre=n;
        unidadMedida=um;
    }
    
    public String getNombre(){
        return(nombre);
    }
    
    public String getUnidadMedida(){
        return(unidadMedida);
    }
    
    public void setNombre(String n) {
        nombre=n;
    }
    
    public void setUnidadMedida(String n){
        unidadMedida=n;
    }
}