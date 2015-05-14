package hr.ZGPetrol.converter;

import hr.ZGPetrol.ejb.CompanyEJB;
import hr.ZGPetrol.model.Company;

import java.util.logging.Logger;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

@FacesConverter(forClass=Company.class)
public class CompanyConverter implements javax.faces.convert.Converter {
       Logger log = Logger.getLogger(CompanyConverter.class.getName());
      
       @Inject
       CompanyEJB companyEjb;
      
      
       @Override
       public Object getAsObject(FacesContext context, UIComponent component,
                    String value) {
             if (value==null || "".equals(value))
                    return null;
             log.warning("getAsObject (Company)");
             return companyEjb.findByPrimaryKey(Long.parseLong(value));
       }
 
       @Override
       public String getAsString(FacesContext context, UIComponent component,
                    Object value) {
             if (value==null)
                    return "";
             if (value instanceof String)
                    return value.toString();
             Company c = (Company)value;
             log.warning("getAsString (Company)");
             return "" + c.getId();
       }
 
}
