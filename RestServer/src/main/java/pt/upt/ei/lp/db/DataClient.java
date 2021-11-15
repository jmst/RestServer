package pt.upt.ei.lp.db;

import java.util.List;

public interface DataClient {
	public List<Reader> findAllReaders();
	public Reader findReader( int id);
	public Reader updateReader( int id, String name, String phone, List<Book> books);
	public Reader updateReader( int id, String name, String phone);
	public void removeReader( int id);
	public List<Book> findAllBooks();
	public Book updateBook( int id, String title, String author, Boolean available);
	public void removeBook( int id);
	public Book findBook( int id);

	
}
