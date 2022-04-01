package org.sfaci.gestionanimales.util;

import javax.swing.*;

/**
 * Helper class
 *
 * Clase con métodos de utilidades genéricas para la aplicación
 *
 * @author Santiago Faci
 * @version curso 2015-2016
 */
public class Util {

    public static void mensajeError(String mensaje, String titulo) {
        JOptionPane.showMessageDialog(null, mensaje, titulo, JOptionPane.ERROR_MESSAGE);
    }

    public static void mensajeInformacion(String mensaje, String titulo) {
        JOptionPane.showMessageDialog(null, mensaje, titulo, JOptionPane.INFORMATION_MESSAGE);
    }
}
