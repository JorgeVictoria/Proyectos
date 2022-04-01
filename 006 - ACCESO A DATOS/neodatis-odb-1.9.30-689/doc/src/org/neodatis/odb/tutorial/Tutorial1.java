/*
NeoDatis ODB : Native Object Database (odb.info@neodatis.org)
Copyright (C) 2007 NeoDatis Inc. http://www.neodatis.org

This file is part of the db4o open source object database.

NeoDatis ODB is free software; you can redistribute it and/or
modify it under the terms of the GNU Lesser General Public
License as published by the Free Software Foundation; either
version 2.1 of the License, or (at your option) any later version.

NeoDatis ODB is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
Lesser General Public License for more details.

You should have received a copy of the GNU Lesser General Public
License along with this library; if not, write to the Free Software
Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */
package org.neodatis.odb.tutorial;

import java.util.Date;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBAuthenticationRuntimeException;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.OID;
import org.neodatis.odb.ObjectValues;
import org.neodatis.odb.Objects;
import org.neodatis.odb.Values;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.core.query.nq.SimpleNativeQuery;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;
import org.neodatis.odb.impl.core.query.values.ValuesCriteriaQuery;
import org.neodatis.odb.xml.XMLExporter;
import org.neodatis.odb.xml.XMLImporter;
import org.neodatis.tool.IOUtil;

public class Tutorial1 {
	public static final String ODB_NAME = "tutorial1.neodatis";

	public static final String ODB_NAME_2 = "tutorial1bis.neodatis";

	public Tutorial1() throws Exception {
		clearDatabase();
	}

	public void step1() throws Exception {

		// Create instance
		Sport sport = new Sport("volley-ball");

		ODB odb = null;

		try {
			// Open the database
			odb = ODBFactory.open(ODB_NAME);

			// Store the object
			odb.store(sport);
		} finally {
			if (odb != null) {
				// Close the database
				odb.close();
			}
		}
	}

	public void step2() throws Exception {

		// Create instance
		Sport volleyball = new Sport("volley-ball");

		// Create 4 players
		Player player1 = new Player("olivier", new Date(), volleyball);
		Player player2 = new Player("pierre", new Date(), volleyball);
		Player player3 = new Player("elohim", new Date(), volleyball);
		Player player4 = new Player("minh", new Date(), volleyball);

		// Create two teams
		Team team1 = new Team("Paris");
		Team team2 = new Team("Montpellier");

		// Set players for team1
		team1.addPlayer(player1);
		team1.addPlayer(player2);

		// Set players for team2
		team2.addPlayer(player3);
		team2.addPlayer(player4);

		// Then create a volley ball game for the two teams
		Game game = new Game(new Date(), volleyball, team1, team2);

		ODB odb = null;

		try {
			// Open the database
			odb = ODBFactory.open(ODB_NAME);

			// Store the object
			odb.store(game);
		} finally {
			if (odb != null) {
				// Close the database
				odb.close();
			}
		}
	}

	/** Criteria Query */
	public void step3() throws Exception {
		ODB odb = null;

		try {
			// Open the database
			odb = ODBFactory.open(ODB_NAME);
			IQuery query = new CriteriaQuery(Player.class, Where.equal("name", "olivier"));
			Objects <Player>players = odb.getObjects(query);

			System.out.println("\nStep 3 : Players with name olivier");

			int i = 1;
			// display each object
			while (players.hasNext()) {
				System.out.println((i++) + "\t: " + players.next());
			}
		} finally {
			if (odb != null) {
				// Close the database
				odb.close();
			}
		}
	}

	/**
	 * Where query with relations
	 * 
	 */
	public void step4() throws Exception {

		ODB odb = null;

		try {
			// Open the database
			odb = ODBFactory.open(ODB_NAME);
			// Let's insert a tennis player
			Player agassi = new Player("Andr\u00E9 Agassi", new Date(), new Sport("Tennis"));
			odb.store(agassi);

			IQuery query = new CriteriaQuery(Player.class, Where.equal("favoriteSport.name", "volley-ball"));

			Objects<Player> players = odb.getObjects(query);

			System.out.println("\nStep 4 : Players of Voller-ball");

			int i = 1;
			// display each object
			while (players.hasNext()) {
				System.out.println((i++) + "\t: " + players.next());
			}
		} finally {
			if (odb != null) {
				// Close the database
				odb.close();
			}
		}
	}

	/**
	 * Criteria query with relations
	 * 
	 */
	public void step5() throws Exception {

		ODB odb = null;

		try {
			// Open the database
			odb = ODBFactory.open(ODB_NAME);
			// retrieve the volley ball sport object
			IQuery query = new CriteriaQuery(Sport.class, Where.equal("name", "volley-ball"));
			Sport volleyBall = (Sport) odb.getObjects(query).getFirst();

			// Now build a query to get all players that play volley ball, using
			// the volley ball object
			query = new CriteriaQuery(Player.class, Where.equal("favoriteSport", volleyBall));

			Objects<Player> players = odb.getObjects(query);

			System.out.println("\nStep 5: Players of Voller-ball");

			int i = 1;
			// display each object
			while (players.hasNext()) {
				System.out.println((i++) + "\t: " + players.next());
			}

		} finally {
			if (odb != null) {
				// Close the database
				odb.close();
			}
		}
	}

	/**
	 * Criteria query with relations and OR
	 * 
	 */
	public void step6() throws Exception {
		ODB odb = null;

		try {
			// Open the database
			odb = ODBFactory.open(ODB_NAME);
			IQuery query = new CriteriaQuery(Player.class, Where.or().add(Where.equal("favoriteSport.name", "volley-ball")).add(
					Where.like("favoriteSport.name", "%nnis")));

			Objects<Player> players = odb.getObjects(query);

			System.out.println("\nStep 6 : Volley-ball and Tennis Players");

			int i = 1;
			// display each object
			while (players.hasNext()) {
				System.out.println((i++) + "\t: " + players.next());
			}
		} finally {
			if (odb != null) {
				// Close the database
				odb.close();
			}
		}
	}

	/**
	 * Criteria query with relations and NOT
	 * 
	 */
	public void step7() throws Exception {
		ODB odb = null;

		try {
			// Open the database
			odb = ODBFactory.open(ODB_NAME);
			IQuery query = new CriteriaQuery(Player.class, Where.not(Where.equal("favoriteSport.name", "volley-ball")));

			Objects<Player> players = odb.getObjects(query);

			System.out.println("\nStep 7 : Players that don't play Volley-ball");

			int i = 1;
			// display each object
			while (players.hasNext()) {
				System.out.println((i++) + "\t: " + players.next());
			}

		} finally {
			if (odb != null) {
				// Close the database
				odb.close();
			}
		}
	}

	/**
	 * Native query
	 * 
	 */
	public void step8() throws Exception {
		ODB odb = null;

		try {
			// Open the database
			odb = ODBFactory.open(ODB_NAME);
			IQuery query = new SimpleNativeQuery() {
				public boolean match(Player player) {
					return player.getFavoriteSport().getName().toLowerCase().startsWith("volley");
				}
			};

			Objects<Player> players = odb.getObjects(query);

			System.out.println("\nStep 8 bis: Players that play Volley-ball");

			int i = 1;
			// display each object
			while (players.hasNext()) {
				System.out.println((i++) + "\t: " + players.next());
			}

		} finally {
			if (odb != null) {
				// Close the database
				odb.close();
			}
		}
	}

	/**
	 * Native query with Objectss,
	 * 
	 */
	public void step9() throws Exception {
		ODB odb = null;

		try {
			// Open the database
			odb = ODBFactory.open(ODB_NAME);

			// first retrieve the player Minh
			IQuery query = new CriteriaQuery(Player.class, Where.equal("name", "minh"));
			Player minh = (Player) odb.getObjects(query).getFirst();

			// builds a query to get all teams where mihn plays
			query = new CriteriaQuery(Team.class, Where.contain("players", minh));
			Objects<Team> teams = odb.getObjects(query);

			System.out.println("\nStep 9: Team where minh plays");

			int i = 1;
			// display each object
			while (teams.hasNext()) {
				System.out.println((i++) + "\t: " + teams.next());
			}

		} finally {
			if (odb != null) {
				// Close the database
				odb.close();
			}
		}
	}

	/**
	 * Ordering query result
	 * 
	 * @throws Exception
	 */
	public void step10() throws Exception {
		ODB odb = null;

		try {
			// Open the database
			odb = ODBFactory.open(ODB_NAME);
			IQuery query = new CriteriaQuery(Player.class);
			query.orderByAsc("name");

			Objects<Player> players = odb.getObjects(query);

			System.out.println("\nStep 10: Players ordered by name asc");

			int i = 1;
			// display each object
			while (players.hasNext()) {
				System.out.println((i++) + "\t: " + players.next());
			}

			query.orderByDesc("name");

			players = odb.getObjects(query);

			System.out.println("\nStep 10: Players ordered by name desc");

			i = 1;
			// display each object
			while (players.hasNext()) {
				System.out.println((i++) + "\t: " + players.next());
			}

		} finally {
			if (odb != null) {
				odb.close();
			}
		}
	}

	/**
	 * Using indexes
	 * 
	 */
	public void step11() throws Exception {
		// Open the database
		ODB odb = null;

		try {
			odb = ODBFactory.open(ODB_NAME);

			String[] fieldNames = { "name" };
			// Creates the index 
			odb.getClassRepresentation(Sport.class).addUniqueIndexOn("sport-index", fieldNames, true);
			odb.close();

			odb = ODBFactory.open(ODB_NAME);
			IQuery query = new CriteriaQuery(Sport.class, Where.equal("name", "volley-ball"));

			Objects<Player> sports = odb.getObjects(query);

			// Display execution plan details to check if index has been used
			System.out.println(query.getExecutionPlan().getDetails());
			
			System.out.println("\nStep 11 : Using index");

			int i = 1;
			// display each object
			while (sports.hasNext()) {
				System.out.println((i++) + "\t: " + sports.next());
			}

		} finally {
			if (odb != null) {
				// Close the database
				odb.close();
			}
		}
	}
	
	/**
	 * Using indexes 2
	 * 
	 */
	public void step11_b() throws Exception {
		// Open the database
		ODB odb = null;

		try {
			odb = ODBFactory.open(ODB_NAME);

			String[] fieldNames = { "name" };
			odb.getClassRepresentation(Sport.class).addUniqueIndexOn("sport-index", fieldNames, true);
			odb.getClassRepresentation(Sport.class).existIndex("sport-index");
			odb.getClassRepresentation(Sport.class).deleteIndex("sport-index", true);
			odb.getClassRepresentation(Sport.class).rebuildIndex("sport-index", true);
			odb.close();

			odb = ODBFactory.open(ODB_NAME);
			IQuery query = new CriteriaQuery(Sport.class, Where.equal("name", "volley-ball"));

			Objects sports = odb.getObjects(query);
			
			// Display execution plan details to check if index has been used
			System.out.println(query.getExecutionPlan().getDetails());

			System.out.println("\nStep 11 : Using index");

			int i = 1;
			// display each object
			while (sports.hasNext()) {
				System.out.println((i++) + "\t: " + sports.next());
			}

		} finally {
			if (odb != null) {
				// Close the database
				odb.close();
			}
		}
	}

	/**
	 * Updating objects
	 * 
	 */
	public void step12() throws Exception {
		ODB odb = null;

		try {
			// Open the database
			odb = ODBFactory.open(ODB_NAME);
			IQuery query = new CriteriaQuery(Sport.class, Where.equal("name", "volley-ball"));

			Objects<Sport> sports = odb.getObjects(query);

			// Gets the first sport (there is only one!)
			Sport volley = sports.getFirst();

			// Changes the name
			volley.setName("Beach-Volley");

			// Actually updates the object
			odb.store(volley);

			// Commits the changes
			odb.close();

			odb = ODBFactory.open(ODB_NAME);
			// Now query the database to check the change
			sports = odb.getObjects(Sport.class);

			System.out.println("\nStep 12 : Updating sport");

			int i = 1;
			// display each object
			while (sports.hasNext()) {
				System.out.println((i++) + "\t: " + sports.next());
			}

		} finally {
			if (odb != null) {
				// Close the database
				odb.close();
			}
		}
	}

	/**
	 * Deleting objects
	 * 
	 */
	public void step13() throws Exception {

		ODB odb = null;

		try {
			// Open the database
			odb = ODBFactory.open(ODB_NAME);
			IQuery query = new CriteriaQuery(Player.class, Where.like("name", "%Agassi"));

			Objects<Player> players = odb.getObjects(query);

			// Gets the first player (there is only one!)
			Player agassi = players.getFirst();

			odb.delete(agassi);

			odb.close();

			odb = ODBFactory.open(ODB_NAME);
			// Now query the databas eto check the change
			players = odb.getObjects(Player.class);

			System.out.println("\nStep 13 : Deleting players");

			int i = 1;
			// display each object
			while (players.hasNext()) {
				System.out.println((i++) + "\t: " + players.next());
			}

		} finally {
			if (odb != null) {
				// Close the database
				odb.close();
			}
		}
	}

	/**
	 * Deleting objects using id
	 * 
	 */
	public void step14() throws Exception {
		ODB odb = null;

		try {
			// Open the database
			odb = ODBFactory.open(ODB_NAME);

			Sport tennis = (Sport) odb.getObjects(new CriteriaQuery(Sport.class, Where.equal("name", "Tennis"))).getFirst();
			// Firts re-create Agassi player - it has been deleted in step 13
			Player agassi = new Player("Andr\u00E9 Agassi", new Date(), tennis);
			odb.store(agassi);
			odb.commit();

			IQuery query = new CriteriaQuery(Player.class, Where.like("name", "%Agassi"));

			Objects players = odb.getObjects(query);

			// Gets the first player (there is only one!)
			agassi = (Player) players.getFirst();
			OID agassiId = odb.getObjectId(agassi);

			odb.deleteObjectWithId(agassiId);

			odb.close();

			odb = ODBFactory.open(ODB_NAME);
			// Now query the databas eto check the change
			players = odb.getObjects(Player.class);

			System.out.println("\nStep 14 : Deleting players");

			int i = 1;
			// display each object
			while (players.hasNext()) {
				System.out.println((i++) + "\t: " + players.next());
			}

		} finally {
			if (odb != null) {
				// Close the database
				odb.close();
			}
		}
	}

	/**
	 * exporting to XML
	 * 
	 */
	public void step15() throws Exception {
		ODB odb = null;

		try {
			// Open the database
			odb = ODBFactory.open(ODB_NAME);
			// Creates the exporter
			XMLExporter exporter = new XMLExporter(odb);

			// Actually export to current directory into the sports.xml file
			exporter.export(".", "sports.xml");
		} finally {
			if (odb != null) {
				// Close the database
				odb.close();
			}
		}
		System.out.println("\nStep 15 : exporting database to sports.xml");
	}

	/**
	 * importing from XML
	 * 
	 */
	public void step16() throws Exception {
		ODB odb = null;

		try {
			// Delete database first
			IOUtil.deleteFile("imported-" + ODB_NAME);

			// Open a database to receive imported data
			odb = ODBFactory.open("imported-" + ODB_NAME);
			// Creates the exporter
			XMLImporter importer = new XMLImporter(odb);

			// Actually import data from sports.xml file
			importer.importFile(".", "sports.xml");

			// Closes the database
			odb.close();

			// Re open the database
			odb = ODBFactory.open("imported-" + ODB_NAME);
			// Now query the databas eto check the change
			Objects<Player> players = odb.getObjects(Player.class);

			System.out.println("\nStep 16 : getting players of imported database");

			int i = 1;
			// display each object
			while (players.hasNext()) {
				System.out.println((i++) + "\t: " + players.next());
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (odb != null) {
				// Close the database
				odb.close();
			}
		}
	}

	/**
	 * Using user and password to protect the database file
	 * 
	 */
	public void step17() throws Exception {
		ODB odb = null;

		try {
			// Open the database
			odb = ODBFactory.open(ODB_NAME_2, "user", "password");
			odb.store(new Sport("Tennis"));
			// Commits the changes
			odb.close();

			try {
				// try to open the database without user/password
				odb = ODBFactory.open(ODB_NAME_2);
			} catch (ODBAuthenticationRuntimeException e) {
				System.out.println("\nStep 17 : invalid user/password : database could not be opened");
			}
			// then open the database with correct user/password
			odb = ODBFactory.open(ODB_NAME_2, "user", "password");
			System.out.println("\nStep 17 : user/password : database opened");
		} finally {
			if (odb != null) {
				// Close the database
				odb.close();
			}
		}
	}

	public void displayObjectsOf(Class clazz, String label1, String label2) throws Exception {
		// Open the database
		ODB odb = null;

		try {
			odb = ODBFactory.open(ODB_NAME);
			// Get all object of type clazz
			Objects objects = odb.getObjects(clazz);

			System.out.println("\n" + label1 + " : " + objects.size() + label2);

			int i = 1;
			// display each object
			while (objects.hasNext()) {
				System.out.println((i++) + "\t: " + objects.next());
			}

		} finally {
			if (odb != null) {
				// Close the database
				odb.close();
			}
		}
	}

	public void clearDatabase() throws Exception {
		Objects objects = null;

		// Open the database
		ODB odb = null;

		try {
			odb = ODBFactory.open(ODB_NAME);
			objects = odb.getObjects(Game.class);
			while (objects.hasNext()) {
				odb.delete(objects.next());
			}
			objects = odb.getObjects(Player.class);
			while (objects.hasNext()) {
				odb.delete(objects.next());
			}
			objects = odb.getObjects(Sport.class);
			while (objects.hasNext()) {
				odb.delete(objects.next());
			}
		} finally {
			if (odb != null) {
				// Close the database
				odb.close();
			}
		}
	}

	
	
	public void step18_ObjectValues() throws Exception {
		ODB odb = null;
		System.out.println("\nStep 18 : Object Values");
		try {
			// Open the database
			odb = ODBFactory.open(ODB_NAME);
			
			// Creates 100 players
			for(int i=0;i<10;i++){
				odb.store(new Player("player "+i,new Date(),new Sport("Sport "+i)));
			}
			// Close the database
			odb.close();
			
			// Opens the database
			odb = ODBFactory.open(ODB_NAME);
			// Executes the Object Values query
			Values values = odb.getValues(new ValuesCriteriaQuery(Player.class).field("name").field("favoriteSport.name", "sport"));
			
			// Iterate of the result
			while(values.hasNext()){
				// Each object is an ObjectValues that gives access to the fields
				ObjectValues objectValues= (ObjectValues) values.next();
				// Prints the name of the player and the name of the sport
				// Retrieve the player name by alias and the sport name by index
				System.out.println(objectValues.getByAlias("name") + " plays " + objectValues.getByIndex(1));
			}
		} finally {
			if (odb != null) {
				// Close the database
				odb.close();
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		IOUtil.deleteFile(ODB_NAME);
		Tutorial1 tutorial1 = new Tutorial1();

		tutorial1.step1();
		tutorial1.displayObjectsOf(Sport.class, "Step 1", " sport(s):");

		tutorial1.clearDatabase();

		tutorial1.step2();
		tutorial1.displayObjectsOf(Game.class, "Step 2", " games(s):");
		tutorial1.displayObjectsOf(Team.class, "Step 2", " team(s):");
		tutorial1.displayObjectsOf(Player.class, "Step 2", " player(s):");
		tutorial1.displayObjectsOf(Sport.class, "Step 2", " sport(s):");

		tutorial1.step3();

		tutorial1.step4();
		tutorial1.step5();
		tutorial1.step6();
		tutorial1.step7();
		tutorial1.step8();
		tutorial1.step9();
		tutorial1.step10();
		tutorial1.step11();
		tutorial1.step12();
		tutorial1.step13();
		
		tutorial1.step14();
		tutorial1.step15();
		tutorial1.step16();
		tutorial1.step17();
		tutorial1.step18_ObjectValues();

	}

}
