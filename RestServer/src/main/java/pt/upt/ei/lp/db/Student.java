package pt.upt.ei.lp.db;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.google.gson.annotations.Expose;


@Entity
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Expose
	private int id;
	@Expose
	private int number;
	@Expose
	private String name;

	@ManyToMany
//	@JoinTable(name = "StudentClass", joinColumns = { @JoinColumn(name = "idStudent", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "idClass", referencedColumnName = "id") })
	private List<Class> classes = new ArrayList<Class>();

	public Student() {
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Class> getClasses() {
		return classes;
	}
	
	public String toString() {
		String st = "Student id=" + id + "  num=" + number + "  name=" + name
				+ "\n";
		for (Class t : classes) {
			st += "  " + t + "\n";
		}
		return st;
	}

}
