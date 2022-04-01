package lotr;

public class Chapter {
	
	//atributos
	private String id;
	private String chapter_name;
	private Book book;
	
	//constructores
	public Chapter(String id, String chapter_name, Book book) {
		this.id = id;
		this.chapter_name = chapter_name;
		this.book = book;
	}
	
	public Chapter() {};

	//getters and setters
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getChapter_name() {
		return chapter_name;
	}

	public void setChapter_name(String chapter_name) {
		this.chapter_name = chapter_name;
	}

	public Book getId_book() {
		return book;
	}

	public void setId_book(Book id_book) {
		this.book = id_book;
	}
}
