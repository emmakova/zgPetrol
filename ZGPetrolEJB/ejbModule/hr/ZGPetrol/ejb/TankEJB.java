package hr.ZGPetrol.ejb;

import hr.ZGPetrol.model.Tank;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class TankEJB {
	@PersistenceContext
	private EntityManager em;
	
	public List<Tank> findAll(){
		TypedQuery<Tank> tq = em.createNamedQuery(Tank.FIND_TANK, Tank.class);
		return tq.getResultList();
	}
	
	public void insert(Tank t){
		em.persist(t);
	}
	public void update(Tank t){
		em.merge(t);
	}
	public void delete(Tank t){
		em.remove(em.getReference(Tank.class, t.getId()));
	}
}
