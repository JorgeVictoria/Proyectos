package lotr;

import java.util.HashSet;
import java.util.Set;

public class Book {
	
	//atributos
	private String id;
	private String title;
	private Set<Movie> movies = new HashSet<Movie>();
	private Set<Chapter> chapters = new HashSet<Chapter>();
	
	//constructores
	public Book(String id, String title, Set<Movie> movies, Set<Chapter> chapters) {
		this.id = id;
		this.title = title;
		this.movies = movies;
		this.chapters = chapters;
	}
	
	public Book() {}

	//getters and setters
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Set<Movie> getMovies() {
		return movies;
	}

	public void setMovies(Movie movie) {
		this.movies.add(movie);
	}

	public Set<Chapter> getChapters() {
		return chapters;
	}

	public void setChapters(Chapter chapter) {
		this.chapters.add(chapter);
	}
	
	
	
	

}
