package ro.test.entity;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "contacts")
public class Contact implements Comparable<Contact> {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Integer id;

	@Size(min = 4, max = 10, message = "Name must be between 4 and 10 characters")
	private String name;

	private String phoneNumber;

	private String email;

	public Contact() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Contact))
			return false;
		Contact contact = (Contact) o;
		return id == contact.id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return "Name: " + this.name + ", Email: " + this.email + ", Phone: " + this.phoneNumber;
	}

	@Override
	public int compareTo(Contact obj) {
		return this.name.compareTo(obj.name);
	}

}
