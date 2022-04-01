package hogwarts_package;

import java.util.List;

import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;



public class Principal {

	public static void main(String[] args) {
		
		//inicializamos el entorno de hibernate
		Configuration cfg = new Configuration().configure();
		
		//crear el ejemplar de SessionFactory
		SessionFactory sf = cfg.buildSessionFactory(new StandardServiceRegistryBuilder().configure().build());
		
		//abrir la sesion
		Session sesion = sf.openSession();
		
		//ejercicio 1 : Saca por pantalla toda la información de cada miembro 
		//de la familia Potter de la tabla Person utilizando un getResultList()
		listarFamiliaPotter(sesion);
		
		//ejercicio 2 : Saca por pantalla una lista con todos los alumnos 
		//matriculados en Hogwarts, ordenados por orden alfabético de apellido. Debes 
		//utilizar el método scroll(). En una última línea saca cuántos alumnos están 
		//matriculados mediante otra consulta y el método uniqueResult().
		listarMiembrosHogwarts(sesion);
		
		//Ejercicio 3 (2.5 punto): Saca una lista de los profesores que han dado o 
		//quitado algún punto a Harry Potter, Ron Weasley y Hermione Granger junto 
		//con los puntos dados o quitados, así como el total de puntos de todos. Debes 
		//utilizar una consulta con parámetros en la consulta.
		listaPuntos(sesion);
		
		/*Ejercicio 4 (4 puntos): Saca por pantalla una lista con todos los estudiantes de 
		Gryffindor que tienen la asignatura de Pociones junto con el nombre del 
		profesor. En una última línea saca cuántos alumnos hay en la lista. Este ejercicio 
		deberás hacerlo de dos formas diferentes: con una consulta con inner join sin 
		fetch y con fetch. Para la primera deberá utilizar una consulta no asociada*/
		estudianPociones(sesion);
		
		//cerrar la sesion
		sesion.close();
		

	}

	

	private static void listarFamiliaPotter(Session sesion) {
		
		//creamos la lista con la consulta que queremos realizar
		List<Person> pl = sesion.createQuery("from Person AS p where p.lastName = 'Potter'").getResultList();
		//mostramos el resultado por pantalla
		System.out.println("Ejercicio01");
		//corremos la coleccion y mostramos la info solicitada
		for(Person p: pl) System.out.println(p.getFirstName() + " " + p.getLastName() + " - " + p.getHouse().getName());
		
		
	}
	
	private static void listarMiembrosHogwarts(Session sesion) {
		
		//ScrollableResults sr = sesion.createQuery("select p.firstName, p,lastName from Person as p join p.courses_1 as c group by p.lastName).scroll();
		ScrollableResults sr = sesion.createQuery("from Person as p order by p.lastName " ).scroll();
		System.out.println();
		System.out.println("Ejercicio02");
		while (sr.next()) {
			Person p = (Person)sr.get(0);
			System.out.println(p.getLastName() + ", " + p.getFirstName());
		}
		
	}
	
	private static void listaPuntos(Session sesion) {
		int totalPuntosOtorgados = 0;
		ScrollableResults sr = sesion.createQuery("from HousePoints as hopo where hopo.personByReceiver.firstName like ?1 and hopo.personByReceiver.lastName like ?2 or hopo.personByReceiver.firstName like ?3 and hopo.personByReceiver.lastName like ?4 or hopo.personByReceiver.firstName like ?5 and hopo.personByReceiver.lastName like ?6" ).setParameter(1, "Harry").setParameter(2, "Potter").setParameter(3,"Ron").setParameter(4,"Weasley").setParameter(5,"Hermione").setParameter(6,"Granger") .scroll();
				
		System.out.println();
		System.out.println("Ejercicio03");
		while(sr.next()) {
			HousePoints hopo = (HousePoints) sr.get(0);
			Person otorgaPuntos = hopo.getPersonByGiver();
			Person receptorDePuntos = hopo.getPersonByReceiver();
			int puntos = hopo.getPoints();
			System.out.println(otorgaPuntos.getFirstName() + " " + otorgaPuntos.getLastName() 
			                 + " -> " + puntos + " puntos "
			                 + "para " + receptorDePuntos.getFirstName() + " " + receptorDePuntos.getLastName());
			totalPuntosOtorgados = totalPuntosOtorgados+puntos;
		}
		System.out.println("Puntos totales: " + totalPuntosOtorgados);
	} 
	
	private static void estudianPociones(Session sesion) {
		
	}

}


