package hr.ZGPetrol.controler;

import hr.ZGPetrol.ejb.TankEJB;
import hr.ZGPetrol.model.Pump;
import hr.ZGPetrol.model.Tank;

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
public class TankControler {
	public static Logger log = Logger.getLogger(TankControler.class.getName());

	@Inject
	TankEJB tankEJB;
	@Inject
	UserSession userSession;
	
	Tank tank;
	List<Tank> tanks;
	
	Pump pump;
	double volumen;
	int pad; 
	String gorivo;
	int br_kosara;
	
	String action;
	
	@PostConstruct
	public void init(){
		log.warning("-------------------init tank controller");
		pump = userSession.getPump();
		tanks = pump.getTanks();
		if(userSession.getTank() != null){
			tank = userSession.getTank();
			volumen = tank.getVolumen();
			pad = tank.getPad();
			gorivo = tank.getGorivo();
			br_kosara = tank.getBrKosara();
			action = "edit";
		} else {
			action = "add";
		}
	}
	
	public void insertTank(){
		FacesContext context = FacesContext.getCurrentInstance();
		switch (action) {
		case "add":
			tank = new Tank(volumen, pad, gorivo, br_kosara, pump);
			tanks.add(tank);
			try{
				tankEJB.insert(tank);
				context.getExternalContext().getFlash().setKeepMessages(true);
				context.addMessage(null, new FacesMessage("Successful", "Novi spremnik uspjesno dodan!!!"));
				FacesContext.getCurrentInstance().getExternalContext().redirect("pump.xhtml");
			}catch(Exception e){
				context.getExternalContext().getFlash().setKeepMessages(true);
				context.addMessage(null, new FacesMessage("Error", "Doslo je do greske pri dodavanju spremnika!!!"));
			}
			break;
		case "edit":
			tank.setBrKosara(br_kosara);
			tank.setGorivo(gorivo);
			tank.setPad(pad);
			tank.setVolumen(volumen);
			try{
				tankEJB.update(tank);
				userSession.setTank(null);
				context.getExternalContext().getFlash().setKeepMessages(true);
				context.addMessage(null, new FacesMessage("Successful", "Spremnik uspjesno izmijenjen!!!"));
				FacesContext.getCurrentInstance().getExternalContext().redirect("pump.xhtml");
			}catch(Exception e){
				userSession.setTank(null);
				context.getExternalContext().getFlash().setKeepMessages(true);
				context.addMessage(null, new FacesMessage("Error", "Doslo je do greske pri izmijenjivanju spremnika!!!"));
			}
			break;
		default:
			break;
		}
	}
	
	public String addTank(){
		action = "add";
		userSession.setTank(null);
		return "addTank.xhtml?faces-redirect=true";
	}
	
	public String editTank(Tank t){
		userSession.setTank(t);
		return "addTank.xhtml?faces-redirect=true";
	}

	public void removeTank(Tank t){
		tankEJB.delete(t);
		tanks.remove(t);
	}
	
	public TankEJB getTankEJB() {
		return tankEJB;
	}

	public void setTankEJB(TankEJB tankEJB) {
		this.tankEJB = tankEJB;
	}

	public UserSession getUserSession() {
		return userSession;
	}

	public void setUserSession(UserSession userSession) {
		this.userSession = userSession;
	}

	public Tank getTank() {
		return tank;
	}

	public void setTank(Tank tank) {
		this.tank = tank;
	}

	public Pump getPump() {
		return pump;
	}

	public void setPump(Pump pump) {
		this.pump = pump;
	}

	public double getVolumen() {
		return volumen;
	}

	public void setVolumen(double volumen) {
		this.volumen = volumen;
	}

	public int getPad() {
		return pad;
	}

	public void setPad(int pad) {
		this.pad = pad;
	}

	public String getGorivo() {
		return gorivo;
	}

	public void setGorivo(String gorivo) {
		this.gorivo = gorivo;
	}

	public int getBr_kosara() {
		return br_kosara;
	}

	public void setBr_kosara(int br_kosara) {
		this.br_kosara = br_kosara;
	}

	public List<Tank> getTanks() {
		return tanks;
	}

	public void setTanks(List<Tank> tanks) {
		this.tanks = tanks;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	
}
