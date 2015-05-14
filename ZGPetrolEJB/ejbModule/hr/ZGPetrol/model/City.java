package hr.ZGPetrol.model;

import java.io.Serializable;

import javax.faces.bean.SessionScoped;
import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the city database table.
 * 
 */
@Entity
@NamedQueries(
		{ 	@NamedQuery(name= City.FIND_ALL_CITIES, query="SELECT c FROM City c"),
			@NamedQuery(name= City.FIND_BY_REGION , query="SELECT c FROM City c where c.region.id =:regionId"),
			@NamedQuery(name= City.FIND_BY_COUNTRY , query="SELECT c FROM City c where c.country.id =:countryId"),
			@NamedQuery(name= City.FIND_CITY_BY_PK, query="SELECT c FROM City c where c.id = :valueId")
})
@NamedNativeQuery(name= City.FIND_BY_COMPANY , 
query="SELECT c.* FROM city c INNER JOIN cico cc ON c.id = cc.city_id INNER JOIN company co ON cc.company_id = co.id where co.id = ?",
 resultClass=City.class)
@Table(name="city")
@SessionScoped
public class City implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String FIND_ALL_CITIES = "findAllCities";
	public static final String FIND_BY_COUNTRY = "findCitiesByCountry";
	public static final String FIND_BY_REGION = "findCitiesByRegion";
	public static final String FIND_BY_COMPANY = "findCitiesByCompany";
	public static final String FIND_CITY_BY_PK = "findCityByPK";

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private String name;

	//bi-directional many-to-one association to Country
	@ManyToOne
	private Country country;
	
	//bi-directional many-to-one association to Region
	@ManyToOne
	private Region region;

	//bi-directional many-to-many association to Company
	@ManyToMany(mappedBy="cities")
	private List<Company> companies;

	//bi-directional many-to-one association to Pump
	@OneToMany(mappedBy="city")
	private List<Pump> pumps;
	
	public City() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Region getRegion() {
		return this.region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public List<Company> getCompanies() {
		return this.companies;
	}

	public void setCompanies(List<Company> companies) {
		this.companies = companies;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		City other = (City) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public List<Pump> getPumps() {
		return pumps;
	}

	public void setPumps(List<Pump> pumps) {
		this.pumps = pumps;
	}

}