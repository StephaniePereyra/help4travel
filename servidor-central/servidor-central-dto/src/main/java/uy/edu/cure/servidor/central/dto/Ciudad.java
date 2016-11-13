/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.cure.servidor.central.dto;

/**
 *
 * @author Rodrigo "Lobo Plateado" Pérez
 */
public class Ciudad {

    private String nombre;
    private Pais pais;

    public Ciudad(String nombre, Pais pais) {
        this.nombre = nombre;
        this.pais = pais;
    }
    
    public Ciudad (){
        this.nombre = "";
        this.pais = null;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

}
