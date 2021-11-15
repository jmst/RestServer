package pt.upt.ei.lp.db;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class BookService {

		protected EntityManager em;
		
		public BookService(EntityManager em) {
			this.em = em;
		}
		
		public Book updateBook( int id, String title, String author, Boolean available) {
			Book b = em.find(Book.class, id);
			if (b == null) {
				b = new Book();
				em.persist( b);
			}
			b.setId(id);
			b.setTitle(title);
			b.setAuthor(author);
			b.setAvailable(available);
			em.persist( b);
			return b;
		}

		public void removeBook( int id) {
			Book c = findBook(id);
			if (c != null)
				em.remove(c);
			return;
		}

		public Book findBook( int id) {
			return em.find(Book.class, id);
		}
		
		public List<Book> findAllBooks() {
			 Query qd = em.createQuery("SELECT b FROM Book b");
			 return qd.getResultList();
		}


}
