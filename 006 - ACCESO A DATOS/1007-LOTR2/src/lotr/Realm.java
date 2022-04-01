package lotr;

import java.util.HashSet;
import java.util.Set;

public class Realm {
	


	//atributos
	private String id;
	private String name;
	private int population;
	private int area;
	private Set<Character> characters = new HashSet<Character>();
	
	//constructores
	public Realm(String id, String name, int population, int area, Set<Character> characters) {
		this.id = id;
		this.name = name;
		this.population = population;
		this.area = area;
		this.characters = characters;
	}
	
	public Realm() {};

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

	public int getPopulation() {
		return population;
	}

	public void setPopulation(int population) {
		this.population = population;
	}

	public int getArea() {
		return area;
	}

	public void setArea(int area) {
		this.area = area;
	}

	public Set<Character> getCharacters() {
		return characters;
	}

	public void setCharacters(Character character) {
		this.characters.add(character);
	}
	
	
	@Override
	public String toString() {
		return "Realm [id=" + id + ", name=" + name + ", population=" + population + ", area=" + area + "]";
	}
	

}
