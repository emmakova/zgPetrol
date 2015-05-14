package hr.ZGPetrol.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the changes database table.
 * 
 */
@Entity
@Table(name="changes")
@NamedQueries(
		{ 	@NamedQuery(name=Change.FIND_ALL_CHANGES, query="SELECT c FROM Change c"),
			@NamedQuery(name= Change.FIND_BY_PUMP , query="SELECT c FROM Change c where c.pump.id LIKE :pumpId"),
			@NamedQuery(name= Change.FIND_CHANGE_BY_PK, query="SELECT c FROM Change c where c.id = :valueId")
		})

public class Change implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String FIND_ALL_CHANGES = "findAllChanges";
	public static final String FIND_BY_PUMP = "findChangesByPump";
	public static final String FIND_CHANGE_BY_PK = "findChangeByPK";

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private String date;

	private String description;
	
	private String user;

	//bi-directional many-to-one association to Pump
	@ManyToOne
	private Pump pump;

	public Change() {
	}
	
	public Change(String dateString, String user, String description, Pump pump) {
		this.date = dateString;
		this.user = user;
		this.description = description;
		this.pump = pump;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDate() {
		return this.date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
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
		Change other = (Change) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

}