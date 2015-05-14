package hr.ZGPetrol.ejb;

import hr.ZGPetrol.model.Country;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class CountryEJB {

	@PersistenceContext
	private EntityManager em;
	
	public List<Country> findAll(){
		TypedQuery<Country> tq = em.createNamedQuery(Country.FIND_ALL, Country.class);
		return tq.getResultList();
	}

	public Object findByPrimaryKey(long value) {
		// TODO Auto-generated method stub
		TypedQuery<Country> tq = em.createNamedQuery(Country.FIND_COUNTRY_BY_PK, Country.class);
		tq.setParameter("valueId", value);
		return tq.getSingleResult();
	}
	
	
}
