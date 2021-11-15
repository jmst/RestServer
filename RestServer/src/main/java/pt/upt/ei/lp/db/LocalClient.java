package pt.upt.ei.lp.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// class RestClient is responsible for accessing the Server
public class LocalClient implements DataClient {
	HashMap<Integer, Reader> readerMap;
	HashMap<Integer, Book> bookMap;
	int lastId;
	private static LocalClient plc = null;

	private LocalClient() {
		readerMap = new HashMap<Integer, Reader>();
		bookMap = new HashMap<Integer, Book>();
		lastId = 0;
		for (Integer k : readerMap.keySet()) {
			if (k > lastId)
				lastId = k;
		}
		for (Integer k : bookMap.keySet()) {
			if (k > lastId)
				lastId = k;
		}
//		defineValoresIniciais();
	}
	
	public static LocalClient getLocalClient() {
		if (plc == null)
			plc = new LocalClient();
		return plc;
	}

	public ArrayList<Reader> findAllReaders() {
		ArrayList<Reader> allReaders = new ArrayList<Reader>();
		allReaders.addAll(readerMap.values());
		return allReaders;
	}

	public Reader findReader(int id) {
		if (!readerMap.containsKey(id))
			return null;
		Reader r = readerMap.get(id);
		return r;
	}

	public Reader updateReader(int id, String name, String phone, List<Book> books) {
		Reader r = new Reader();
		r.setId(id);
		r.setName(name);
		r.setPhone(phone);
		r.getBooks().addAll(books);
		if (r.getId() <= 0) {
			lastId++;
			r.setId(lastId);
		}
		readerMap.put(r.getId(), r);
		return r;
	}

	public Reader updateReader(int id, String name, String phone) {
		Reader r;
		r = readerMap.get(id);
		if (r == null)
			r = new Reader();
		r.setId(id);
		r.setName(name);
		r.setPhone(phone);
		if (r.getId() <= 0) {
			lastId++;
			r.setId(lastId);
		}
		readerMap.put(r.getId(), r);
		return r;
	}

	public void removeReader(int rid) {
		if (rid <= 0)
			return;
		if (!readerMap.containsKey(rid))
			return;
		readerMap.remove(rid);
	}

	public List<Book> findAllBooks() {
		ArrayList<Book> allBooks = new ArrayList<Book>();
		allBooks.addAll(bookMap.values());
		return allBooks;
	}

	public Book findBook(int id) {
		if (!bookMap.containsKey(id))
			return null;
		Book b = bookMap.get(id);
		return b;
	}

	public Book updateBook( int id, String title, String author, Boolean available) {
		Book b = new Book();
		b.setId(id);
		b.setTitle(title);
		b.setAuthor(author);
		b.setAvailable(available);
		if (b.getId() <= 0) {
			lastId++;
			b.setId(lastId);
		}
		bookMap.put(b.getId(), b);
		return b;
	}

	public void removeBook(int bid) {
		if (bid <= 0)
			return;
		if (!bookMap.containsKey(bid))
			return;
		bookMap.remove(bid);
	}

	//
	// cria alguns objetos iniciais
	//
	private void defineValoresIniciais() {
		Reader r = new Reader();
		r.setId(0);
		r.setName("Zezé");
		r.setPhone("123456789");
		if (r.getId() <= 0) {
			lastId++;
			r.setId(lastId);
		}
		readerMap.put(r.getId(), r);
		
		r = new Reader();
		r.setId(0);
		r.setName("Mimi");
		r.setPhone("111111111");
		if (r.getId() <= 0) {
			lastId++;
			r.setId(lastId);
		}
		readerMap.put(r.getId(), r);
		
		r = new Reader();
		r.setId(0);
		r.setName("Lulu");
		r.setPhone("222222222");
		if (r.getId() <= 0) {
			lastId++;
			r.setId(lastId);
		}
		readerMap.put(r.getId(), r);

		Book b = new Book();
		b.setId(0);
		b.setTitle("Lusíadas");
		b.setAuthor("Luís");
		b.setAvailable(true);
		if (b.getId() <= 0) {
			lastId++;
			b.setId(lastId);
		}
		bookMap.put(b.getId(), b);

		b = new Book();
		b.setId(0);
		b.setTitle("Maias");
		b.setAuthor("José Maria");
		b.setAvailable(true);
		if (b.getId() <= 0) {
			lastId++;
			b.setId(lastId);
		}
		bookMap.put(b.getId(), b);

		b = new Book();
		b.setId(0);
		b.setTitle("História");
		b.setAuthor("Alexandre");
		b.setAvailable(true);
		if (b.getId() <= 0) {
			lastId++;
			b.setId(lastId);
		}
		bookMap.put(b.getId(), b);
	}
}
