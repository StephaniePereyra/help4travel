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
import uy.edu.cure.servidor.central.lib.UsuarioController;
import uy.edu.cure.servidor.central.lib.jeringa.Jeringa;
import uy.edu.cure.servidor.central.lib.jeringa.JeringaInjector;

/**
 *
 * @author guido
 */
@ManagedBean
@SessionScoped
public class DatosUser {
    
    private String nickName, passWord, mnsjError;
    @Jeringa(value = "usuariocontroller")
    private UsuarioController usuariocontroller;
    private boolean mostrarError = false;
    private Usuario usuario;
    
    @ManagedProperty(value = "#{loginBean}")
    private LoginBean loginBean;
    
    public DatosUser() {
        try {
            JeringaInjector.getInstance().inyectar(this);
        } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
        }
        nickName = "";
    }
    
    public String action() {
        
        boolean resultadoCliente = usuariocontroller.LogInCliente(nickName, passWord);
        boolean resultadoProveedor = usuariocontroller.LogInProveedor(nickName, passWord);
        String retorno;
        
        if (resultadoCliente || resultadoProveedor) {
            loginBean.setLoggedIn(true);
            retorno = "/secured/isLoggedIn";
        } else {
            
            mostrarError = true;
            mnsjError = "*NickName o Password incorrectas*";
            retorno = "LogIn";
        }
        if (resultadoCliente == true) {
            usuario = usuariocontroller.obtenerProveedor(this.nickName);
        }
        return retorno;
    }
    
     public UsuarioController getUsuariocontroller() {
        return usuariocontroller;
    }
    
    public void setUsuariocontroller(UsuarioController usuariocontroller) {
        this.usuariocontroller = usuariocontroller;
    }
    
    public Usuario getUsuario() {
        return usuario;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public String getNickName() {
        return nickName;
    }
    
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    
    public String getPassWord() {
        return passWord;
    }
    
    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
    
    public String getMnsjError() {
        return mnsjError;
    }
    
    public void setMnsjError(String mnsjError) {
        this.mnsjError = mnsjError;
    }
    
    public boolean isMostrarError() {
        return mostrarError;
    }
    
    public void setMostrarError(boolean mostrarError) {
        this.mostrarError = mostrarError;
    }

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }
}
