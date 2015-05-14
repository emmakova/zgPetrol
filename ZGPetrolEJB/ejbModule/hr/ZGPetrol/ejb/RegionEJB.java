package hr.ZGPetrol.ejb;

import hr.ZGPetrol.model.Country;
import hr.ZGPetrol.model.Region;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class RegionEJB {

	@PersistenceContext
	private EntityManager em;
	
	public List<Region> findAll(){
		TypedQuery<Region> tq = em.createNamedQuery(Region.FIND_ALL_REGION, Region.class);
		return tq.getResultList();
	}
	
	public List<Region> findByCountry(Country country){
		TypedQuery<Region> tq = em.createNamedQuery(Region.FIND_BY_COUNTRY, Region.class);
		tq.setParameter("countryId", country.getId());
		return tq.getResultList();
	}

	public Object findByPrimaryKey(long value) {
		// TODO Auto-generated method stub
		TypedQuery<Region> tq = em.createNamedQuery(Region.FIND_REGION_BY_PK, Region.class);
		tq.setParameter("valueId", value);
		return tq.getSingleResult();
	}

}
