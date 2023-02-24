/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.JSF.Descompiladas_6_1;
import java.util.Map;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
//import javax.validation.Validator;
//import javax.validation.ValidatorFactory;
import org.primefaces.cache.CacheProvider;
import org.primefaces.config.PrimeConfiguration;

public abstract class ApplicationContext {

            public static final String INSTANCE_KEY = "org/primefaces/context/ApplicationContext.getName()";


            public static ApplicationContext getCurrentInstance() {
/*  41*/        FacesContext facesContext = FacesContext.getCurrentInstance();
/*  43*/        return (ApplicationContext)facesContext.getExternalContext().getApplicationMap().get(INSTANCE_KEY);
            }

            public static void setCurrentInstance(ApplicationContext context, FacesContext facesContext) {
/*  47*/        facesContext.getExternalContext().getApplicationMap().put(INSTANCE_KEY, context);
            }

            public abstract PrimeConfiguration getConfig();

//            public abstract ValidatorFactory getValidatorFactory();

            public abstract CacheProvider getCacheProvider();

            public abstract Map getEnumCacheMap();

            public abstract Map getConstantsCacheMap();

//            public abstract Validator getValidator();

            public abstract void release();

}
