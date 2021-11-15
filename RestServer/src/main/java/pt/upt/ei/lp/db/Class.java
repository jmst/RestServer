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
public class Class {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Expose
	private int id;
	@Expose
	private int number;
	@Expose
	private String name;

	@ManyToMany(mappedBy="classes")
//	@JoinTable(name = "StudentClass", joinColumns = { @JoinColumn(name = "idStudent", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "idClass", referencedColumnName = "id") })
	private List<Student> students = new ArrayList<Student>();

	public Class() {
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

	public String toString() {
		String st = "Class id=" + id + "  num=" + number + "  name=" + name;
		return st;
	}

}
