package ro.test.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import ro.test.entity.Contact;

@Repository
public class ContactRepository {

	@PersistenceContext
	private EntityManager entityManager;

	public void addContact(Contact contact) {
		entityManager.persist(contact);
	}

	public List<Contact> findAll() {
		TypedQuery<Contact> query = entityManager.createQuery("SELECT c FROM Contact c", Contact.class);
		return query.getResultList();
	}

	public Optional<Contact> findByEmail(String email) {
		TypedQuery<Contact> query = entityManager.createQuery("SELECT c FROM Contact c WHERE c.email = :email",
				Contact.class);
		query.setParameter("email", email);
		return query.getResultList().stream().findFirst();
	}

	public void deleteContact(Contact contact) {
		contact = entityManager.merge(contact);
		entityManager.remove(contact);
	}

	public void updateContact(Contact newContact) {
		Contact contact = entityManager.find(Contact.class, newContact.getId());
		contact.setName(newContact.getName());
		contact.setPhoneNumber(newContact.getPhoneNumber());
		contact.setEmail(newContact.getEmail());
	}

	public Contact findContactById(int contactId) {
		return entityManager.find(Contact.class, contactId);
	}

}
