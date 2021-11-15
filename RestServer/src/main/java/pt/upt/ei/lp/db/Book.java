package pt.upt.ei.lp.db;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.google.gson.annotations.Expose;

@Entity
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Expose
	private int id;
	@Expose
	private String author;
	@Expose
	private String title;
	@Expose
	private Boolean available;

//	@ManyToMany(mappedBy="books")
//	@JoinTable(name = "StudentClass", joinColumns = { @JoinColumn(name = "idStudent", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "idClass", referencedColumnName = "id") })
//	private List<Student> students = new ArrayList<Student>();

	public Book() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean out) {
		this.available = out;
	}

	public String toString() {
		String st = "Book id=" + id + "  author=" + author + "  title=" + title+"  available=" + available;
		return st;
	}

}
