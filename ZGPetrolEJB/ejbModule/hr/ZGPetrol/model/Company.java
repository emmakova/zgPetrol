package hr.ZGPetrol.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the company database table.
 * 
 */
@Entity
@NamedQueries(
		{ 	@NamedQuery(name= Company.FIND_ALL_COMPANY, query="SELECT c FROM Company c"),
			@NamedQuery(name= Company.FIND_COMPANY_BY_PK, query="SELECT c FROM Company c where c.id = :valueId")
})
@NamedNativeQuery(name= Company.FIND_BY_CITY , 
query="SELECT c.* FROM company c INNER JOIN cico cc ON c.id = cc.company_id INNER JOIN city ci ON cc.city_id = ci.id where ci.id = ?",
 resultClass=Company.class)
@Table(name="company")
public class Company implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String FIND_ALL_COMPANY = "findAllCompany";
	public static final String FIND_BY_CITY = "findByCity";
	public static final String FIND_COMPANY_BY_PK = "findCompanyByPK";
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private String name;

	//bi-directional many-to-many association to City
	@ManyToMany
	@JoinTable(
		name="cico"
		, joinColumns={
			@JoinColumn(name="company_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="city_id")
			}
		)
	private List<City> cities;

	//bi-directional many-to-one association to Pump
	@OneToMany(mappedBy="company")
	private List<Pump> pumps;

	public Company() {
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

	public List<Pump> getPumps() {
		return this.pumps;
	}

	public void setPumps(List<Pump> pumps) {
		this.pumps = pumps;
	}

	public Pump addPump(Pump pump) {
		getPumps().add(pump);
		pump.setCompany(this);

		return pump;
	}

	public Pump removePump(Pump pump) {
		getPumps().remove(pump);
		pump.setCompany(null);

		return pump;
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
		Company other = (Company) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}