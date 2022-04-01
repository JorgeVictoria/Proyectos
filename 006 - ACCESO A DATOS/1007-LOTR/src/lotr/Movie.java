package lotr;

import java.util.HashSet;
import java.util.Set;

public class Movie {
	
	private String id;
	private String name;
	private int runtimeInMinutes;
	private int budgetInMillions;
	private int boxOfficeRevenueInMillions;
	private int academyAwardNominations;
	private int academyAwardWins;
	private int TomatoesScore;
	private Set<Book> books = new HashSet<Book>();
	private Set<Dialog> dialogs = new HashSet<Dialog>();
	
	//constructor
	public Movie(String id, String name, int runtimeInMinutes, int budgetInMillions, int boxOfficeRevenueInMillions,
			int academyAwardNominations, int academyAwardWins, int tomatoesScore, Set<Book> books,
			Set<Dialog> dialogs) {
		this.id = id;
		this.name = name;
		this.runtimeInMinutes = runtimeInMinutes;
		this.budgetInMillions = budgetInMillions;
		this.boxOfficeRevenueInMillions = boxOfficeRevenueInMillions;
		this.academyAwardNominations = academyAwardNominations;
		this.academyAwardWins = academyAwardWins;
		this.TomatoesScore = tomatoesScore;
		this.books = books;
		this.dialogs = dialogs;
	}
	
	public Movie() {};

	//getters and setters
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRuntimeInMinutes() {
		return runtimeInMinutes;
	}

	public void setRuntimeInMinutes(int runtimeInMinutes) {
		this.runtimeInMinutes = runtimeInMinutes;
	}

	public int getBudgetInMillions() {
		return budgetInMillions;
	}

	public void setBudgetInMillions(int budgetInMillions) {
		this.budgetInMillions = budgetInMillions;
	}

	public int getBoxOfficeRevenueInMillions() {
		return boxOfficeRevenueInMillions;
	}

	public void setBoxOfficeRevenueInMillions(int boxOfficeRevenueInMillions) {
		this.boxOfficeRevenueInMillions = boxOfficeRevenueInMillions;
	}

	public int getAcademyAwardNominations() {
		return academyAwardNominations;
	}

	public void setAcademyAwardNominations(int academyAwardNominations) {
		this.academyAwardNominations = academyAwardNominations;
	}

	public int getAcademyAwardWins() {
		return academyAwardWins;
	}

	public void setAcademyAwardWins(int academyAwardWins) {
		this.academyAwardWins = academyAwardWins;
	}

	public int getTomatoesScore() {
		return TomatoesScore;
	}

	public void setTomatoesScore(int tomatoesScore) {
		TomatoesScore = tomatoesScore;
	}

	public Set<Book> getBooks() {
		return books;
	}

	public void setBooks(Book book) {
		this.books.add(book);
	}

	public Set<Dialog> getDialogs() {
		return dialogs;
	}

	public void setDialogs(Dialog dialog) {
		this.dialogs.add(dialog);
	}
	
	@Override
	public String toString() {
		return "Movie [id=" + id + ", name=" + name + ", runtimeInMinutes=" + runtimeInMinutes + ", budgetInMillions="
				+ budgetInMillions + ", boxOfficeRevenueInMillions=" + boxOfficeRevenueInMillions
				+ ", academyAwardNominations=" + academyAwardNominations + ", academyAwardWins=" + academyAwardWins
				+ ", TomatoesScore=" + TomatoesScore + "]";
	}
	
	

}
