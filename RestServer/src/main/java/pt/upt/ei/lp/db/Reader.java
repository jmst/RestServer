package pt.upt.ei.lp.db;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.google.gson.annotations.Expose;


@Entity
public class Reader {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Expose
	private int id;
	@Expose
	private String phone;
	@Expose
	private String name;

	@Expose
	@OneToMany
	private List<Book> books = new ArrayList<Book>();

	public Reader() {
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Book> getBooks() {
		return books;
	}
	
	public String toString() {
		String st = "Reader id=" + id + "  phone=" + phone + "  name=" + name
				+ "\n";
		for (Book t : books) {
			st += "  " + t + "\n";
		}
		return st;
	}

}
