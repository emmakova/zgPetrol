package hr.ZGPetrol.converter;

import hr.ZGPetrol.ejb.CountryEJB;
import hr.ZGPetrol.model.Country;

import java.util.logging.Logger;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

@FacesConverter(forClass=Country.class)
public class CountryConverter implements javax.faces.convert.Converter {
       Logger log = Logger.getLogger(CountryConverter.class.getName());
      
       @Inject
       CountryEJB countryEjb;
      
      
       @Override
       public Object getAsObject(FacesContext context, UIComponent component,
                    String value) {
             if (value==null || "".equals(value))
                    return null;
             log.warning("getAsObject (Country)");
             return countryEjb.findByPrimaryKey(Long.parseLong(value));
       }
 
       @Override
       public String getAsString(FacesContext context, UIComponent component,
                    Object value) {
             if (value==null)
                    return "";
             if (value instanceof String)
                    return value.toString();
             Country c = (Country)value;
             log.warning("getAsString (Country)");
             return "" + c.getId();
       }
 
}
