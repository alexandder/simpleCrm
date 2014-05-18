package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Constraint;

import play.data.validation.Constraints;
import play.db.ebean.Model;


@Entity
public class Contact extends Model {
	
	@Id
	public Long id;
	@Constraints.Required
	public String name;
	@Constraints.Required
	public String lastName;
	public String company;
	public String email;
	public String phoneNumber;
	
	@ManyToOne
	public User user;
	
	public static Finder<Long, Contact> find = new Finder<>(Long.class, Contact.class);
	
	public static List<Contact> findAll() {
		return find.all();
	}
	
	public static Contact findById(Long id) {
		return find.byId(id);
	}
}
