package org.sfaci.gestionanimales.base;

/**
 * Clase que representa a un Animal
 *
 * @author Santiago Faci
 * @version curso 2015-2016
 */
public class Animal {

    private String nombre;
    private String raza;
    private String caracteristicas;
    private float peso;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(String caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }
}
