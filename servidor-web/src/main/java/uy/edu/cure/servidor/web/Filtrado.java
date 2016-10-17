/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.cure.servidor.web;

/**
 *
 * @author Rodrigo "Lobo Plateado" Pérez
 */
public class Filtrado {

    private String tipo;
    private String linkInfo;
    private String nombre;
    private String proveedor;
    private double precio;

    public Filtrado(String tipo, String nombre, String proveedor, double precio) {
        this.tipo = tipo;
        if(tipo.equals("Promocion")) {
            this.linkInfo = "InfoPromocion.xhtml";
        }
        this.nombre = nombre;
        this.proveedor = proveedor;
        this.precio = precio;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getLinkInfo() {
        return linkInfo;
    }

    public void setLinkInfo(String linkInfo) {
        this.linkInfo = linkInfo;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

}
