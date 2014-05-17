package models;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.db.ebean.Model;


@Entity
public class Contact extends Model {
	
	@Id
	public Long id;

	public String name;
	public String lastName;
	public String company;
	public String email;
	public String phoneNumber;
}
