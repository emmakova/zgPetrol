package hr.ZGPetrol.ejb;

import hr.ZGPetrol.model.Change;
import hr.ZGPetrol.model.City;
import hr.ZGPetrol.model.Company;
import hr.ZGPetrol.model.Country;
import hr.ZGPetrol.model.Pump;
import hr.ZGPetrol.model.Tank;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class PumpEJB {

	@PersistenceContext
	private EntityManager em;
	
	public List<Pump> findAll(){
		TypedQuery<Pump> tq = em.createNamedQuery(Pump.FIND_ALL_PUMP, Pump.class);
		return tq.getResultList();
	}
	
	public List<Pump> findByCompany(Company company){
		TypedQuery<Pump> tq = em.createNamedQuery(Pump.FIND_PUMP_BY_COMPANY, Pump.class);
		tq.setParameter("companyId", company.getId());
		return tq.getResultList();
	}

	public List<Pump> findByCountry(Country country) {
		// TODO Auto-generated method stub
		TypedQuery<Pump> tq = em.createNamedQuery(Pump.FIND_PUMP_BY_COUNTRY, Pump.class);
		tq.setParameter("countryId", country.getId());
		return tq.getResultList();
	}
	
	public List<Pump> findByCity(City city) {
		TypedQuery<Pump> tq = em.createNamedQuery(Pump.FIND_PUMP_BY_CITY, Pump.class);
		tq.setParameter("cityId", city.getId());
		return tq.getResultList();
	}
	
	public Object findByPrimaryKey(long value) {
		// TODO Auto-generated method stub
		TypedQuery<Pump> tq = em.createNamedQuery(Pump.FIND_PUMP_BY_PK, Pump.class);
		tq.setParameter("valueId", value);
		return tq.getSingleResult();
	}

	public List<Pump> findByCityAndCompany(Company company, City city) {
		TypedQuery<Pump> tq = em.createNamedQuery(Pump.FIND_PUMP_BY_CITY_AND_COMPANY, Pump.class);
		tq.setParameter("cityId", city.getId());
		tq.setParameter("companyId", company.getId());
		return tq.getResultList();
	}

	public void insert(Pump pump) {
			em.persist(pump);
		}
	
	public void update(Pump pump){
		em.merge(pump);
	}
	public void delete(Pump pump){
		em.remove(em.getReference(Pump.class, pump.getId()));
	}
}
