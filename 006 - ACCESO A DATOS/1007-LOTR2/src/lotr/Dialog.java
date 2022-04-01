package lotr;

public class Dialog {

	//atributos
	private String id;
	private String dialog;
	private Movie movie;
	private Character character;
	
	//constructores
	public Dialog(String id, String dialog, Movie movie, Character character) {
		this.id = id;
		this.dialog = dialog;
		this.movie = movie;
		this.character = character;
	}
	
	public Dialog() {};

	//getters and setters
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDialog() {
		return dialog;
	}

	public void setDialog(String dialog) {
		this.dialog = dialog;
	}

	public Movie getId_movie() {
		return movie;
	}

	public void setId_movie(Movie id_movie) {
		this.movie = id_movie;
	}

	public Character getId_character() {
		return character;
	}

	public void setId_character(Character id_character) {
		this.character = id_character;
	}
}
