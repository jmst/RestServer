package pt.upt.ei.lp.restServer;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import pt.upt.ei.lp.db.Book;
import pt.upt.ei.lp.db.BookService;
import pt.upt.ei.lp.db.Reader;
import pt.upt.ei.lp.db.ReaderService;

@Path("/")
public class RestServer {
	private static final String PERSISTENCE_UNIT_NAME = "LibraryJPA";
	private static EntityManagerFactory factory;
	private static EntityManager emanager = null;

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayPlainTextHello() {
		return "REST Server for readers and books";
	}

	@GET
	@Path("/reader/{id}")
	@Produces("application/json")
	public String getReader(@PathParam("id") int numid) {
		GsonBuilder builder = new GsonBuilder();
		builder.setPrettyPrinting();
		builder.excludeFieldsWithoutExposeAnnotation();
		Gson gson = builder.create();
		ReaderService rs = new ReaderService(getEM());
		Reader r = rs.findReader(numid);
		String jsonString = gson.toJson(r);
		System.out.println(jsonString);
		return jsonString;
	}

	@GET
	@Path("/readerBooks/{id}")
	@Produces("application/json")
	public String getReaderBooks(@PathParam("id") int numid) {
		GsonBuilder builder = new GsonBuilder();
		builder.setPrettyPrinting();
		builder.excludeFieldsWithoutExposeAnnotation();
		Gson gson = builder.create();
		ReaderService rs = new ReaderService(getEM());
		Reader r = rs.findReader(numid);
		List<Book> books = r.getBooks();
		String jsonString = gson.toJson(books);
		return jsonString;
	}

	@GET
	@Path("/readers")
	@Produces("application/json")
	public String getReaders() {
		System.out.println("GET ALL READERS");
		GsonBuilder builder = new GsonBuilder();
		builder.setPrettyPrinting();
		builder.excludeFieldsWithoutExposeAnnotation();
		Gson gson = builder.create();
		ReaderService rs = new ReaderService(getEM());
		List<Reader> readers = rs.findAllReaders();
		String jsonString = gson.toJson(readers);
		System.out.println(jsonString);
		return jsonString;
	}

	@POST
	@Path("/reader/{id}")
	@Consumes("application/json")
	@Produces("application/json")
	public String postReader(@PathParam("id") int numid, String data) {
		System.out.println("POST reader data : " + data);
		GsonBuilder builder = new GsonBuilder();
		builder.excludeFieldsWithoutExposeAnnotation();
		builder.setPrettyPrinting();
		Gson gson = builder.create();
		Reader r = gson.fromJson(data, Reader.class);
		EntityManager em = getEM();
		ReaderService rs = new ReaderService(em);
		em.getTransaction().begin();
		rs.updateReader(r.getId(),r.getName(),r.getPhone(),r.getBooks());
		em.getTransaction().commit();
		return gson.toJson(rs.findReader(numid));
	}

	@DELETE
	@Path("/reader/{id}")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteReader(@PathParam("id") int numid) {
		System.out.println("DELETE reader data : " + numid);
		EntityManager em = getEM();
		ReaderService rs = new ReaderService(em);
		em.getTransaction().begin();
		rs.removeReader(numid);
		em.getTransaction().commit();
		return "OK";
	}

	@GET
	@Path("/book/{id}")
	@Produces("application/json")
	public String getBook(@PathParam("id") int numid) {
		GsonBuilder builder = new GsonBuilder();
		builder.setPrettyPrinting();
		builder.excludeFieldsWithoutExposeAnnotation();
		Gson gson = builder.create();
		BookService bs = new BookService(getEM());
		Book b = bs.findBook(numid);
		String jsonString = gson.toJson(b);
		return jsonString;
	}

	@GET
	@Path("/books")
	@Produces("application/json")
	public String getBooks() {
		GsonBuilder builder = new GsonBuilder();
		builder.setPrettyPrinting();
		builder.excludeFieldsWithoutExposeAnnotation();
		Gson gson = builder.create();
		BookService bs = new BookService(getEM());
		List<Book> books = bs.findAllBooks();
		String jsonString = gson.toJson(books);
		return jsonString;
	}

	@POST
	@Path("/book/{id}")
	@Consumes("application/json")
	@Produces("application/json")
	public String postBook(@PathParam("id") int numid, String data) {
		System.out.println("POST book data : " + data);
		GsonBuilder builder = new GsonBuilder();
		builder.setPrettyPrinting();
		builder.excludeFieldsWithoutExposeAnnotation();
		Gson gson = builder.create();
		Book b = gson.fromJson(data, Book.class);
		EntityManager em = getEM();
		BookService bs = new BookService(em);
		em.getTransaction().begin();
		b = bs.updateBook(b.getId(),b.getTitle(),b.getAuthor(), b.getAvailable());
		em.getTransaction().commit();
		return gson.toJson(b);
	}

	@POST
	@Path("/fill")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces("application/json")
	public String fill() {
		System.out.println("========");
		System.out.println("  FILL");
		System.out.println("========");
		EntityManager em = getEM();
		Query q = null;
		List<Reader> readers = null;
		List<Book> books = null;
		// Remove the existing entries
		em.getTransaction().begin();
		ReaderService rs = new ReaderService(getEM());
		List<Reader> readerList = rs.findAllReaders();
		for (Reader a : readerList) {
			rs.removeReader(a.getId());
		}
		BookService bs = new BookService(getEM());
		List<Book> bookList = bs.findAllBooks();
		for (Book b : bookList) {
			bs.removeBook(b.getId());
		}
		em.getTransaction().commit();
		//
		System.out.println("Cleaned DB");
		System.out.println("------------------------");
		// Begin a new local transaction so that we can persist new entities
		em.getTransaction().begin();
		// create students
		Reader r1 = rs.updateReader(0,"Alice","111111111");
		Reader r2 = rs.updateReader(0,"Bruno","222222222");
		Reader r3 = rs.updateReader(0,"Carlos","333333333");
		Reader r4 = rs.updateReader(0,"Duarte","444444444");
		// create classes and assign them to students
		Book b1 = bs.updateBook(0, "IA", "autor 1", false);
		r1.getBooks().add(b1);
		Book b2 = bs.updateBook(0, "POO", "author 2", false);
		r3.getBooks().add(b2);
		Book b3 = bs.updateBook(0, "SD", "author 3", false);
		r4.getBooks().add(b3);
		Book b4 = bs.updateBook(0, "PROJ", "author 2", false);
		r3.getBooks().add(b4);
		Book b5 = bs.updateBook(0, "Lusiadas", "Camões", true);
		Book b6 = bs.updateBook(0, "História", "author 4", true);
		Book b7 = bs.updateBook(0, "Os Maias", "Eça de Queirós", true);

		// Commit the transaction, which will cause the entity to
		// be stored in the database
		em.getTransaction().commit();
		//
		// print the data in the database
		//
		readers = rs.findAllReaders();
		System.out.println("------------------------");
		System.out.println("Readers table");
		for (Reader a : readers) {
			System.out.println(a);
		}
		//
		books = bs.findAllBooks();
		System.out.println("------------------------");
		System.out.println("Books table");
		for (Book t : books) {
			System.out.println(t);
		}
		System.out.println("------------------------");
		System.out.println("\n\nFinished!!!");
		//
		GsonBuilder builder = new GsonBuilder();
		builder.setPrettyPrinting();
		Gson gson = builder.create();
		readers = rs.findAllReaders();

		String jsonString = gson.toJson(readers);
		System.out.println(jsonString);
		return jsonString;
	}
	

	public static EntityManager getEM() {
		if (emanager == null) {
			factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
			emanager = factory.createEntityManager();
		}
		return emanager;
	}
}
