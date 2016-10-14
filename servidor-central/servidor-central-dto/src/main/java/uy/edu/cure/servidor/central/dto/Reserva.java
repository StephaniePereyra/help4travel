/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.cure.servidor.central.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author juan
 */
public class Reserva {
    private static int increment = 0;
    private int numero;
    private Date fechaCreacion;
    private double precio;
    private String estado;
    private Cliente cliente;
    private List<Promocion> promociones;
    private List<Servicio> servicios;

    public Reserva(Date fechaCreacion, double precio, String estado, List<Promocion> promociones, List<Servicio> servicios) {
        this.numero = ++increment;
        this.fechaCreacion = fechaCreacion;
        this.precio = precio;
        this.estado = estado;
        this.promociones = promociones;
        this.servicios = servicios;
    } 
    

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<Promocion> getPromociones() {
        return promociones;
    }

    public void setPromociones(ArrayList<Promocion> promociones) {
        this.promociones = promociones;
    }

    public List<Servicio> getServicios() {
        return servicios;
    }

    public void setServicios(ArrayList<Servicio> servicios) {
        this.servicios = servicios;
    }
    
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    public void setServicio(Servicio servicio) {
        this.servicios.add(servicio);
    }
    
    public void setPromocion(Promocion promocion) {
        this.promociones.add(promocion);
    }
}
