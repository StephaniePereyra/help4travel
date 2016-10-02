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
 * @author SCN
 */
@ManagedBean
@SessionScoped
public class DatosProveedor {
    private MensajeDatosUsuario datosusuarioMnsj = new MensajeDatosUsuario();
    private String nickName,nombre,apellido,correo,nombreEmpresa,linkEmpresa,mensaje,mensajeDefault = "*No pueden existir campos vacios*";
    private int dia,mes,anio;
    private boolean mostrarMensaje;
    private UsuarioController usuariocontroller = new UsuarioController();

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

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getLinkEmpresa() {
        return linkEmpresa;
    }

    public void setLinkEmpresa(String linkEmpresa) {
        this.linkEmpresa = linkEmpresa;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensajeDefault() {
        return mensajeDefault;
    }

    public void setMensajeDefault(String mensajeDefault) {
        this.mensajeDefault = mensajeDefault;
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

    public MensajeDatosUsuario getDatosusuarioMnsj() {
        return datosusuarioMnsj;
    }

    public void setDatosusuarioMnsj(MensajeDatosUsuario datosusuarioMnsj) {
        this.datosusuarioMnsj = datosusuarioMnsj;
    }

    public boolean isMostrarMensaje() {
        return mostrarMensaje;
    }

    public void setMostrarMensaje(boolean mostrarMensaje) {
        this.mostrarMensaje = mostrarMensaje;
    }

    public UsuarioController getUsuariocontroller() {
        return usuariocontroller;
    }

    public void setUsuariocontroller(UsuarioController usuariocontroller) {
        this.usuariocontroller = usuariocontroller;
    }
    
    public void action() {
        int resultado = usuariocontroller.crearProveedor(nickName, nombre, apellido, correo, dia, mes, anio, nombreEmpresa, linkEmpresa, "");
        mostrarMensaje = true;
        if (nickName.equals("") || nombre.equals("") || apellido.equals("") || correo.equals("") || nombreEmpresa.equals("") || linkEmpresa.equals("")) {
            mensaje = mensajeDefault;
        } else {
            mensaje = datosusuarioMnsj.retornoMensajeUsuario(resultado);
        }
    }

}
