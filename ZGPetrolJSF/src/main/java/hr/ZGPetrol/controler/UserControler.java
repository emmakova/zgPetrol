package hr.ZGPetrol.controler;

import hr.ZGPetrol.ejb.UserEJB;
import hr.ZGPetrol.model.Country;
import hr.ZGPetrol.model.User;

import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class UserControler {

	public static Logger log = Logger.getLogger(UserControler.class.getName());
	
	@Inject
	UserEJB userEJB;
	@Inject
	UserSession userSession;

	User user;
	private List<User> users;

	private String firstName;
	private boolean isAdmin;
	private String lastName;
	private String password;
	private String username;
	
	@PostConstruct
	public void init(){
		users = userEJB.findAll();
	}
	
	public void insertUser(){
		user = new User(firstName, lastName, username, password, isAdmin);
		log.warning("" + user.getUsername());
		FacesContext context = FacesContext.getCurrentInstance();
		try{
			userEJB.insert(user);
			context.getExternalContext().getFlash().setKeepMessages(true);
			context.addMessage(null, new FacesMessage("Successful", "Novi korisnik uspjesno dodan!!!"));
			FacesContext.getCurrentInstance().getExternalContext().redirect("search.xhtml");
		}catch(Exception e){
			context.getExternalContext().getFlash().setKeepMessages(true);
			context.addMessage(null, new FacesMessage("Error", "Doslo je do greske pri dodavanju korisnika!!!"));
		}
	}
	
	public void editUser(){
		user = userSession.getUser();
		 FacesContext context = FacesContext.getCurrentInstance();
		try{
			userEJB.update(user);
			context.addMessage(null, new FacesMessage("Successful",  "Izmjene uspjesno spremljene"));
		}catch(Exception e){
			context.addMessage(null, new FacesMessage("Error",  "Doslo je do problema pri spremanju izmjena"));
		}
	}
	
	public void removeUser(){
		FacesContext context = FacesContext.getCurrentInstance();
		try{
			userEJB.delete(user);
			users.remove(user);
			context.addMessage(null, new FacesMessage("Successful",  "Korisnik uspjesno obrisan"));
		}catch(Exception e){
			context.addMessage(null, new FacesMessage("Error",  "Doslo je do problema pri brisanju korisnika"));
		}
	}
	
	
	public UserEJB getUserEJB() {
		return userEJB;
	}
	public void setUserEJB(UserEJB userEJB) {
		this.userEJB = userEJB;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public boolean isAdmin() {
		return isAdmin;
	}
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
}
