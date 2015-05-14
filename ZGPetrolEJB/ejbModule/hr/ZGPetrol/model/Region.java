package hr.ZGPetrol.model;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.SessionScoped;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the region database table.
 * 
 */
@Entity
@NamedQueries(
		{ 	@NamedQuery(name= Region.FIND_ALL_REGION, query="SELECT r FROM Region r"),
			@NamedQuery(name= Region.FIND_BY_COUNTRY , query="SELECT r FROM Region r where r.country.id LIKE :countryId"),
			@NamedQuery(name= Region.FIND_REGION_BY_PK, query="SELECT r FROM Region r where r.id = :valueId")
})
@Table(name="region")
@SessionScoped
public class Region implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String FIND_ALL_REGION = "findAllRegion";
	public static final String FIND_BY_COUNTRY = "findByCountry";
	public static final String FIND_REGION_BY_PK = "findRegionByPK";

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private Country country;

	private String name;

	//bi-directional many-to-one association to City
	@OneToMany(mappedBy="region")
	private List<City> cities;

	public Region() {
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

	public List<City> getCities() {
		return this.cities;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
	}

	public City addCity(City city) {
		getCities().add(city);
		city.setRegion(this);

		return city;
	}

	public City removeCity(City city) {
		getCities().remove(city);
		city.setRegion(null);

		return city;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
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
		Region other = (Region) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}