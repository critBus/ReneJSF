/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.JSF;

//import static Utiles.JSF.FacesBean.execute;
//import static Utiles.JSF.FacesBean.getExternalContext;
import Utiles.MetodosUtiles.Archivo;
import Utiles.MetodosUtiles.MetodosUtiles;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.*;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
//import org.primefaces.util.
//import org.apache.commons.fileupload.FileItemFactory;
//.ui-state-default, .ui-widget-content .ui-state-default, .ui-widget-header .ui-state-default

/**
 *
 * @author Rene
 */
import org.primefaces.util.SerializableSupplier;

public abstract class FacesUtil {

    public static String crearImagenYBorrarSiExiste(byte[] bytes, String direccionRelativa) throws IOException {
        String direccion = getRealPath() + direccionRelativa;
        Archivo.crearImagenYBorrarSiExiste(bytes, direccion);
        return direccionRelativa;
    }

    public static StreamedContent getStreamedContent(byte contenido[]) {
//        new SerializableSupplier<ByteArrayInputStream>() {
//            @Override
//            public ByteArrayInputStream get() {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//            }
//        };
        return DefaultStreamedContent.builder().stream(() -> new ByteArrayInputStream(contenido)).build();
//      return  new DefaultStreamedContent(new ByteArrayInputStream(contenido));
    }

    public static StreamedContent getStreamedContent(String direccion) throws FileNotFoundException {
        return DefaultStreamedContent.builder().stream(() -> {
            try {
                return new FileInputStream(new File(FacesUtil.getRealPath() + direccion));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(FacesUtil.class.getName()).log(Level.SEVERE, null, ex);
            }
            return null;
        }).build();
//    return new DefaultStreamedContent(new FileInputStream(new File(FacesUtil.getRealPath()+ "resources\\img\\u9.png")));
    }

    public static void mensajeERROR(String mensaje) {
        mensaje(FacesMessage.SEVERITY_ERROR, mensaje);
    }

    public static void mensajeINFO(String mensaje) {
        mensaje(FacesMessage.SEVERITY_INFO, mensaje);
    }

    private static void mensaje(FacesMessage.Severity tipo, String mensaje) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(tipo, mensaje, null));
    }

    public static void responderException(Exception ex) {
        System.out.println("erorrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr");
        String mensajesError[] = MetodosUtiles.getResponderExceptionMensage(ex);
        if (mensajesError == null) {
            mensajesError = new String[]{"Error de codigo"};
        }
        mensajeERROR(mensajesError[0]);
    }

    public static void showDialog(String id) {
        execute("PF('" + id + "').show();");
        //RequestContext.getCurrentInstance().execute("PF('"+id+"').show();");
    }

    public static void hideDialog(String id) {
        execute("PF('" + id + "').hide();");
    }

    public static void execute(String execute) {
//        Utiles.JSF.Descompiladas_6_1.RequestContext cur=Utiles.JSF.Descompiladas_6_1.RequestContext.getCurrentInstance();
//        System.out.println("cur="+cur);
//        if(cur!=null){
//            System.out.println("no es null");
//         cur.execute(execute);
//        }
//System.out.println("execute="+execute);
//System.out.println("PrimeRequestContext.getCurrentInstance()="+PrimeRequestContext.getCurrentInstance());
//        System.out.println("PrimeRequestContext.getCurrentInstance().getScriptsToExecute()="+PrimeRequestContext.getCurrentInstance().getScriptsToExecute());
        PrimeRequestContext.getCurrentInstance().getScriptsToExecute().add(execute);
        //PrimeRequestContext.getCurrentInstance().getInitScriptsToExecute().add(execute);
//   getRequestContext().getScriptsToExecute().add(statement);
        // RequestContext.getCurrentInstance().execute(execute);
//     org.primefaces.webapp.MultipartRequest.
    }

    public static void bibrarDialog(String id) {
        execute("PF('" + id + "').jq.effect(\"shake\", {times:5}, 100);");
    }

    public static void irA(String direccion) throws IOException {
        //execute("location.href=\"" + direccion + "\";");
        getExternalContext().redirect(direccion);
    }

    public static FacesContext getCurrentInstance() {
        return FacesContext.getCurrentInstance();
    }

    public static ExternalContext getExternalContext() {
        return getCurrentInstance().getExternalContext();
    }

    public static void putSessionMap(String llave, Object o) {
        getExternalContext().getSessionMap().put(llave, o);
    }

    public static <E> E getSessionMap(String llave) {
        return (E) getExternalContext().getSessionMap().get(llave);
    }

    public static boolean existeSessionMap(String llave) {
        return getExternalContext().getSessionMap().get(llave) != null;
    }

    public static void invalidateSession() {
        getExternalContext().invalidateSession();
    }

    public static UIComponent getComponent(String... ids) {
        String ubicacion = "";
        for (String id : ids) {
            ubicacion += ":" + id;
        }
        // FacesContext.getCurrentInstance().getViewRoot().findComponent(":form:cars");
        return getCurrentInstance().getViewRoot().findComponent(ubicacion);
    }

//    public static void resetearDataTable(String... ids) {
//        UIComponent ui = getComponent(ids);
//        ui.setValueExpression("sortBy", null);
//    }

    public static String getRealPath() {
        return getExternalContext().getRealPath("");
    }
//      public static void setLocaleCalendarES(){
//        execute("PrimeFaces.locales['es']={\n" +
//"	closeText:'Cerrar',\n" +
//"	prevText:'Anterior',\n" +
//"	nextText:'Siguiente',\n" +
//"	monthNames:['Enero','Febrero','Marzo','Abril','Mayo','Junio','Julio','Agosto','Septiembre','Octubre','Noviembre','Diciembre'],\n" +
//"	monthNamesShort:['Ene','Feb','Mar','Abr','May','Jun','Jul','Ago','Sep','Oct','Nov','Dic'],\n" +
//"	dayNames:['Domingo','Lunes','Martes','Miercoles','Jueves','Viernes','Sabado'],\n" +
//"	dayNamesShort:['Dom','Lun','Mar','Mie','Jue','Vie','Sab'],\n" +
//"	dayNamesMin:['D','L','MA','MI','J','V','S'],\n" +
//"	weekHeader:'Semana',\n" +
//"	firstDay:1,\n" +
//"	isRTL:false,\n" +
//"	showMonthAfterYear:false,\n" +
//"	yearSuffix:'',\n" +
//"	timeOnlyTitle:'Solo hora',\n" +
//"	timeText:'Tiempo',\n" +
//"	hourText:'Hora',\n" +
//"	minuteText:'Minuto',\n" +
//"	secondText:'Segundo',\n" +
//"	currentText:'Fecha actual',\n" +
//"	ampm:false,\n" +
//"	month:'Mes',\n" +
//"	week:'Semana',\n" +
//"	day:'Dia',\n" +
//"	allDayText:'Todo el dia'\n" +
//"};");
//    }

    
    public static void resetearFitrosTabla(String id) {
        DataTable table = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent(id);
        table.reset();
    }
    
    public static void update(String id){
    PrimeFaces.current().ajax().update(id);
    }
}
