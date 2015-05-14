package hr.ZGPetrol.ejb;

import hr.ZGPetrol.model.User;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class UserEJB {


	@PersistenceContext
	private EntityManager em;
	
	public List<User> findAll(){
		TypedQuery<User> tq = em.createNamedQuery(User.FIND_ALL_USERS, User.class);
		return tq.getResultList();
	}
	
	public User findByUsernameAndPassword(String username, String password){
		try{
		TypedQuery<User> tq = em.createNamedQuery(User.FIND_BY_USERNAME_AND_PASSWORD, User.class);
		tq.setParameter("username", username);
		tq.setParameter("password", password);
		return tq.getSingleResult();
		} catch(Exception e){
//			FacesContext context = FacesContext.getCurrentInstance();
//			context.getExternalContext().getFlash().setKeepMessages(true);
//	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,  "Error", "Nevazeci username ili password!!!"));
			
			return null;
		}
	}
	
	public void insert(User u){
		em.persist(u);
	}
	public void update(User u){
		em.merge(u);
	}
	public void delete(User u){
		em.remove(em.getReference(User.class, u.getId()));
	}

	public Object findByPrimaryKey(long value) {
		TypedQuery<User> tq = em.createNamedQuery(User.FIND_USER_BY_PK, User.class);
		tq.setParameter("valueId", value);
		return tq.getSingleResult();
	}
}
