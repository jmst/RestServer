package pt.upt.ei.lp.db;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class ReaderService {
	protected EntityManager em;
	
	public ReaderService(EntityManager em) {
		this.em = em;
	}
	
	public Reader updateReader( int id, String name, String phone, List<Book> books) {
		Reader r = em.find(Reader.class, id);
		if (r == null) {
			r = new Reader();
			em.persist( r);
		}
		r.setId(id);
		r.setName(name);
		r.setPhone(phone);
		r.getBooks().clear();
		r.getBooks().addAll(books);
		return r;
	}
	
	public Reader updateReader( int id, String name, String phone) {
		Reader r = em.find(Reader.class, id);
		if (r == null) {
			r = new Reader();
			em.persist( r);
		}
		r.setId(id);
		r.setName(name);
		r.setPhone(phone);
		r.getBooks().clear();
		return r;
	}

	public void removeReader( int id) {
		Reader s = findReader(id);
		if (s != null)
			em.remove(s);
		return;
	}

	public Reader findReader( int id) {
		return em.find(Reader.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Reader> findAllReaders() {
		 Query qd = em.createQuery("SELECT r FROM Reader r");
		 return qd.getResultList();
	}

}
