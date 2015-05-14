package hr.ZGPetrol.model;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.SessionScoped;
import javax.persistence.*;


/**
 * The persistent class for the country database table.
 * 
 */
@Entity
@NamedQueries(
		{ 	
			@NamedQuery(name= Country.FIND_ALL, query="SELECT c FROM Country c"),
			@NamedQuery(name= Country.FIND_COUNTRY_BY_PK, query="SELECT c FROM Country c where c.id = :valueId")
		})
@Table(name="country")
@SessionScoped
public class Country implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String FIND_ALL = "findAll";
	public static final String FIND_COUNTRY_BY_PK = "findCountryByPK";

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private String name;

	//bi-directional many-to-one association to Region
	@OneToMany(mappedBy="country")
	private List<Region> regions;
	
	//bi-directional many-to-one association to City
	@OneToMany(mappedBy="country")
	private List<City> cities;
	
	public Country() {
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

	public List<Region> getRegions() {
		return regions;
	}

	public void setRegions(List<Region> regions) {
		this.regions = regions;
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
		Country other = (Country) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public List<City> getCities() {
		return cities;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
	}

}