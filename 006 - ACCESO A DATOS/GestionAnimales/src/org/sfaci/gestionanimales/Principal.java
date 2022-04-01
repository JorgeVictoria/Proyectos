package org.sfaci.gestionanimales;

import org.sfaci.gestionanimales.gui.Ventana;
import org.sfaci.gestionanimales.gui.VentanaController;
import org.sfaci.gestionanimales.gui.VentanaModel;

/**
 * Aplicación para la gestión de animales utilizando el patrón MVC
 *
 * Clase principal, que solamente lanza la aplicación
 *
 * @author Santiago Faci
 * @version 2015-2016
 */
public class Principal {

    public static void main(String args[]) {

        //creamos la ventana
        Ventana view = new Ventana();


        VentanaModel model = new VentanaModel();
        VentanaController controller = new VentanaController(model, view);
    }
}
