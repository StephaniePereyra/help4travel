/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.cure.servidor.central.dto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rodrigo "Lobo Plateado" Pérez
 */
public class Pais {

    private String nombre;
    private List<Ciudad> ciudades;

    public Pais(String nombre) {
        this.nombre = nombre;
        ciudades = new ArrayList<Ciudad>();
    }
    
    public Pais(){
        this.nombre = "";
        ciudades = new ArrayList<Ciudad>();        
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Ciudad> getCiudades() {
        return ciudades;
    }

    public void setCiudades(Ciudad ciudad) {
        ciudades.add(ciudad);
    }

}
