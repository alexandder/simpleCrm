package models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.UniqueConstraint;

import play.data.validation.Constraints.Email;
import play.data.validation.Constraints;
import play.data.validation.Constraints.MinLength;
import play.db.ebean.Model;

@Entity
public class User extends Model{

	@Id
	public Long id;
	
	@Email
	@Constraints.Required
	@Column(unique=true)
	public String email;
	
	@Constraints.Required
	@MinLength(3)
	public String password;
	
	public boolean isAdmin = false;

	
	public String image;
	
	@OneToMany(mappedBy="user")
	public List<Contact> contacts;
	
	public static User authenticate(String email, String password) {
		return finder.where().eq("email", email).eq("password", password).findUnique();
	}
	
	public User(String email, String password) {
		this.email = email;
		this.password = password;
	}
	
	
	public static Finder<Long, User> finder = new Finder<>(Long.class, User.class);
	
	public static List<User> findAll() {
		return finder.all();
	}
	
	public static User findByEmail(String email) {
		return finder.where().eq("email", email).findUnique();
	}
}
