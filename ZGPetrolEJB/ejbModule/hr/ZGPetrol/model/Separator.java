package hr.ZGPetrol.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the separator database table.
 * 
 */
@Entity
@NamedQueries(
		{ 	@NamedQuery(name=Separator.FIND_SEPARATOR, query="SELECT s FROM Separator s")
})
@Table(name="separators")
public class Separator implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String FIND_SEPARATOR = "finSeparator";

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private double kanalica;

	private int okna;

	private int slivnici;


	//bi-directional many-to-one association to Pump
	@ManyToOne
	private Pump pump;

	public Separator() {
	}
	
	public Separator(double kanalica, int okna, int slivnici, Pump pump) {
		this.kanalica = kanalica;
		this.okna = okna;
		this.slivnici = slivnici;
		this.pump = pump;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getKanalica() {
		return this.kanalica;
	}

	public void setKanalica(double kanalica) {
		this.kanalica = kanalica;
	}

	public int getOkna() {
		return this.okna;
	}

	public void setOkna(int okna) {
		this.okna = okna;
	}

	public int getSlivnici() {
		return this.slivnici;
	}

	public void setSlivnici(int slivnici) {
		this.slivnici = slivnici;
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
		Separator other = (Separator) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}