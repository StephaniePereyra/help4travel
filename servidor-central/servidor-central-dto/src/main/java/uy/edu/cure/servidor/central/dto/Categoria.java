/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.cure.servidor.central.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author guido
 */
//@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
public class Categoria {

    private String nombre;
    private String padre;
    private List<Categoria> hijos;

    public Categoria(String nombre) {
        this.nombre = nombre;
        this.padre = null;
        this.hijos = new ArrayList<Categoria>();

    }
    
    public Categoria (){
        this.nombre = UUID.randomUUID().toString();
        this.padre = null;
        this.hijos = new ArrayList<Categoria>();
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPadre() {
        return padre;
    }

    public void setPadre(String padre) {
        this.padre = padre;
    }

    public List<Categoria> getHijos() {
        return hijos;
    }
    
    public void setHijosdeAuno (Categoria hijo){
        this.hijos.add(hijo);
    }

    public void setHijos(List<Categoria> hijos) {
        this.hijos = hijos;
    }

}
