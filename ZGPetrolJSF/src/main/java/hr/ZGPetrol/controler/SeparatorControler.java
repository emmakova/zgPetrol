package hr.ZGPetrol.controler;

import hr.ZGPetrol.ejb.SeparatorEJB;
import hr.ZGPetrol.model.Pump;
import hr.ZGPetrol.model.Separator;

import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
@ManagedBean
public class SeparatorControler {
	public static Logger log = Logger.getLogger(SeparatorControler.class.getName());

	@Inject
	SeparatorEJB separatorEJB;
	@Inject
	UserSession userSession;
	
	Separator separator;
	
	List<Separator> separators;
	
	Pump pump;
	double kanalica;
	int okna;
	int slivnici;
	
	String action;
	
	@PostConstruct
	public void init(){
		log.warning("-------------------init separator controller");
		pump = userSession.getPump();
		this.separators = pump.getSeparators();
		
		if(userSession.getSeparator() != null){
			separator = userSession.getSeparator();
			kanalica = separator.getKanalica();
			okna = separator.getOkna();
			slivnici = separator.getSlivnici();
			action = "edit";
		} else {
			action = "add";
		}
		
	}
	
	public String addSeparator(){
		action = "add";
		userSession.setSeparator(null);
		return "addSeparator.xhtml?faces-redirect=true";
	}
	
	public void insertSeparator(){
		FacesContext context = FacesContext.getCurrentInstance();
		switch (action) {
		case "add":
			separator = new Separator(kanalica, okna, slivnici, pump);
			
			try{
				separatorEJB.insert(separator);
				userSession.getPump().addSeparator(separator);
				context.getExternalContext().getFlash().setKeepMessages(true);
				context.addMessage(null, new FacesMessage("Successful", "Novi separator uspjesno dodan!!!"));
				FacesContext.getCurrentInstance().getExternalContext().redirect("pump.xhtml");
			}catch(Exception e){
				context.getExternalContext().getFlash().setKeepMessages(true);
				context.addMessage(null, new FacesMessage("Error", "Doslo je do greske pri dodavanju separatora!!!"));
			}
			break;
		case "edit":
			separator.setKanalica(kanalica);
			separator.setOkna(okna);
			separator.setSlivnici(slivnici);
			try{
				separatorEJB.update(separator);
				userSession.setSeparator(null);
				context.getExternalContext().getFlash().setKeepMessages(true);
				context.addMessage(null, new FacesMessage("Successful", "Separator uspjesno izmijenjen!!!"));
				FacesContext.getCurrentInstance().getExternalContext().redirect("pump.xhtml");
			}catch(Exception e){
				userSession.setSeparator(null);
				context.getExternalContext().getFlash().setKeepMessages(true);
				context.addMessage(null, new FacesMessage("Error", "Doslo je do greske pri izmijenjivanju separatora!!!"));
			}
			break;
		default:
			break;
		}
		
	}
	public void removeSeparator(Separator s){
		separatorEJB.delete(s);
		separators.remove(s);
	}
	
	public String editSeparator(Separator s){
		userSession.setSeparator(s);
		return "addSeparator.xhtml?faces-redirect=true";
	}

	public Pump getPump() {
		return pump;
	}

	public void setPump(Pump pump) {
		this.pump = pump;
	}

	public Separator getSeparator() {
		return separator;
	}

	public void setSeparator(Separator separator) {
		this.separator = separator;
	}

	public double getKanalica() {
		return kanalica;
	}

	public void setKanalica(double kanalica) {
		this.kanalica = kanalica;
	}

	public int getOkna() {
		return okna;
	}

	public void setOkna(int okna) {
		this.okna = okna;
	}

	public int getSlivnici() {
		return slivnici;
	}

	public void setSlivnici(int slivnici) {
		this.slivnici = slivnici;
	}

	public List<Separator> getSeparators() {
		return separators;
	}

	public void setSeparators(List<Separator> separators) {
		this.separators = separators;
	}
}
