package hr.ZGPetrol.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the tank database table.
 * 
 */
@Entity
@NamedQuery(name= Tank.FIND_TANK, query="SELECT t FROM Tank t")
@Table(name="tank")
public class Tank implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String FIND_TANK = "findTank";

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(name="br_kosara")
	private int brKosara;

	private String gorivo;

	private int pad;

	private double volumen;

	//bi-directional many-to-one association to Pump
	@ManyToOne
	private Pump pump;

	public Tank() {
	}

	public Tank(double volumen, int pad, String gorivo, int brKosara, Pump pump) {
		this.volumen = volumen;
		this.pad = pad;
		this.gorivo = gorivo;
		this.brKosara = brKosara;
		this.pump = pump;
	}
	
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getBrKosara() {
		return this.brKosara;
	}

	public void setBrKosara(int brKosara) {
		this.brKosara = brKosara;
	}

	public String getGorivo() {
		return this.gorivo;
	}

	public void setGorivo(String gorivo) {
		this.gorivo = gorivo;
	}

	public int getPad() {
		return this.pad;
	}

	public void setPad(int pad) {
		this.pad = pad;
	}

	public double getVolumen() {
		return this.volumen;
	}

	public void setVolumen(double volumen) {
		this.volumen = volumen;
	}

	public Pump getPump() {
		return this.pump;
	}

	public void setPump(Pump pump) {
		this.pump = pump;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tank other = (Tank) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}