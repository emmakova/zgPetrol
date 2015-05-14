package hr.ZGPetrol.controler;

import hr.ZGPetrol.ejb.ChangeEJB;
import hr.ZGPetrol.model.Change;
import hr.ZGPetrol.model.Pump;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ManagedBean
@ViewScoped
public class ChangeControler implements Serializable{
	public static Logger log = Logger.getLogger(ChangeControler.class.getName());
	
	private static final long serialVersionUID = 1L;
	
	Change change;
	
	private String description;
	private String action;
	private Date date;
	private String dateString;
	private String user;
	Pump pump;
	
	Change selectedChange;
	List<Change> changes;
	
	@Inject
	ChangeEJB changeEJB;
	@Inject
	UserSession userSession;
	DateFormat dateFormat;
	
	
	@PostConstruct
	public void init(){
		user = userSession.getUser().getFirstName() + " " + userSession.getUser().getLastName() + " (" + userSession.getUser().getUsername() + ")";
		dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		date = new Date();
		dateString = dateFormat.format(date);
		changes = userSession.getPump().getChanges();
		if(userSession.getChange() != null){
			description = userSession.getChange().getDescription();
			action = "edit";
		} else {
			action = "add";
		}

		log.warning(description);
		
	}
	

	public String addChange(){
		action = "add";
		userSession.setChange(null);
		return "editChanges.xhtml?faces-redirect=true";
	}
	public String editChange(Change c){
		userSession.setChange(c);
		log.warning(c.getDescription());
		return "editChanges.xhtml?faces-redirect=true";
	}
	
	
	
	public void removeChange(Change c){
		changeEJB.delete(c);
		changes.remove(c);
	}
	
	public void saveChange(){
		FacesContext context = FacesContext.getCurrentInstance();
		if(description == null || description.equals("")){
			context.getExternalContext().getFlash().setKeepMessages(true);
			context.addMessage(null, new FacesMessage("Warning", "Promjena nemoze biti prazna!!!"));
		} else {
		switch (action) {
			case "add":
				pump = userSession.getPump();
				change = new Change(dateString, user, description, pump);
				try{
					pump.addChange(change);
					changeEJB.insert(change);
					context.getExternalContext().getFlash().setKeepMessages(true);
					context.addMessage(null, new FacesMessage("Successful", "Nova promjena uspjesno dodana!!!"));
					FacesContext.getCurrentInstance().getExternalContext().redirect("pump.xhtml");
				}catch(Exception e){
					context.getExternalContext().getFlash().setKeepMessages(true);
					context.addMessage(null, new FacesMessage("Error", "Doslo je do greske pri dodavanju promjena!!!"));
				}
				break;
				
			case "edit":
				change = userSession.getChange();
				change.setDescription(description);
				try{
					changeEJB.update(change);
					context.getExternalContext().getFlash().setKeepMessages(true);
					context.addMessage(null, new FacesMessage("Successful", "Promjena uspjesno izmijenjena!!!"));
					FacesContext.getCurrentInstance().getExternalContext().redirect("pump.xhtml");
				}catch(Exception e){
					userSession.setSeparator(null);
					context.getExternalContext().getFlash().setKeepMessages(true);
					context.addMessage(null, new FacesMessage("Error", "Doslo je do greske pri izmijenjivanju promijena!!!"));
				}
				break;
			
			default:
				break;
			}
		}
	}
	
	
	public void removeItem(Change c){
		changeEJB.delete(c);
		changes.remove(c);
	}
	public void editItem(){
		change.setDescription(action);
		changeEJB.update(change);
	}
	
	public String editDescription(Change c){
		change = c;
		return "editChanges.xhtml?faces-redirect=true";
	}
	
	public Change getChange() {
		return change;
	}

	public void setChange(Change change) {
		this.change = change;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public UserSession getUserSession() {
		return userSession;
	}

	public void setUserSession(UserSession userSession) {
		this.userSession = userSession;
	}

	public String getDateString() {
		return dateString;
	}

	public void setDateString(String dateString) {
		this.dateString = dateString;
	}

	public Pump getPump() {
		return pump;
	}

	public void setPump(Pump pump) {
		this.pump = pump;
	}

	public ChangeEJB getChangeEJB() {
		return changeEJB;
	}

	public void setChangeEJB(ChangeEJB changeEJB) {
		this.changeEJB = changeEJB;
	}

	public DateFormat getDateFormat() {
		return dateFormat;
	}

	public void setDateFormat(DateFormat dateFormat) {
		this.dateFormat = dateFormat;
	}

	public Change getSelectedChange() {
		return selectedChange;
	}

	public void setSelectedChange(Change selectedChange) {
		this.selectedChange = selectedChange;
	}

	public List<Change> getChanges() {
		return changes;
	}

	public void setChanges(List<Change> changes) {
		this.changes = changes;
	}

	public String getDescription2() {
		return action;
	}

	public void setDescription2(String description2) {
		this.action = description2;
	}

}
