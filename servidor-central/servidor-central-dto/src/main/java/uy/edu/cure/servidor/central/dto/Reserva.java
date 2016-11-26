/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.cure.servidor.central.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
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
    private List<ValidacionPago> pagos;
    private List<Promocion> promociones;
    private List<Servicio> servicios;
    private List<Integer> cantidadPromociones;
    private List<Integer> cantidadServicios;

    public Reserva(Date fechaCreacion, double precio, String estado, List<Promocion> promociones, List<Servicio> servicios) {
        this.numero = ++increment;
        this.fechaCreacion = fechaCreacion;
        this.precio = precio;
        this.estado = estado;
        this.promociones = promociones;
        this.servicios = servicios;
        this.pagos = new ArrayList();
        Iterator<Servicio> iteratorServicios = servicios.iterator();
        while(iteratorServicios.hasNext()) {
            Servicio servicioAux = iteratorServicios.next();
            if(!proveedorInPagos(servicioAux.getProveedor().getNickName())) {
                ValidacionPago pago = new ValidacionPago(servicioAux.getProveedor().getNickName());
                setPagos(pago);
            }
        }
        Iterator<Promocion> iteratorPromociones = promociones.iterator();
        while(iteratorPromociones.hasNext()) {
            Promocion promocionAux = iteratorPromociones.next();
            if(!proveedorInPagos(promocionAux.getProveedor().getNickName())) {
                ValidacionPago pago = new ValidacionPago(promocionAux.getProveedor().getNickName());
                setPagos(pago);
            }
        }
    } 
    
    public Reserva(){
        this.numero = ++increment;
        this.fechaCreacion = new Date();
        this.precio = 0.0;
        this.estado = "creado";
        this.pagos = new ArrayList();
        this.promociones = new ArrayList<Promocion>();
        this.servicios = new ArrayList<Servicio>();
        this.cantidadPromociones = new ArrayList<Integer>();
        this.cantidadServicios = new ArrayList<Integer>();
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
    
    //
    public List<ValidacionPago> getPagos() {
        return pagos;
    }

    public void setPagos(ValidacionPago pago) {
        pagos.add(pago);
    }
    
    public boolean proveedorInPagos(String nickProveedor) {
        Iterator<ValidacionPago> iteratorPagos = pagos.iterator();
        while(iteratorPagos.hasNext()) {
            ValidacionPago pagoAux = iteratorPagos.next();
            if(pagoAux.getNickProveedor().equals(nickProveedor)){
                return true;
            }
        }
        return false;
    }
    
    public boolean proveedorIsPago(String nickProveedor) {
        Iterator<ValidacionPago> iteratorPagos = pagos.iterator();
        while(iteratorPagos.hasNext()) {
            ValidacionPago pagoAux = iteratorPagos.next();
            if(pagoAux.getNickProveedor().equals(nickProveedor) && pagoAux.isPago()){
                return true;
            }
        }
        return false;
    }
    
    public boolean isAllPago() {
        Iterator<ValidacionPago> iteratorPagos = pagos.iterator();
        while(iteratorPagos.hasNext()) {
            ValidacionPago pagoAux = iteratorPagos.next();
            if(!pagoAux.isPago()){
                return false;
            }
        }
        return true;
    }
    //
    
    public void setServicio(Servicio servicio) {
        this.servicios.add(servicio);
    }
    
    public void setPromocion(Promocion promocion) {
        this.promociones.add(promocion);
    }

    public List<Integer> getCantidadPromociones() {
        return cantidadPromociones;
    }

    public void setCantidadPromociones(List<Integer> cantidadPromociones) {
        this.cantidadPromociones = cantidadPromociones;
    }

    public List<Integer> getCantidadServicios() {
        return cantidadServicios;
    }

    public void setCantidadServicios(List<Integer> cantidadServicios) {
        this.cantidadServicios = cantidadServicios;
    }
    
}
