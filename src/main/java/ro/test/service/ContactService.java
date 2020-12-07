package ro.test.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ro.test.entity.Contact;
import ro.test.repository.ContactRepository;

@Service
public class ContactService {

	@Autowired
	private ContactRepository contactRepository;

	@Transactional(propagation = Propagation.REQUIRED)
	public void addContact(Contact contact) {
		Optional<Contact> optionalContact = contactRepository.findByEmail(contact.getEmail());
		if (optionalContact.isEmpty()) {
			contactRepository.addContact(contact);
		} else {
			throw new IllegalArgumentException();
		}
	}

	public List<Contact> findAll() {
		return contactRepository.findAll();
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteContact(Contact contact) {
		contactRepository.deleteContact(contact);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void updateContact(Contact newContact) {
		contactRepository.updateContact(newContact);
	}

	public Contact findContactById(int contactId) {
		return contactRepository.findContactById(contactId);
	}
}
