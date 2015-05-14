package hr.ZGPetrol.controler;

import hr.ZGPetrol.ejb.ChangeEJB;
import hr.ZGPetrol.ejb.PumpEJB;
import hr.ZGPetrol.model.Change;
import hr.ZGPetrol.model.City;
import hr.ZGPetrol.model.Company;
import hr.ZGPetrol.model.Country;
import hr.ZGPetrol.model.Pump;

import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

@Named
@ViewScoped
public class PumpControler {
	
	public static Logger log = Logger.getLogger(PumpControler.class.getName());
	
	Country country;
	City city;
	Company company;
	Pump pump;
	Change change;
	
	@Inject
	ChangeEJB changeEJB;
	
	@Inject
	PumpEJB pumpEJB;
	
	List<Change> changes;
	
	private MapModel map;
	
	@Inject
	UserSession userSession;
	
	public String fullAddress;
	
	@PostConstruct
	public void init() {
		// TODO Auto-generated method stub
		country = userSession.getPump().getCountry();
		city = userSession.getPump().getCity();
		//company = userSession.company;
		pump = userSession.getPump();
		fullAddress = country.getName().toString() + " / " + city.getName().toString();
		changes = pump.getChanges();
		
		LatLng coords = new LatLng(pump.getLatitude(), pump.getLongitude());
		
		map = new DefaultMapModel();
		map.addOverlay(new Marker(coords, pump.getName()));
	}
	
	public void removePump(){
		FacesContext context = FacesContext.getCurrentInstance();
		try{
			pumpEJB.delete(pump);
			context.getExternalContext().getFlash().setKeepMessages(true);
			context.addMessage(null, new FacesMessage("Successful", "Pumpa uspjesno obrisana!!!"));
			FacesContext.getCurrentInstance().getExternalContext().redirect("search.xhtml");
		} catch(Exception e){
			context.getExternalContext().getFlash().setKeepMessages(true);
			context.addMessage(null, new FacesMessage("Error", "Doslo je do greske pri brisanju pumpe!!!"));
		}
	}
	
	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Pump getPump() {
		return pump;
	}

	public void setPump(Pump pump) {
		this.pump = pump;
	}

	public UserSession getUserSession() {
		return userSession;
	}

	public void setUserSession(UserSession userSession) {
		this.userSession = userSession;
	}

	public void setFullAddress(String fullAddress) {
		this.fullAddress = fullAddress;
	}

	public String getFullAddress() {
		return fullAddress;
	}

	public MapModel getMap() {
		return map;
	}

	public void setMap(MapModel map) {
		this.map = map;
	}

	public List<Change> getChanges() {
		return changes;
	}

	public void setChanges(List<Change> changes) {
		this.changes = changes;
	}

	public Change getChange() {
		return change;
	}

	public void setChange(Change change) {
		this.change = change;
	}

	public ChangeEJB getChangeEJB() {
		return changeEJB;
	}

	public void setChangeEJB(ChangeEJB changeEJB) {
		this.changeEJB = changeEJB;
	}

	
}
