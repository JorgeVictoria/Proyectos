package org.sfaci.gestionanimales.gui;

import javax.swing.*;

/**
 * Componente swing que representa una Barra de Estado
 *
 * @author Santiago Faci
 * @version curso 2015-2016
 */
public class JBarraEstado {
    private JPanel panel1;
    private JLabel lbMensaje;
    private JLabel lbEstado;

    public JBarraEstado() {

    }

    /**
     * Fija el mensaje de la barra de estado
     * @param mensaje
     */
    public void setMensaje(String mensaje) {
        lbMensaje.setText(mensaje);
    }

    /**
     * Fija el estado de la barra de estado
     * @param mensaje
     */
    public void setEstado(String mensaje) {
        lbEstado.setText(mensaje);
    }
}
