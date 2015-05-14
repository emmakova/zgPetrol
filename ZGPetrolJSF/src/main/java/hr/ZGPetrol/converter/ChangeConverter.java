package hr.ZGPetrol.converter;

import hr.ZGPetrol.ejb.ChangeEJB;
import hr.ZGPetrol.model.Change;

import java.util.logging.Logger;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

@FacesConverter(forClass=Change.class)
public class ChangeConverter implements javax.faces.convert.Converter {
       Logger log = Logger.getLogger(ChangeConverter.class.getName());
      
       @Inject
       ChangeEJB changeEjb;
      
      
       @Override
       public Object getAsObject(FacesContext context, UIComponent component,
                    String value) {
             if (value==null || "".equals(value))
                    return null;
             log.warning("getAsObject (Change)");
             return changeEjb.findByPrimaryKey(Long.parseLong(value));
       }
 
       @Override
       public String getAsString(FacesContext context, UIComponent component,
                    Object value) {
             if (value==null)
                    return "";
             if (value instanceof String)
                    return value.toString();
             Change c = (Change)value;
             log.warning("getAsString (Change)");
             return "" + c.getId();
       }
 
}

