package hr.ZGPetrol.ejb;

import hr.ZGPetrol.model.Separator;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class SeparatorEJB {

	@PersistenceContext
	private EntityManager em;
	
	public List<Separator> findAll(){
		TypedQuery<Separator> tq = em.createNamedQuery(Separator.FIND_SEPARATOR, Separator.class);
		return tq.getResultList();
	}
	
	public void insert(Separator s){
		em.persist(s);
	}
	public void update(Separator s){
		em.merge(s);
	}
	public void delete(Separator s){
		em.remove(em.getReference(Separator.class, s.getId()));
	}
}
