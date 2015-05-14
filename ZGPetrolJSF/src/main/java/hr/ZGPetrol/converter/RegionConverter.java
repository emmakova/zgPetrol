package hr.ZGPetrol.converter;

import hr.ZGPetrol.ejb.RegionEJB;
import hr.ZGPetrol.model.Region;

import java.util.logging.Logger;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
 
@FacesConverter(forClass=Region.class)
public class RegionConverter implements javax.faces.convert.Converter {
       Logger log = Logger.getLogger(RegionConverter.class.getName());
      
       @Inject
       RegionEJB regionEjb;
      
      
       @Override
       public Object getAsObject(FacesContext context, UIComponent component,
                    String value) {
             if (value==null || "".equals(value))
                    return null;
             log.warning("getAsObject (Region)");
             return regionEjb.findByPrimaryKey(Long.parseLong(value));
       }
 
       @Override
       public String getAsString(FacesContext context, UIComponent component,
                    Object value) {
             if (value==null)
                    return "";
             if (value instanceof String)
                    return value.toString();
             Region r = (Region)value;
             log.warning("getAsString (Region)");
             return "" + r.getId();
       }
 
}