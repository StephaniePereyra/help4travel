/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.cure.servidor.web;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import uy.edu.cure.servidor.central.lib.UsuarioController;

/**
 *
 * @author guido
 */
@ManagedBean
@SessionScoped
public class DatosCliente {
    
    
private String nickName,nombre,apellido,correo,ruta,mensaje;
private int dia,mes,anio;
private boolean mensajeboolean = false;

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public boolean isMensajeboolean() {
        return mensajeboolean;
    }

    public void setMensajeboolean(boolean mensajeboolean) {
        this.mensajeboolean = mensajeboolean;
    }



    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

public void action(){
    UsuarioController usuariocontroller = new UsuarioController();
    int resultado = usuariocontroller.crearCliente(nickName, nombre, apellido, correo, dia, mes, anio, apellido);
    
    if(resultado == 1){
        this.setMensajeboolean(true);
        this.setMensaje("NickName Ya existe");
    }
    if(resultado == -1){
        this.setMensajeboolean(true);
        this.setMensaje("SE CREO CORRECTAMENTE");
    }
}

}
