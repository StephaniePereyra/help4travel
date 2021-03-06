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
 * @author guido
 */
public class Cliente extends Usuario {

    private String imagenPerfil;
    private List<Reserva> reservas;
    private Reserva carrito;
    
    public Cliente(String nickName, String nombre, String apellido, String correo, Date fechanacimiento, String imagenPerfil, String passWord) {
        super(nickName, nombre, apellido, correo, fechanacimiento, passWord);
        this.imagenPerfil = imagenPerfil;
        this.reservas = new ArrayList<Reserva>();
        this.carrito = new Reserva();
    }
    
    public Cliente(){
        this.imagenPerfil = "";
        reservas = new ArrayList();
        carrito = new Reserva();
    }

    public String getImagenPerfil() {
        return imagenPerfil;
    }

    public void setImagenPerfil(String imagenPerfil) {
        this.imagenPerfil = imagenPerfil;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(Reserva reserva) {
        this.reservas.add(reserva);
    }

    public Reserva getCarrito() {
        return carrito;
    }

    public void setCarrito(Reserva carrito) {
        this.carrito = carrito;
    }
    
}
