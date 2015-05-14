package hr.ZGPetrol.ejb;

import hr.ZGPetrol.model.City;
import hr.ZGPetrol.model.Company;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

@Stateless
public class CompanyEJB {

	@PersistenceContext
	private EntityManager em;
	
	public List<Company> findAll(){
		TypedQuery<Company> tq = em.createNamedQuery(Company.FIND_ALL_COMPANY, Company.class);
		return tq.getResultList();
	}
	
	public List<Company> findByCity(City city){
		Query tq = em.createNamedQuery(Company.FIND_BY_CITY, Company.class);
		tq.setParameter(1, city.getId());
		return tq.getResultList();
	}

	public Object findByPrimaryKey(long value) {
		TypedQuery<Company> tq = em.createNamedQuery(Company.FIND_COMPANY_BY_PK, Company.class);
		tq.setParameter("valueId", value);
		return tq.getSingleResult();
	}

}
