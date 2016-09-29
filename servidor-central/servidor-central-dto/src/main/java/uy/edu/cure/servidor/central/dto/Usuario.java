/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.cure.servidor.central.dto;

import java.util.*;

/**
 *
 * @author guido
 */
public class Usuario {

    private String nickName;
    private String nombre;
    private String apellido;
    private String correo;
    private Date fechanacimiento;

    public Usuario(String nickName, String nombre, String apellido, String correo, Date fechanacimiento) {
        this.nickName = nickName;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.fechanacimiento = fechanacimiento;
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

    public Date getFechanacimiento() {
        return fechanacimiento;
    }

    public void setFechanacimiento(Date fechanacimiento) {
        this.fechanacimiento = fechanacimiento;
    }

}
