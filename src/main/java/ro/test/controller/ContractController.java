package ro.test.controller;

import java.util.Collections;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ro.test.entity.Contact;
import ro.test.service.ContactService;

@Controller
public class ContractController {

	@Autowired
	private ContactService contactService;

	@RequestMapping("/")
	public String showMyPhoneBookPage(Model model) {
		List<Contact> allContacts = contactService.findAll();
		Collections.sort(allContacts);
		model.addAttribute("list", allContacts);
		return "phoneBook";
	}

	@RequestMapping("/addContact")
	public String showAddContactPage() {
		return "addContact";
	}

	@RequestMapping(value = "/process-addContact", method = RequestMethod.POST)
	public String addContact(@Valid @ModelAttribute("contact") Contact contact, BindingResult bindingResult,
			Model model) {
		if (bindingResult.hasErrors()) {
			List<ObjectError> errorList = bindingResult.getAllErrors();
			model.addAttribute("message", errorList.get(0).getDefaultMessage());
			return "addContact";
		}
		try {
			contactService.addContact(contact);
			return "redirect:/";
		} catch (IllegalArgumentException e) {
			model.addAttribute("message", "Contact already exist.");
		}
		return "addContact";
	}

	@RequestMapping(value = "/deleteContact/{idContact}", method = RequestMethod.GET)
	public String deleteContact(@PathVariable("idContact") int contactId, Model model) {
		Contact contact = contactService.findContactById(contactId);
		contactService.deleteContact(contact);
		return "redirect:/";
	}

	@RequestMapping(value = "/editContact/{idContact}")
	public String showEditContactPage(@PathVariable("idContact") int contactId, Model model) {
		Contact contact = contactService.findContactById(contactId);
		model.addAttribute("c", contact);
		return "editContact";
	}

	@RequestMapping(value = "/process-editContact/{idContact}", method = RequestMethod.POST)
	public String editContact(@PathVariable("idContact") int contactId, @ModelAttribute("contact") Contact contact,
			Model model) {
		contact.setId(contactId);
		contactService.updateContact(contact);
		return "redirect:/";
	}

}
