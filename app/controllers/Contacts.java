package controllers;

import java.util.List;

import com.avaje.ebean.Ebean;

import models.Contact;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.contacts.list;
import views.html.contacts.details;
import play.mvc.Security;

@Security.Authenticated(Secured.class)
public class Contacts extends Controller {

	private static final Form<Contact> contactForm = Form.form(Contact.class);
	
    public static Result showAll() {
    	List<Contact> contacts = Contact.findAll();
    	return ok(list.render(contacts));
    }
    
    public static Result newContact() {
    	return ok(details.render(contactForm));
    }
    
    public static Result details(Long id) {
    	Contact contact = Contact.findById(id);
    	Form<Contact> filledForm = contactForm.fill(contact);
    	return ok(details.render(filledForm));
    }
    
    public static Result delete(Long id) {
    	Contact contactToDelete = Contact.findById(id);
    	Ebean.delete(contactToDelete);
    	return redirect(routes.Contacts.showAll());
    }
    
    public static Result save() {
    	Form<Contact> boundForm = contactForm.bindFromRequest();
    	if (boundForm.hasErrors()) {
    		flash("error", "Nieprawidłowe dane. Proszę poprawić formularz");
    		return badRequest(details.render(boundForm));
    	}
    	Contact contact = boundForm.get();
    	if (contact.id == null) {
    		Ebean.save(contact);
        	flash("success", "Pomyślnie dodano nowy kontakt");
    	}
    	else {
    		Ebean.update(contact);
        	flash("success", "Pomyślnie edytowano kontakt");
    	}
    	
    	return redirect(routes.Contacts.showAll());
    }
}
