package hr.ZGPetrol.controler;

import hr.ZGPetrol.ejb.CityEJB;
import hr.ZGPetrol.ejb.CompanyEJB;
import hr.ZGPetrol.ejb.CountryEJB;
import hr.ZGPetrol.ejb.PumpEJB;
import hr.ZGPetrol.ejb.RegionEJB;
import hr.ZGPetrol.model.City;
import hr.ZGPetrol.model.Company;
import hr.ZGPetrol.model.Country;
import hr.ZGPetrol.model.Pump;
import hr.ZGPetrol.model.Region;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class Search {
	
	public static Logger log = Logger.getLogger(Search.class.getName());
	
	@Inject
	UserSession userSession;
	@Inject
	LoginControler loginControler;
	
	Country country;
	Region region;
	City city;
	Company company;
	Pump pump;

	@Inject
	CountryEJB countryEJB;
	@Inject
	RegionEJB regionEJB;
	@Inject
	CityEJB cityEJB;
	@Inject
	CompanyEJB companyEJB;
	@Inject
	PumpEJB pumpEJB;
	
	
	private List<Country> countries;
	private List<Region> regions;
	private List<City> cities;
	private List<Company> companies;
	private List<Pump> pumps;

	//////// FUNCTIONS \\\\\\\\
	
	private void loadCountries() {
		countries = countryEJB.findAll();
	}
	
	private void loadRegions() {
		regions = regionEJB.findByCountry(country);
	}
	private void loadAllRegions() {
		regions = regionEJB.findAll();
	}
	
	private void loadCities() {
		cities = cityEJB.findByRegion(region);
	}
	private void loadAllCities() {
		cities = cityEJB.findAll();
	}
	private void loadCitiesInCountry() {
		cities = cityEJB.findByCountry(country);
	}
	private void loadCitiesByCompany() {
		// TODO Auto-generated method stub
		cities = cityEJB.findByCompany(company);
	}
	
	private void loadCompaniesInCity() {
		companies = companyEJB.findByCity(city);
	}
	
	private void loadAllCompanies(){
		companies = companyEJB.findAll();
	}
	
	private void loadAllPumps(){
		pumps = pumpEJB.findAll();
	}

	private void loadPumpsByCompany() {
		pumps = pumpEJB.findByCompany(company);
	}

	private void loadPumpsInCountry() {
		// TODO Auto-generated method stub
		pumps = pumpEJB.findByCountry(country);
	}
	private void loadPumpsInCity() {
		// TODO Auto-generated method stub
		pumps = pumpEJB.findByCity(city);
	}
	private void loadPumpsByCityAndCompany() {
		// TODO Auto-generated method stub
		pumps = pumpEJB.findByCityAndCompany(company, city);
	}

	@PostConstruct
	@ViewScoped
	public void init() throws IOException {
		log.warning("----------------------------init: " + this);

		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext(); 
		try{
			if(!userSession.isLoggedIn())
				context.redirect("login.xhtml");
		}catch(Exception e){
		}
		
		loadCountries();
		loadAllRegions();
		loadAllCities();
		loadAllCompanies();
		loadAllPumps();
	}
	
	
	public void onCountryChange(){
		if(country != null){
			 loadRegions();
			 loadCitiesInCountry();
			 loadPumpsInCountry();
		}
	}
	public void onRegionChange(){
		log.warning("OnRegionChange");
		if(region != null)
			 loadCities();
	}
	
	public void onCityChange(){
		log.warning("OnCityChange");
		if(city != null){
			 loadCompaniesInCity();
			 if(company != null){
					loadPumpsByCityAndCompany();
				} else {
					 loadPumpsInCity();
				}
		}
	}
	

	public void onCompanyChange(){
		log.warning("OnCompanyChange");
		if(company != null){
			loadCitiesByCompany(); 
			if(city != null){
				loadPumpsByCityAndCompany();
			} else {
				loadPumpsByCompany();
			}
			 
		}
	}
	
	//////// GETTERS AND SETTERS \\\\\\\\
	
	

	public Country getCountry() {
		log.warning("--------------------------GetCountry");
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}
	
	public CountryEJB getCountryEJB() {
		return countryEJB;
	}

	public void setCountryEJB(CountryEJB countryEJB) {
		this.countryEJB = countryEJB;
	}
	
	public List<Country> getCountries() {
		return countries;
	}

	public void setCountries(List<Country> countries) {
		this.countries = countries;
	}

	public Region getRegion() {
		log.warning("--------------------------GetRegion");
		return region;
	}

	public void setRegion(Region region) {
		log.warning("Setting region" + region );
		this.region = region;
	}

	public RegionEJB getRegionEJB() {
		return regionEJB;
	}

	public void setRegionEJB(RegionEJB regionEJB) {
		this.regionEJB = regionEJB;
	}

	public List<Region> getRegions() {
		return regions;
	}

	public void setRegions(List<Region> regions) {
		this.regions = regions;
	}

	public City getCity() {
		log.warning("--------------------------GetCity");
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public CityEJB getCityEJB() {
		return cityEJB;
	}

	public void setCityEJB(CityEJB cityEJB) {
		this.cityEJB = cityEJB;
	}

	public List<City> getCities() {
		return cities;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
	}
	
	public Company getCompany() {
		log.warning("--------------------------GetCompany");
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public List<Company> getCompanies() {
		return companies;
	}

	public void setCompanies(List<Company> companies) {
		this.companies = companies;
	}
	
	public Pump getPump() {
		log.warning("--------------------------GetPump");
		return pump;
	}

	public void setPump(Pump pump) {
		this.pump = pump;
	}

	public List<Pump> getPumps() {
		return pumps;
	}

	public void setPumps(List<Pump> pumps) {
		this.pumps = pumps;
	}

}
