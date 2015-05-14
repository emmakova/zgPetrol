package hr.ZGPetrol.converter;

import hr.ZGPetrol.ejb.PumpEJB;
import hr.ZGPetrol.model.Pump;

import java.util.logging.Logger;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

@FacesConverter(forClass=Pump.class)
public class PumpConverter implements javax.faces.convert.Converter {
       Logger log = Logger.getLogger(PumpConverter.class.getName());
      
       @Inject
       PumpEJB pumpEjb;
      
      
       @Override
       public Object getAsObject(FacesContext context, UIComponent component,
                    String value) {
             if (value==null || "".equals(value))
                    return null;
             log.warning("getAsObject (Pump)");
             return pumpEjb.findByPrimaryKey(Long.parseLong(value));
       }
 
       @Override
       public String getAsString(FacesContext context, UIComponent component,
                    Object value) {
             if (value==null)
                    return "";
             if (value instanceof String)
                    return value.toString();
             Pump p = (Pump)value;
             log.warning("getAsString (Pump)");
             return "" + p.getId();
       }
 
}