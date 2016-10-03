/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.cure.servidor.web;


import java.lang.reflect.InvocationTargetException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import uy.edu.cure.servidor.central.lib.UsuarioController;
import uy.edu.cure.servidor.central.lib.jeringa.Jeringa;
import uy.edu.cure.servidor.central.lib.jeringa.JeringaInjector;

/**
 *
 * @author guido
 */
@ManagedBean
@SessionScoped
public class DatosCliente {
    
private MensajeDatosUsuario datosusuarioMnsj = new MensajeDatosUsuario();
private String nickName,nombre,apellido,correo,ruta,mensaje,mensajeDefault = "*No pueden existir campos vacios*";
private int dia,mes,anio;
private boolean mostrarMensaje = false;
@Jeringa (value = "usuariocontroller")
private UsuarioController usuariocontroller;


    public DatosCliente(){
        try {
            JeringaInjector.getInstance().inyectar(this);
        } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public boolean isMostrarMensaje() {
        return mostrarMensaje;
    }

    public void setMostrarMensaje(boolean mostrarMensaje) {
        this.mostrarMensaje = mostrarMensaje;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
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
    int resultado = usuariocontroller.crearCliente(nickName, nombre, apellido, correo, dia, mes, anio, apellido);
    
    mostrarMensaje = true;
    if(nickName.equals("") || nombre.equals("") || apellido.equals("") || correo.equals("")){
        mensaje = mensajeDefault;
    }else{
        mensaje = datosusuarioMnsj.retornoMensajeUsuario(resultado);
    }
    

}

}
