/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.cure.servidor.central.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author guido
 */
public class Proveedor extends Usuario {

    private String nombreEmpresa;
    private String linkEmpresa;
    @XmlTransient
    private List<Servicio> servicios;
    private String imagenPerfil;

    public Proveedor(String nickName, String nombre, String apellido, String correo, Date fechanacimiento, String nombreEmpresa, String linkEmpresa, String imagenPerfil,String passWord) {
        super(nickName, nombre, apellido, correo, fechanacimiento,passWord);
        this.nombreEmpresa = nombreEmpresa;
        this.linkEmpresa = linkEmpresa;
        servicios = new ArrayList<Servicio>();
        this.imagenPerfil = imagenPerfil;

    }
    
    public Proveedor (){
        this.nombreEmpresa = "";
        this.linkEmpresa = "";
        servicios = new ArrayList<Servicio>();
        this.imagenPerfil = "";
    }

    public String getImagenPerfil() {
        return imagenPerfil;
    }

    public void setImagenPerfil(String imagenPerfil) {
        this.imagenPerfil = imagenPerfil;
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

    public void setServicios(List<Servicio> servicios) {
        this.servicios.addAll(servicios);
    }

    public void setServicio(Servicio servicio) {
        this.servicios.add(servicio);
    }

    @XmlTransient
    public List<Servicio> getServicios() {
        return servicios;
    }

}
