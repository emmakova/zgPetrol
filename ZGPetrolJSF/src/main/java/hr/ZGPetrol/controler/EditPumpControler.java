package hr.ZGPetrol.controler;

import hr.ZGPetrol.ejb.CityEJB;
import hr.ZGPetrol.ejb.CompanyEJB;
import hr.ZGPetrol.ejb.CountryEJB;
import hr.ZGPetrol.ejb.PumpEJB;
import hr.ZGPetrol.model.City;
import hr.ZGPetrol.model.Company;
import hr.ZGPetrol.model.Country;
import hr.ZGPetrol.model.Pump;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class EditPumpControler {

	Country country;
	City city;
	Company company;
	
	private String contact;
	private String name;
	private String address;
	private double latitude;
	private double longitude;
	private String workingTime;
	
	Pump pump;
	
	@Inject
	CountryEJB countryEJB;
	@Inject
	CityEJB cityEJB;
	@Inject
	CompanyEJB companyEJB;
	@Inject
	PumpEJB pumpEJB;
	@Inject
	UserSession userSession;
	
	private List<Country> countries;
	private List<City> cities;
	private List<Company> companies;
	
	@PostConstruct
	public void init(){
		loadCountries();
		loadAllCities();
		loadAllCompanies();
		pump = userSession.getPump();
		country = pump.getCountry();
		city = pump.getCity();
		company = pump.getCompany();
		contact = pump.getContact();
		name = pump.getName();
		address = pump.getAddress();
		latitude = pump.getLatitude();
		longitude = pump.getLongitude();
		workingTime = pump.getWorkingTime();
	}
	
	private void loadCountries() {
		countries = countryEJB.findAll();
	}
	private void loadAllCities() {
		cities = cityEJB.findAll();
	}
	private void loadAllCompanies(){
		companies = companyEJB.findAll();
	}
	private void loadCitiesInCountry() {
		cities = cityEJB.findByCountry(country);
	}
	
	public void onCountryChange(){
		if(country != null){
			 loadCitiesInCountry();
		}
	}
	
	public void updatePump(){
		
		pump.setAddress(address);
		pump.setCountry(country);
		pump.setCity(city);
		pump.setCompany(company);
		pump.setContact(contact);
		pump.setName(name);
		pump.setWorkingTime(workingTime);
		pump.setLatitude(latitude);
		pump.setLongitude(longitude);
		
		FacesContext context = FacesContext.getCurrentInstance();
		try{
			pumpEJB.update(pump);
			context.getExternalContext().getFlash().setKeepMessages(true);
			context.addMessage(null, new FacesMessage("Successful", "Pumpa uspjesno izmjenjena!!!"));
			FacesContext.getCurrentInstance().getExternalContext().redirect("pump.xhtml");
		}catch(Exception e){
			context.getExternalContext().getFlash().setKeepMessages(true);
			context.addMessage(null, new FacesMessage("Error", "Doslo je do greske pri izmjenjivanju pumpe!!!"));
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


	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getWorkingTime() {
		return workingTime;
	}

	public void setWorkingTime(String workingTime) {
		this.workingTime = workingTime;
	}
	public CountryEJB getCountryEJB() {
		return countryEJB;
	}
	public void setCountryEJB(CountryEJB countryEJB) {
		this.countryEJB = countryEJB;
	}
	public CityEJB getCityEJB() {
		return cityEJB;
	}
	public void setCityEJB(CityEJB cityEJB) {
		this.cityEJB = cityEJB;
	}
	public CompanyEJB getCompanyEJB() {
		return companyEJB;
	}
	public void setCompanyEJB(CompanyEJB companyEJB) {
		this.companyEJB = companyEJB;
	}
	public PumpEJB getPumpEJB() {
		return pumpEJB;
	}
	public void setPumpEJB(PumpEJB pumpEJB) {
		this.pumpEJB = pumpEJB;
	}
	public List<Country> getCountries() {
		return countries;
	}
	public void setCountries(List<Country> countries) {
		this.countries = countries;
	}
	public List<City> getCities() {
		return cities;
	}
	public void setCities(List<City> cities) {
		this.cities = cities;
	}
	public List<Company> getCompanies() {
		return companies;
	}
	public void setCompanies(List<Company> companies) {
		this.companies = companies;
	}
}
