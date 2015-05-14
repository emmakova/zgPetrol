package hr.ZGPetrol.controler;

import hr.ZGPetrol.ejb.UserEJB;
import hr.ZGPetrol.model.User;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class LoginControler implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	User user;
	
	String username;
	String password;
	
	@Inject
	UserEJB userEJB;
	@Inject
	UserSession userSession;
	
	@PostConstruct
	public void checkUser() throws IOException{
		if(userSession.getUser() != null){
			ExternalContext context = FacesContext.getCurrentInstance().getExternalContext(); 
			context.redirect("search.xhtml");
		}
	}
	
	public String loginControl(){
		FacesContext context = FacesContext.getCurrentInstance();
		try{
			user = userEJB.findByUsernameAndPassword(username, password);
			if(user != null){
				context.getExternalContext().getFlash().setKeepMessages(true);
		        context.addMessage(null, new FacesMessage("Successful",  "Prijavili ste se kao: " + user.getFirstName() +" "+ user.getLastName()));
		        userSession.setUser(user);
				return "search.xhtml?faces-redirect=true";
			}
			else{
				//context.getExternalContext().getFlash().setKeepMessages(true);
		        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,  "Error", "Nevazeci username ili password!!!"));
				return "";
			}
		}catch(Exception e){
			//context.getExternalContext().getFlash().setKeepMessages(true);
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,  "Error", "Nevazeci username ili password!!!"));
			return "";
		}
	}

	public boolean isLoggedIn() {
        return user != null;
    }
	
	public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "login?faces-redirect=true";
    }
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserEJB getUserEJB() {
		return userEJB;
	}

	public void setUserEJB(UserEJB userEJB) {
		this.userEJB = userEJB;
	}

	
}