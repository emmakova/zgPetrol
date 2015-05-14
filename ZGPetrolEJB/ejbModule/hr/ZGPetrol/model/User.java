package hr.ZGPetrol.model;

import java.io.Serializable;

import javax.faces.bean.SessionScoped;
import javax.persistence.*;


/**
 * The persistent class for the users database table.
 * 
 */
@Entity
@Table(name="users")
@NamedQueries(
		{ 	
			@NamedQuery(name=User.FIND_ALL_USERS, query="SELECT u FROM User u WHERE u.isAdmin = false"),
			@NamedQuery(name=User.FIND_BY_USERNAME_AND_PASSWORD, query="SELECT u FROM User u where u.username = :username AND u.password = :password"),
			@NamedQuery(name= User.FIND_USER_BY_PK, query="SELECT u FROM User u where u.id = :valueId")
		})
@SessionScoped
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String FIND_ALL_USERS = "findAllUsers";
	public static final String FIND_BY_USERNAME_AND_PASSWORD= "findByUsernameAndPassword";
	public static final String FIND_USER_BY_PK = "findUserByPk";

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(name="first_name")
	private String firstName;

	@Column(name="is_admin")
	private boolean isAdmin;

	@Column(name="last_name")
	private String lastName;

	private String password;

	private String username;

	public User(){
		
	}
	
	public User(String firstName, String lastName, String username, String password, boolean isAdmin){
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.isAdmin = isAdmin;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public boolean getIsAdmin() {
		return this.isAdmin;
	}

	public void setIsAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
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
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}