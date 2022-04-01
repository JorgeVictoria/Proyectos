package temario;

/*Clase que permite escribir en un archivo de texto*/

//Importamos clases que se usaran
import java.io.File;
import java.io.FileWriter;

public class Escribir
{
public static void main(String []args)
{

//Un texto cualquiera guardado en una variable
String saludo="Hola";

try
{
//Crear un objeto File se encarga de crear o abrir acceso a un archivo que se especifica en su constructor
File archivo=new File("texto.txt");

//Crear objeto FileWriter que sera el que nos ayude a escribir sobre archivo
FileWriter escribir=new FileWriter(archivo,true);

//Escribimos en el archivo con el metodo write
escribir.write(saludo);

//Cerramos la conexion
escribir.close();
}

//Si existe un problema al escribir cae aqui
catch(Exception e)
{
System.out.println("Error al escribir");
}
}
}