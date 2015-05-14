package hr.ZGPetrol.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the pump database table.
 * 
 */
@Entity
@NamedQueries(
		{ 	@NamedQuery(name= Pump.FIND_ALL_PUMP, query="SELECT p FROM Pump p"),
			@NamedQuery(name= Pump.FIND_PUMP_BY_COMPANY , query="SELECT p FROM Pump p where p.company.id LIKE :companyId"),
			@NamedQuery(name= Pump.FIND_PUMP_BY_COUNTRY , query="SELECT p FROM Pump p where p.country.id LIKE :countryId"),
			@NamedQuery(name= Pump.FIND_PUMP_BY_CITY , query="SELECT p FROM Pump p where p.city.id LIKE :cityId"),
			@NamedQuery(name= Pump.FIND_PUMP_BY_CITY_AND_COMPANY , query="SELECT p FROM Pump p where p.city.id LIKE :cityId AND p.company.id LIKE :companyId"),
			@NamedQuery(name= Pump.FIND_PUMP_BY_PK, query="SELECT p FROM Pump p where p.id = :valueId")
})
@Table(name="pump")
public class Pump implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String FIND_ALL_PUMP = "findAllPump";
	public static final String FIND_PUMP_BY_COMPANY = "findPumpByCompany";
	public static final String FIND_PUMP_BY_PK = "findPumpByPK";
	public static final String FIND_PUMP_BY_COUNTRY = "findPumpByCountry";
	public static final String FIND_PUMP_BY_CITY = "findPumpByCity";
	public static final String FIND_PUMP_BY_CITY_AND_COMPANY = "findPumpByCityAndCompany";

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	
	private String name;
	private String address;
	private double latitude;
	private double longitude;
	private String contact;
	
	@Column(name="working_time")
	private String workingTime;

	//bi-directional many-to-one association to Change
	@OneToMany(mappedBy="pump", fetch=FetchType.EAGER)
	private List<Change> changes;

	//bi-directional many-to-one association to Company
	@ManyToOne
	private Company company;
	
	//bi-directional many-to-one association to City
	@ManyToOne
	private City city;
	
	//bi-directional many-to-one association to City
	@ManyToOne
	private Country country;

	//bi-directional many-to-one association to Separator
	@OneToMany(mappedBy="pump", fetch = FetchType.EAGER)
	private List<Separator> separators;

	//bi-directional many-to-one association to Tank
	@OneToMany(mappedBy="pump", fetch = FetchType.EAGER)
	private List<Tank> tanks;

	public Pump() {
	}
	
	public Pump(String name, String address, double lat, double lng, String contact, String wt, Country country, City city, Company company) {
		this.name = name;
		this.address = address;
		this.latitude = lat;
		this.longitude = lng;
		this.contact = contact;
		this.workingTime = wt;
		this.country = country;
		this.city = city;
		this.company = company;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContact() {
		return this.contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWorkingTime() {
		return this.workingTime;
	}

	public void setWorkingTime(String workingTime) {
		this.workingTime = workingTime;
	}

	public List<Change> getChanges() {
		return this.changes;
	}

	public void setChanges(List<Change> changes) {
		this.changes = changes;
	}

	public Change addChange(Change change) {
		getChanges().add(change);
		change.setPump(this);

		return change;
	}

	public Change removeChange(Change change) {
		getChanges().remove(change);
		change.setPump(null);

		return change;
	}

	public Company getCompany() {
		return this.company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
	
	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public List<Separator> getSeparators() {
		return this.separators;
	}

	public void setSeparators(List<Separator> separators) {
		this.separators = separators;
	}

	public Separator addSeparator(Separator separator) {
		getSeparators().add(separator);
		separator.setPump(this);

		return separator;
	}

	public Separator removeSeparator(Separator separator) {
		getSeparators().remove(separator);
		separator.setPump(null);

		return separator;
	}

	public List<Tank> getTanks() {
		return this.tanks;
	}

	public void setTanks(List<Tank> tanks) {
		this.tanks = tanks;
	}

	public Tank addTank(Tank tank) {
		getTanks().add(tank);
		tank.setPump(this);

		return tank;
	}

	public Tank removeTank(Tank tank) {
		getTanks().remove(tank);
		tank.setPump(null);

		return tank;
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
		Pump other = (Pump) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}