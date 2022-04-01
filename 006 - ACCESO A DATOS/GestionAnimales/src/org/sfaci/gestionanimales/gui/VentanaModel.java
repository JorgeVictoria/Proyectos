package org.sfaci.gestionanimales.gui;

import org.sfaci.gestionanimales.base.Animal;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
import java.io.File;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.w3c.dom.*;

/**
 * Modelo para la ventana
 * @author Santiago Faci
 * @curso 2015-2016
 */
public class VentanaModel {

    private ArrayList<Animal> listaAnimales;
    private int posicion;
    private static Element raiz;
    private static Document documento;

    public VentanaModel() {
        listaAnimales = new ArrayList<>();
        posicion = 0;
    }

    /**
     * Guarda un animal en la lista
     * @param animal
     */
    public void guardar(Animal animal) {

        listaAnimales.add(animal);
        posicion++;
    }

    /**
     * Modifica los datos del animal actual
     * @param animalModificado
     */
    public void modificar(Animal animalModificado) {

        Animal animal = listaAnimales.get(posicion);
        animal.setNombre(animalModificado.getNombre());
        animal.setCaracteristicas(animalModificado.getCaracteristicas());
        animal.setRaza(animalModificado.getRaza());
        animal.setPeso(animalModificado.getPeso());
    }

    /**
     * Elimina el animal actual
     */
    public void eliminar() {
        listaAnimales.remove(posicion);
    }

    public Animal getActual() {

        return listaAnimales.get(posicion);
    }

    /**
     * Busca un animal en la lista
     * @param nombre El nombre del animal
     * @return El animal o null si no se ha encontrado nada
     */
    public Animal buscar(String nombre) {
        for (Animal animal : listaAnimales) {
            if (animal.getNombre().equalsIgnoreCase(nombre)) {
                return animal;
            }
        }

        return null;
    }

    /**
     * Obtiene el animal que está en primera posición en la lista
     * @return
     */
    public Animal getPrimero() {

        posicion = 0;
        return listaAnimales.get(posicion);
    }

    /**
     * Obtiene el animal que está en la posición anterior a la actual
     * @return
     */
    public Animal getAnterior() {

        if (posicion == 0)
            return null;

        posicion--;
        return listaAnimales.get(posicion);
    }

    /**
     * Obtiene el animal que está en la posición siguiente a la actual
     * @return
     */
    public Animal getSiguiente() {

        if (posicion == listaAnimales.size() - 1)
            return null;

        posicion++;
        return listaAnimales.get(posicion);
    }

    /**
     * Obtiene el animal que está en la última posición de la lista
     * @return
     */
    public Animal getUltimo() {

        posicion = listaAnimales.size() - 1;
        return listaAnimales.get(posicion);
    }

    /**
     * metodo pasa los objetos a un fichero XML
     */
    public void toXML() {

        try {

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            //root element
            Element rootElement = doc.createElement("ANIMALS");
            doc.appendChild(rootElement);

            //corremos el array list con los objetos y vamos creando los tags
            if(listaAnimales.size() > 0) {

                for(int i = 0; i < listaAnimales.size();i++){
                    Element animal = doc.createElement("animal");
                    rootElement.appendChild(animal);
                    Element nombre = doc.createElement("Nombre");
                    nombre.appendChild((doc.createTextNode(listaAnimales.get(i).getNombre())));
                    animal.appendChild(nombre);
                    Element raza = doc.createElement("Raza");
                    raza.appendChild((doc.createTextNode(listaAnimales.get(i).getRaza())));
                    animal.appendChild(raza);
                    Element caracteristicas = doc.createElement("Caracteristicas");
                    caracteristicas.appendChild((doc.createTextNode(listaAnimales.get(i).getCaracteristicas())));
                    animal.appendChild(caracteristicas);
                    Element peso = doc.createElement("Peso");
                    String pesoString = String.valueOf(listaAnimales.get(i).getPeso());
                    peso.appendChild((doc.createTextNode(pesoString)));
                    animal.appendChild(peso);
                }

                // escribimos el contenido en un fichero XML
                //llamada a la funcion para coger el fichero donde queremos guardar
                File file = guardarComo();
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                DOMSource source = new DOMSource(doc);
                StreamResult result = new StreamResult(new File(String.valueOf(file)));
                transformer.transform(source, result);
            }

        } catch(Exception e){
            System.err.print("Error: " + e.getMessage());
        }
    }

    /**
     * metodo para cargar en el programa datos a partir de un fichero xml
     */
    public void toObject(){

        try{

            File file = buscarFile();
            //Crear una factoria que permita usar un parser:
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

            //Crear un builder que permite crear documentos DOM usando un parser
            documento = builder.parse(new File(String.valueOf(file)));

            //los nodos de texto adyacentes los fusiona
            documento.getDocumentElement().normalize();

            //crear una lista con todos los nodos Character
            NodeList animalitos = documento.getElementsByTagName("animal");

            //recorremos los diferentes
            for (int i = 0; i < animalitos.getLength(); i++) {
                //Creamos un objeto nodo con cada elemento Character
                Node animalito = animalitos.item(i);
                if (animalito.getNodeType() == Node.ELEMENT_NODE) {
                    Element elemento = (Element) animalito;
                    //Creamos un nuevo objeto animal
                    Animal unAnimal = new Animal();
                    unAnimal.setNombre(((Element) animalito).getElementsByTagName("Nombre").item(0).getTextContent());
                    unAnimal.setRaza(((Element) animalito).getElementsByTagName("Raza").item(0).getTextContent());
                    unAnimal.setCaracteristicas(((Element) animalito).getElementsByTagName("Caracteristicas").item(0).getTextContent());
                    unAnimal.setPeso(Float.parseFloat(((Element) animalito).getElementsByTagName("Peso").item(0).getTextContent()));
                    //añadimos el animal al array
                    listaAnimales.add(i,unAnimal);
                }

            }

        } catch(Exception e){
            System.err.print("Error: " + e.getMessage());
        }

    }

    //metodo para buscar y filtrar un fichero
    private File buscarFile() {

        Scanner entrada = null;
        File f = null;
        //Se crea el JFileChooser. Se le indica que la ventana se abra en el directorio actual
        JFileChooser fileChooser = new JFileChooser(".");
        //Se crea el filtro. El primer parámetro es el mensaje que se muestra,
        //el segundo es la extensión de los ficheros que se van a mostrar
        FileFilter filtro = new FileNameExtensionFilter("archivos XML (.xml)" , "xml");
        //Se le asigna al JFileChooser el filtro
        fileChooser.setFileFilter(filtro);
        //se muestra la ventana
        int valor = fileChooser.showOpenDialog(fileChooser);
        if (valor == JFileChooser.APPROVE_OPTION) {
            String ruta = fileChooser.getSelectedFile().getAbsolutePath();
            try {
                f = new File(ruta);
                entrada = new Scanner(f);
                while (entrada.hasNext()) {
                    System.out.println(entrada.nextLine());
                }
            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            } finally {
                if (entrada != null) {
                    entrada.close();
                }
            }
        } else {
            System.out.println("No se ha seleccionado ningún fichero");
        }

        entrada.close();
        return f;
    }

    /**
     * fichero para seleccionar el fichero donde queremos guardar los datos
     * @return archivo, que es un File
     */
    private File guardarComo(){

        //creamos el filtro para indicar el tipo de fichero que guardamos
        FileFilter filtro = new FileNameExtensionFilter("archivos XML (.xml)" , "xml");
        //creamos el jfileChoser
        JFileChooser guardar = new JFileChooser();
        //al jfilechooser le pasamos el filtro que queremos aplicar
        guardar.setFileFilter(filtro);
        //mostramos la ventana
        guardar.showSaveDialog(null);
        guardar.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

        //creamos un File donde almacenamos el fichero creado/seleccionado
        File archivo = guardar.getSelectedFile();

        //puede ocurrir que el fichero se guarde sin extension. se la añadimos
        if(!archivo.getName().contains(".")) archivo = new File(archivo.toString() + ".xml");

        //devolvemos el fichero
        return archivo;

    }

}
