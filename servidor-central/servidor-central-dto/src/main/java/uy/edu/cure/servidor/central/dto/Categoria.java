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
 * @author guido
 */
public class Categoria {

    private String nombre;
    private Categoria padre;
    private List<Categoria> hijos;

    public Categoria(String nombre) {
        this.nombre = nombre;
        this.padre = null;
        this.hijos = new ArrayList<Categoria>();

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Categoria getPadre() {
        return padre;
    }

    public void setPadre(Categoria padre) {
        this.padre = padre;
    }

    public List<Categoria> getHijos() {
        return hijos;
    }

    public void setHijos(Categoria hijo) {
        hijos.add(hijo);
    }

}
