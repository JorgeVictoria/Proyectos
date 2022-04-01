package org.sfaci.gestionanimales.gui;

import org.sfaci.gestionanimales.base.Animal;
import org.sfaci.gestionanimales.util.Util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static javax.swing.JOptionPane.showMessageDialog;

/**
 * Controlador para la ventana
 * @author Santiago Faci
 * @version curso 2015-2016
 */
public class VentanaController implements ActionListener, KeyListener {

    private VentanaModel model;
    private Ventana view;

    private int posicion;

    public VentanaController(VentanaModel model, Ventana view) {
        this.model = model;
        this.view = view;
        anadirActionListener(this);
        anadirKeyListener(this);

        posicion = 0;
    }

    @Override
    public void actionPerformed(ActionEvent event) {

        String actionCommand = event.getActionCommand();
        Animal animal = null;

        switch (actionCommand) {
            case "Nuevo":
                view.tfNombre.setText("");
                view.tfNombre.setEditable(true);
                view.tfCaracteristicas.setText("");
                view.tfCaracteristicas.setEditable(true);
                view.tfPeso.setText("");
                view.tfPeso.setEditable(true);
                view.tfRaza.setText("");
                view.tfRaza.setEditable(true);

                view.btGuardar.setEnabled(true);

                break;
            case "Guardar":

                //tenemos que ver que los campos Nombre y Raza tengan contenido
                if (view.tfNombre.getText().equals("")) {
                    Util.mensajeError("El nombre es un campo obligatorio", "Nuevo Animal");
                    return;
                }

                if (view.tfRaza.getText().equals("")) {
                    Util.mensajeError("La raza es un campo obligatorio", "Nuevo Animal");
                    return;
                }

                //llamamos a la funcion para ver que el peso sea un numero
                if(esNumero(view.tfPeso.getText())) {
                    animal = new Animal();
                    animal.setNombre(view.tfNombre.getText());
                    animal.setRaza(view.tfRaza.getText());
                    animal.setCaracteristicas(view.tfCaracteristicas.getText());
                    animal.setPeso(Float.parseFloat(view.tfPeso.getText()));
                    model.guardar(animal);

                    view.btGuardar.setEnabled(false);
                    //como ya tenemos datos, podemos pasar a xml
                    view.xmlButton.setEnabled(true);
                }
                break;
            case "Modificar":
                //llamamos a la funcion para ver que el peso sea un numero
                if(esNumero(view.tfPeso.getText())) {
                    animal = new Animal();
                    animal.setNombre(view.tfNombre.getText());
                    animal.setRaza(view.tfRaza.getText());
                    animal.setCaracteristicas(view.tfCaracteristicas.getText());
                    animal.setPeso(Float.parseFloat(view.tfPeso.getText()));
                    model.modificar(animal);
                    view.btModificar.setEnabled(false);
                }
                break;
            case "Cancelar":
                view.tfNombre.setEditable(false);
                view.tfCaracteristicas.setEditable(false);
                view.tfPeso.setEditable(false);
                view.tfRaza.setEditable(false);

                animal = model.getActual();
                cargar(animal);

                view.btGuardar.setEnabled(false);
                break;
            case "Eliminar":
                if (JOptionPane.showConfirmDialog(null, "¿Está seguro?", "Eliminar", JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION)
                    return;

                model.eliminar();
                animal = model.getActual();
                cargar(animal);
                break;
            case "Buscar":
                animal = model.buscar(view.tfBusqueda.getText());
                if (animal == null) {
                    Util.mensajeInformacion("No se ha encontrado ningún animal con ese nombre", "Buscar");
                    return;
                }
                cargar(animal);
                view.btModificar.setEnabled(true);
                //activamos los campos
                view.tfNombre.setEditable(true);
                view.tfRaza.setEditable(true);
                view.tfCaracteristicas.setEditable(true);
                view.tfPeso.setEditable(true);
                break;
            case "Primero":
                animal = model.getPrimero();
                cargar(animal);
                break;
            case "Anterior":
                animal = model.getAnterior();
                cargar(animal);
                break;
            case "Siguiente":
                animal = model.getSiguiente();
                cargar(animal);
                break;
            case "Ultimo":
                animal = model.getUltimo();
                cargar(animal);
                break;
            case "PasarAXML":
                model.toXML();
            case "PasarAClass":
                model.toObject();
                view.xmlButton.setEnabled(true);
            default:
                break;
        }
    }

    /**
    * metodo para controlar que el campo peso sea numerico
     */
    private boolean esNumero(String unNumero) {

        float number;
        boolean correcto;

        try{
            correcto = true;
            number = Float.parseFloat(unNumero);
        } catch (Exception e) {
            showMessageDialog(null, "El valor del peso debe ser numerico");
            correcto = false;
        }

        return correcto;

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

        if (e.getSource() == view.tfBusqueda) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                view.btBuscar.doClick();
            }
        }
    }

    /**
     * Carga los datos de un animal en la vista
     * @param animal
     */
    private void cargar(Animal animal) {
        if (animal == null)
            return;

        view.tfNombre.setText(animal.getNombre());
        view.tfCaracteristicas.setText(animal.getCaracteristicas());
        view.tfRaza.setText(animal.getRaza());
        view.tfPeso.setText(String.valueOf(animal.getPeso()));
    }

    private void anadirKeyListener(KeyListener listener) {
        view.tfBusqueda.addKeyListener(listener);
    }

    private void anadirActionListener(ActionListener listener) {

        view.btNuevo.addActionListener(listener);
        view.btGuardar.addActionListener(listener);
        view.btModificar.addActionListener(listener);
        view.btEliminar.addActionListener(listener);
        view.btPrimero.addActionListener(listener);
        view.btAnterior.addActionListener(listener);
        view.btSiguiente.addActionListener(listener);
        view.btUltimo.addActionListener(listener);
        view.btBuscar.addActionListener(listener);
        view.ClassBtn.addActionListener(listener);
        view.xmlButton.addActionListener(listener);
    }
}
