/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.JSF.Descompiladas_6_1;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.util.AjaxRequestBuilder;
import org.primefaces.util.CSVBuilder;
//import org.primefaces.util.StringEncrypter;
import org.primefaces.util.WidgetBuilder;

// Referenced classes of package org.primefaces.context:
//            ApplicationContext

public abstract class RequestContext {

            private static final ThreadLocal INSTANCE = new ThreadLocal();
            public static final String INSTANCE_KEY ="org/primefaces/context/RequestContext.getName()";


            public static RequestContext getCurrentInstance() {
/*  46*/        RequestContext context = (RequestContext)INSTANCE.get();
/*  51*/        if (context == null) {
    System.out.println("a");
/*  52*/            FacesContext facesContext = FacesContext.getCurrentInstance();
/*  53*/            if (facesContext != null && !facesContext.isReleased()) {
    System.out.println("b");
   
    facesContext.getAttributes().forEach((a,b)->{System.out.println("a="+a+" b="+b);});
    
/*  54*/                context = (RequestContext)facesContext.getAttributes().get(INSTANCE_KEY);
/*  55*/                if (context != null) {
    System.out.println("d");
/*  56*/                    INSTANCE.set(context);
                        }
                    }
                }
/*  61*/        return (RequestContext)INSTANCE.get();
            }

            public static void setCurrentInstance(RequestContext context, FacesContext facesContext) {
/*  65*/        if (context == null) {
/*  66*/            INSTANCE.remove();
/*  67*/            if (facesContext != null) {
/*  68*/                facesContext.getAttributes().remove(INSTANCE_KEY);
                    }
                } else {
/*  71*/            INSTANCE.set(context);
/*  72*/            facesContext.getAttributes().put(INSTANCE_KEY, context);
                }
            }

            public static void releaseThreadLocalCache() {
/*  77*/        INSTANCE.remove();
            }

            public abstract boolean isAjaxRequest();

            public abstract void addCallbackParam(String s, Object obj);

            public abstract Map getCallbackParams();

            public abstract List getScriptsToExecute();

            public abstract void execute(String s);

            public abstract void scrollTo(String s);

            public abstract void update(String s);

            public abstract void update(Collection collection);

            public abstract void reset(String s);

            public abstract void reset(Collection collection);

            public abstract WidgetBuilder getWidgetBuilder();

            public abstract AjaxRequestBuilder getAjaxRequestBuilder();

            public abstract CSVBuilder getCSVBuilder();

            public abstract Map getAttributes();

            public abstract void openDialog(String s);

            public abstract void openDialog(String s, Map map, Map map1);

            public abstract void closeDialog(Object obj);

            public abstract void showMessageInDialog(FacesMessage facesmessage);

            public abstract ApplicationContext getApplicationContext();

            public abstract StringEncrypter getEncrypter();

            public abstract void release();

            public abstract boolean isSecure();

            public abstract boolean isIgnoreAutoUpdate();

            public abstract boolean isRTL();

            public abstract void clearTableStates();

            public abstract void clearTableState(String s);

}
