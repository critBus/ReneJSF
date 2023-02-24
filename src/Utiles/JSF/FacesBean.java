/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.JSF;
//import  Utiles.JSF.FacesUtil;
//import static Utiles.JSF.FacesUtil.getCurrentInstance;
//import static Utiles.JSF.FacesUtil.getExternalContext;
import Utiles.ClasesUtiles.Interfases.Visuales.ControladorVisual;
import Utiles.MetodosUtiles.MetodosUtiles;
import java.io.IOException;
import java.io.Serializable;
import javax.annotation.PostConstruct;

//import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
//import org.primefaces.context.RequestContext;
/**
 *
 * @author Rene
 */
public abstract class FacesBean implements Serializable,ControladorVisual{
    
     @PostConstruct
    public void ini(){}
    public static String crearImagenYBorrarSiExiste(byte[] bytes, String direccionRelativa) throws IOException {
    return FacesUtil.crearImagenYBorrarSiExiste(bytes, direccionRelativa);
    }
    public static void mensajeERROR(String mensaje){
       FacesUtil.mensajeERROR(mensaje);
    }
    public static void mensajeINFO(String mensaje){
       FacesUtil.mensajeINFO(mensaje);
    }
   
    public static void responderException(Exception ex){
  FacesUtil.responderException(ex);
    }
    public static void showDialog(String id){
   FacesUtil.showDialog(id);
    }
    public static void hideDialog(String id){
      FacesUtil.hideDialog(id);
    }
    public static void execute(String execute){
     FacesUtil.execute(execute);
    }
    public static void bibrarDialog(String id){
   FacesUtil.bibrarDialog(id);
    }
    public static void irA(String direccion) throws IOException{
      FacesUtil.irA(direccion);
    }
    
    public static FacesContext getCurrentInstance(){
    return FacesUtil.getCurrentInstance();
    }
    public static ExternalContext getExternalContext(){
    return FacesUtil.getExternalContext();
    }
    public static void putSessionMap(String llave,Object o){
FacesUtil.putSessionMap(llave, o);
    }
    public static <E> E getSessionMap(String llave){
    return  FacesUtil.<E>getSessionMap(llave);
    }
    public static boolean existeSessionMap(String llave){
    return   FacesUtil.existeSessionMap(llave);
    }
             public static void invalidateSession(){
    FacesUtil.invalidateSession();
    }
             public static UIComponent getComponent(String... ids){
       
        return FacesUtil.getComponent(ids);
    }
//     public static void resetearDataTable(String... ids){
//     FacesUtil.resetearDataTable(ids);
//     }
     public static String getRealPath(){
    return FacesUtil.getRealPath();
    }
     
         public static void resetearFitrosTabla(String id) {
         FacesUtil.resetearFitrosTabla(id);
         }
         public static void update(String id){
         FacesUtil.update(id);
         }
//     public static int inT(String numero){
//         return MetodosUtiles.inT(numero);
//     }
//    public static void setLocaleCalendarES(){
//        FacesUtil.setLocaleCalendarES();
//    }
}
