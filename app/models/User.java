package models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

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
	public String email;
	
	@Constraints.Required
	@MinLength(3)
	public String password;
	
	public boolean isAdmin = false;

	@Lob
	public byte[] image;
	
	public static User authenticate(String email, String password) {
		return finder.where().eq("email", email).eq("password", password).findUnique();
	}
	
	public User(String email, String password) {
		this.email = email;
		this.password = password;
	}
	
	
	public static Finder<Long, User> finder = new Finder<>(Long.class, User.class);
}
