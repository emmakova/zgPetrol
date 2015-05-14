package hr.ZGPetrol.controler;

import hr.ZGPetrol.model.*;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class UserSession implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7357166118808357671L;
	
	User user;
	
	Country country;
	Region region;
	City city;
	Company company;
	Pump pump;
	
	Change change;
	Separator separator;
	Tank tank;
	
	@Inject
	Search search;
	
	public void init(){
		
		country = search.getCountry();
		//region = search.region;
		city = search.getCity();
		//company = search.company;
		pump = search.getPump();
	}
	
	public boolean isLoggedIn() {
        return user != null;
    }
	
	public String logout() {
		this.user=null;
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        
        return "login?faces-redirect=true";
    }
	
	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}
	public Region getRegion() {
		return region;
	}
	public void setRegion(Region region) {
		this.region = region;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Change getChange() {
		return change;
	}

	public void setChange(Change change) {
		this.change = change;
	}

	public Separator getSeparator() {
		return separator;
	}

	public void setSeparator(Separator separator) {
		this.separator = separator;
	}

	public Tank getTank() {
		return tank;
	}

	public void setTank(Tank tank) {
		this.tank = tank;
	}
}
