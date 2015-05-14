package hr.ZGPetrol.ejb;

import hr.ZGPetrol.model.Change;
import hr.ZGPetrol.model.Pump;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class ChangeEJB {

	@PersistenceContext
	private EntityManager em;
	
	public List<Change> findAll(){
		TypedQuery<Change> tq = em.createNamedQuery(Change.FIND_ALL_CHANGES, Change.class);
		return tq.getResultList();
	}
	
	public List<Change> findByPump(Pump pump){
		TypedQuery<Change> tq = em.createNamedQuery(Change.FIND_BY_PUMP, Change.class);
		tq.setParameter("pumpId", pump.getId());
		return tq.getResultList();
	}
	
	public void insert(Change c){
		em.persist(c);
	}
	public void update(Change c){
		em.merge(c);
	}
	public void delete(Change c){
		em.remove(em.getReference(Change.class, c.getId()));
	}

	public Object findByPrimaryKey(long value) {
		TypedQuery<Change> tq = em.createNamedQuery(Change.FIND_CHANGE_BY_PK, Change.class);
		tq.setParameter("valueId", value);
		return tq.getSingleResult();
	}
}
