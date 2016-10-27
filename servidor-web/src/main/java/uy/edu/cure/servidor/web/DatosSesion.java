/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.cure.servidor.web;

import java.lang.reflect.InvocationTargetException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import uy.edu.cure.servidor.central.dto.Usuario;
import uy.edu.cure.servidor.central.lib.UsuarioControllerImpl;
import uy.edu.cure.servidor.central.lib.jeringa.Jeringa;
import uy.edu.cure.servidor.central.lib.jeringa.JeringaInjector;

/**
 *
 * @author SCN
 */
@ManagedBean
@SessionScoped
public class DatosSesion {

    @Jeringa(value="usuariocontroller")
    UsuarioControllerImpl usuariocontroller;
    private String passWord, nickName,mnsjError;
    private boolean mostrarError,loged;
    private Usuario usuario;

    public DatosSesion() {
                try {
            JeringaInjector.getInstance().inyectar(this);
        } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
        }
   
        mostrarError = false;
        nickName = "";
        passWord = "";
    }

    public String logIn() {

        boolean resultadoCliente = usuariocontroller.LogInCliente(nickName, passWord);
        String retorno;
        if (resultadoCliente) {
            loged = true;
            usuario = usuariocontroller.obtenerCliente(nickName);
            retorno = "index.xhtml";
        } else {
            mostrarError = true;
            loged = false;
            mnsjError = "*NickName o Password incorrectas*";
            retorno = "";
        }
        return retorno;
    }
    
    public String logOut(){
        loged = false;
        nickName = "";
        usuario = null;
        return "LogIn.xhtml";
    }
    
   /* public String actionLog (){
        String retorno;
        if(loged){
            logOut();
            retorno = "index.xhtml";
        }else{
            retorno = "LogIn.xhtml";
        }
        return retorno;
    }*/

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public boolean isMostrarError() {
        return mostrarError;
    }

    public void setMostrarError(boolean mostrarError) {
        this.mostrarError = mostrarError;
    }

    public String getMnsjError() {
        return mnsjError;
    }

    public void setMnsjError(String mnsjError) {
        this.mnsjError = mnsjError;
    }

    public boolean isLoged() {
        return loged;
    }

    public void setLoged(boolean loged) {
        this.loged = loged;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
}
