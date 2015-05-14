package hr.ZGPetrol.converter;

import hr.ZGPetrol.ejb.UserEJB;
import hr.ZGPetrol.model.User;

import java.util.logging.Logger;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

@FacesConverter(forClass=User.class)
public class UserConverter implements javax.faces.convert.Converter {
       Logger log = Logger.getLogger(UserConverter.class.getName());
      
       @Inject
       UserEJB userEjb;
      
      
       @Override
       public Object getAsObject(FacesContext context, UIComponent component,
                    String value) {
             if (value==null || "".equals(value))
                    return null;
             log.warning("getAsObject (User)");
             return userEjb.findByPrimaryKey(Long.parseLong(value));
       }
 
       @Override
       public String getAsString(FacesContext context, UIComponent component,
                    Object value) {
             if (value==null)
                    return "";
             if (value instanceof String)
                    return value.toString();
             User r = (User)value;
             log.warning("getAsString (User)");
             return "" + r.getId();
       }
 
}