package hogwarts;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class Principal {

	public static void main(String[] args) {
			
			//Inicializamos el entorno Hibernate
			Configuration cfg = new Configuration().configure();
			
			//crear el ejemplar de session factory
			SessionFactory sf = cfg.buildSessionFactory(new StandardServiceRegistryBuilder().configure().build());
			
			//abrir la sesion
			Session sesion = sf.openSession();
			
			//llamada a ejercicios
			ejercicio1(sesion);
			ejercicio2(sesion);
			ejercicio3(sesion);
			ejercicio4(sesion);
			
			//cerrar la sesion
			sf.close();

		}
		
		
		/**
		 * metodo que saca los miembros de la familia Potter
		 * @param sesion
		 */
		public static void ejercicio1(Session sesion) {
			
			//banner
			System.out.println();
			System.out.println("Ejercicio 1:");
			System.out.println();
			
			//almacenamos la consulta en una coleccion de tipo lista
			List<Person> dl = sesion.createQuery("from Person as pe where pe.lastName = 'Potter'").getResultList();
			
			//corremos la coleccion
			for(Person p: dl) System.out.println(p.getFirstName() + " " + p.getLastName() + " - " + p.getHouse().getName());
			
		}
		
		/**
		 * metodo que muestra los alumnos de la cas Gryffindor
		 * @param sesion
		 */
		private static void ejercicio2(Session sesion) {
			
			//banner
			System.out.println();
			System.out.println("Ejercicio 2:");
			System.out.println();
			
			//almacenamos la query en un Scrollable
			ScrollableResults sr = sesion.createQuery("from Person p join p.courses_1 group by p.lastName").scroll();
			
			//corremos la coleccion
			while(sr.next()) {
				Person p = (Person)sr.get(0);
				System.out.println(p.getLastName() + ", " + p.getFirstName());
			}
			
			//alamaceanamos en una variable de tipo la query con el count
			Long count = (Long)sesion.createQuery("select count(distinct p.id) from Person p join p.courses_1").uniqueResult();
			
			//pasamos la consulta a un valor de tipo int y mostramos el resultado
			Integer total = count.intValue();
			System.out.println("Numero total de estudiantes: " + total);
		}
		
		/**
		 * metodo para contar los puntos que han sido dados o quitados a ciertos alumnos y quien ha sido el profesor que ha puntuado
		 * @param sesion
		 */
		private static void ejercicio3(Session sesion) {
			
			//variable para contar el total de puntos
			int puntosTotales = 0;
			
			//banner
			System.out.println();
			System.out.println("Ejercicio 3:");
			System.out.println();
			
			//almacenamos en un objeto de tipo list la query
			List<HousePoints> li = sesion.createQuery("from HousePoints as hp where (hp.personByReceiver.firstName = ?1 and hp.personByReceiver.lastName = ?2) or (hp.personByReceiver.firstName = ?3 and hp.personByReceiver.lastName = ?4)  or (hp.personByReceiver.firstName = ?5 and hp.personByReceiver.lastName = ?6) order by hp.personByReceiver.firstName")
					.setParameter(1, "Harry").setParameter(2, "Potter")
					.setParameter(3, "Ron").setParameter(4, "Weasley")
					.setParameter(5, "Hermione").setParameter(6, "Granger").getResultList();
			
			//corremos la lista y la vez hacemos el recuento de puntos
			for(HousePoints hp: li) {
				System.out.println(hp.getPersonByGiver().getFirstName() + " -> "  + hp.getPoints() + " puntos para " 
							+ hp.getPersonByReceiver().getFirstName() +  " " +  hp.getPersonByReceiver().getLastName()) ;
				puntosTotales = puntosTotales + hp.getPoints();
			}
			
			//mostramos los puntos
			System.out.println("Total Puntos: " + puntosTotales);
			
		}
		
		/**
		 * metodo para mostrar los alumnos de Gryffindor que estudian Pociones y su profesor
		 * @param sesion
		 */
		private static void ejercicio4(Session sesion) {
			
			//banner para el primer apartado
			System.out.println();
			System.out.println("Ejercicio 4.1:");
			System.out.println();
			
			//almacenamos la consulta en un Scrollable
			ScrollableResults sc = sesion.createQuery("from Person as p inner join p.courses_1 as co, Course as cou where co.name = 'Potions' and p.house.name = 'Gryffindor' and cou.name = 'Potions'").scroll();
			
			//corremos la coleccion y mostramos los resultados
			while(sc.next()) {
				Person alumno = (Person)sc.get(0);
				Course modulo = (Course)sc.get(1);
				Course profesor = (Course)sc.get(2);
				System.out.printf("%s %s estudia %s con %s %s\n", alumno.getFirstName(),alumno.getLastName(), modulo.getName(), profesor.getPerson().getFirstName(), profesor.getPerson().getLastName());
			}
			
			//almacenamos en una variable de tipo long la query con el conteo de miembros
			Long count = (Long)sesion.createQuery("select count(*) from Person as p inner join p.courses_1 as co, Course as cou where co.name = 'Potions' and p.house.name = 'Gryffindor' and cou.name = 'Potions'").uniqueResult();
			
			//pasamos el resultado de la query a un valor entero y mostramos el resultado
			Integer total = count.intValue();
			System.out.println("Numero total de estudiantes: " + total);
			
			//banner para el segundo apartado
			System.out.println();
			System.out.println("Ejercicio 4.2:");
			System.out.println();
			
			//almacenamos la consulta en un objeto de tipo Curso. Será unica. 
			Course curso = (Course) sesion.createQuery("Select curso from Course as curso inner join fetch curso.persons as alumno inner join curso.person as profesor where curso.name = 'Potions' and alumno.house.name = 'Gryffindor' order by alumno.lastName").uniqueResult();
			
			//pasamos el resultado de la consulta a una coleccion
			Set<Person> personas = curso.getPersons();
			
			//corremos la coleccion y mostramos los resultados
			for(Person personaje : personas) {
				System.out.printf("%s %s estudia %s con %s %s\n", personaje.getFirstName(), personaje.getLastName(), curso.getName(), curso.getPerson().getFirstName(), curso.getPerson().getLastName());
			}
			
			//imprimimos el total de registros de la consulta
			System.out.println("Numero total de estudiantes: " + curso.getPersons().size());
	}

}
