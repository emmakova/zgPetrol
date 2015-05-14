package hr.ZGPetrol.converter;

import hr.ZGPetrol.ejb.CityEJB;
import hr.ZGPetrol.model.City;

import java.util.logging.Logger;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
 
@FacesConverter(forClass=City.class)
public class CityConverter implements javax.faces.convert.Converter {
       Logger log = Logger.getLogger(CityConverter.class.getName());
      
       @Inject
       CityEJB cityEjb;
      
      
       @Override
       public Object getAsObject(FacesContext context, UIComponent component,
                    String value) {
             if (value==null || "".equals(value))
                    return null;
             log.warning("getAsObject (City)");
             return cityEjb.findByPrimaryKey(Long.parseLong(value));
       }
 
       @Override
       public String getAsString(FacesContext context, UIComponent component,
                    Object value) {
             if (value==null)
                    return "";
             if (value instanceof String)
                    return value.toString();
             City c = (City)value;
             log.warning("getAsString (City)");
             return "" + c.getId();
       }
 
}
