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
public class Servicio {

    private String nombre;
    private String descripcion;
    private double precio;
    private Ciudad origen;
    private Ciudad destino;
    private List<String> imagenes;
    private List<Categoria> categorias;
    private Proveedor proveedor;
    private int cantidad;

    public Servicio(String nombre, String descripcion, double precio, Ciudad origen, Ciudad destino, Proveedor proveedor) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.origen = origen;
        this.destino = destino;
        this.proveedor = proveedor;
        categorias = new ArrayList<Categoria>();
        imagenes = new ArrayList<String>();
    }
    
    public Servicio(){
        this.nombre = "";
        this.descripcion = "";
        this.precio = 0;
        this.origen = null;
        this.destino = null;
        this.proveedor = null;
        categorias = new ArrayList<Categoria>();
        imagenes = new ArrayList<String>();
    }
    
    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Ciudad getOrigen() {
        return origen;
    }

    public void setOrigen(Ciudad origen) {
        this.origen = origen;
    }

    public Ciudad getDestino() {
        return destino;
    }

    public void setDestino(Ciudad destino) {
        this.destino = destino;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(Categoria categoria) {
        categorias.add(categoria);
    }

    public List<String> getImagenes() {
        return imagenes;
    }

    public void setImagen(String imagen) {
        imagenes.add(imagen);
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    

}
