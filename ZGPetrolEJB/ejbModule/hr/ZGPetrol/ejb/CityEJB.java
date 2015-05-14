package hr.ZGPetrol.ejb;

import hr.ZGPetrol.model.City;
import hr.ZGPetrol.model.Company;
import hr.ZGPetrol.model.Country;
import hr.ZGPetrol.model.Region;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

@Stateless
public class CityEJB {

	@PersistenceContext
	private EntityManager em;
	
	public List<City> findAll(){
		TypedQuery<City> tq = em.createNamedQuery(City.FIND_ALL_CITIES, City.class);
		return tq.getResultList();
	}
	
	public List<City> findByCountry(Country country){
		TypedQuery<City> tq = em.createNamedQuery(City.FIND_BY_COUNTRY, City.class);
		tq.setParameter("countryId", country.getId());
		return tq.getResultList();
	}
	
	public List<City> findByCompany(Company company){
		Query tq = em.createNamedQuery(City.FIND_BY_COMPANY, City.class);
		tq.setParameter(1, company.getId());
		return tq.getResultList();
	}
	
	public List<City> findByRegion(Region region){
		TypedQuery<City> tq = em.createNamedQuery(City.FIND_BY_REGION, City.class);
		tq.setParameter("regionId", region.getId());
		return tq.getResultList();
	}
	
	public Object findByPrimaryKey(long value) {
		// TODO Auto-generated method stub
		TypedQuery<City> tq = em.createNamedQuery(City.FIND_CITY_BY_PK, City.class);
		tq.setParameter("valueId", value);
		return tq.getSingleResult();
	}

}