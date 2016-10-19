/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.cure.servidor.web;


import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import uy.edu.cure.servidor.central.lib.UsuarioControllerImpl;
import uy.edu.cure.servidor.central.lib.jeringa.Jeringa;
import uy.edu.cure.servidor.central.lib.jeringa.JeringaInjector;

/**
 *
 * @author guido
 */
@ManagedBean
@ViewScoped
public class DatosCliente {
    
private MensajeDatosUsuario datosusuarioMnsj = new MensajeDatosUsuario();
private String nickName,nombre,apellido,correo,ruta,passWord,passWordConfirm,mensaje,mensajeDefault = "*No pueden existir campos vacios*";
private int dia,mes,anio;
private boolean mostrarMensaje = false;
@Jeringa (value = "usuariocontroller")
private UsuarioControllerImpl usuariocontroller;
private List<Integer> dias,meses,anios;


    public DatosCliente(){
        try {
            JeringaInjector.getInstance().inyectar(this);
        } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
        }
        dias = new ArrayList();
        meses = new ArrayList();
        anios = new ArrayList();
        for(int i =1;i<32;i++){
          dias.add(i);
        }
         for(int i =1;i<13;i++){
          meses.add(i);
        }
         for(int i =1900;i<2017;i++){
          anios.add(i);
        } 
    }    
    
    public String getPassWordConfirm() {
        return passWordConfirm;
    }

    public void setPassWordConfirm(String passWordConfirm) {
        this.passWordConfirm = passWordConfirm;
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

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public MensajeDatosUsuario getDatosusuarioMnsj() {
        return datosusuarioMnsj;
    }

    public void setDatosusuarioMnsj(MensajeDatosUsuario datosusuarioMnsj) {
        this.datosusuarioMnsj = datosusuarioMnsj;
    }

    public String getMensajeDefault() {
        return mensajeDefault;
    }

    public void setMensajeDefault(String mensajeDefault) {
        this.mensajeDefault = mensajeDefault;
    }

    public UsuarioControllerImpl getUsuariocontroller() {
        return usuariocontroller;
    }

    public void setUsuariocontroller(UsuarioControllerImpl usuariocontroller) {
        this.usuariocontroller = usuariocontroller;
    }

    public List<Integer> getDias() {
        return dias;
    }

    public void setDias(List<Integer> dias) {
        this.dias = dias;
    }

    public List<Integer> getMeses() {
        return meses;
    }

    public void setMeses(List<Integer> meses) {
        this.meses = meses;
    }

    public List<Integer> getAnios() {
        return anios;
    }

    public void setAnios(List<Integer> anios) {
        this.anios = anios;
    }
    
    
public void action(){
    int resultado = usuariocontroller.crearCliente(nickName, nombre, apellido, correo, dia, mes, anio, apellido,passWord,passWordConfirm);
    mostrarMensaje = true;
    if(nickName.equals("") || nombre.equals("") || apellido.equals("") || correo.equals("")){
        mensaje = mensajeDefault;
    }else{
        mensaje = datosusuarioMnsj.retornoMensajeUsuario(resultado);
    }
    

}

}
