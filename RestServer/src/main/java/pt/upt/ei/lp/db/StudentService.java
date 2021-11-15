package pt.upt.ei.lp.db;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class StudentService {
	protected EntityManager em;
	
	public StudentService(EntityManager em) {
		this.em = em;
	}
	
	public Student createStudent( int id, String name, int number) {
		Student s = new Student();
		s.setId(id);
		s.setName(name);
		s.setNumber(number);
		em.persist( s);
		return s;
	}

	public void removeStudent( int id) {
		Student s = findStudent(id);
		if (s != null)
			em.remove(s);
		return;
	}

	public Student findStudent( int id) {
		return em.find(Student.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Student> findAllStudents() {
		 Query qd = em.createQuery("SELECT s FROM Student s");
		 return qd.getResultList();
	}

}
