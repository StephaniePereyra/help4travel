/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.cure.servidor.web;

import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import uy.edu.cure.servidor.central.dto.Proveedor;
import uy.edu.cure.servidor.central.dto.Servicio;
import uy.edu.cure.servidor.central.lib.CiudadControllerImpl;
import uy.edu.cure.servidor.central.lib.PaisControllerImpl;
import uy.edu.cure.servidor.central.lib.ServicioControllerImpl;
import uy.edu.cure.servidor.central.lib.UsuarioControllerImpl;

/**
 *
 * @author SCN
 */
@ManagedBean
//@ViewScoped
@SessionScoped

public class DatosProveedor {

    private Proveedor proveedor;
    private ArrayList<Proveedor> proveedores;
    private ArrayList<Servicio> servicios;
    private String nickName;
    private String nombreServicio;
    private UsuarioControllerImpl usuariocontroller;
    private ServicioControllerImpl serviciocontroller;
    private PaisControllerImpl paiscontroller;
    private CiudadControllerImpl ciudadcontroller;

    public DatosProveedor() {
        if (usuariocontroller == null) {
            usuariocontroller = new UsuarioControllerImpl();
            usuariocontroller.crearProveedor("Sebita", "Sebastian", "Sosa", "sebasosa@gmail.com", 19, 8, 1986, "nombreEmpresa1 ", "www.empresa1.com", "hola.png", "1", "1");
            usuariocontroller.crearProveedor("Agus", "Agustin", "Orion", "agusorion@hotmail.com", 26, 6, 1981, "nombreEmpresa2 ", "empresa2.com ", "hola1.png", "12", "12");
            usuariocontroller.crearProveedor("Dami", "Damian", "Frascarelli", "damifrascarelli@hotmail.com", 26, 6, 1981, "nombreEmpresa3 ", "empresa3.com ", "hola2.png", "123", "123");
            usuariocontroller.crearProveedor("Guru", "Gaston", "Guruceaga", "elGuruReGroso@gmail.com", 19, 8, 1986, "nombreEmpresa4 ", "empresa4.com", "hola3.png", "1234", "1234");
            usuariocontroller.crearProveedor("Bati", "Javier", "Batistuta", "batistuta@gmail.com", 19, 8, 1986, "nombreEmpresa5 ", "empresa5.com", "hola4.png", "12345", "12345");
        }
        if (paiscontroller == null) {
            paiscontroller = new PaisControllerImpl();
            paiscontroller.crearPais("Uruguay");
        }
        if (ciudadcontroller == null) {
            ciudadcontroller = new CiudadControllerImpl();
            ciudadcontroller.crearCiudad("Artigas", "Uruguay");
            ciudadcontroller.crearCiudad("Canelones", "Uruguay");
            ciudadcontroller.crearCiudad("Melo", "Uruguay");
            ciudadcontroller.crearCiudad("Colonia de Sacramento", "Uruguay");
            ciudadcontroller.crearCiudad("Durazno", "Uruguay");
            ciudadcontroller.crearCiudad("Trinidad", "Uruguay");
            ciudadcontroller.crearCiudad("Florida", "Uruguay");
            ciudadcontroller.crearCiudad("Minas", "Uruguay");
            ciudadcontroller.crearCiudad("Maldonado", "Uruguay");
            ciudadcontroller.crearCiudad("Montevideo", "Uruguay");
            ciudadcontroller.crearCiudad("Paysandu", "Uruguay");
            ciudadcontroller.crearCiudad("Fray Bentos", "Uruguay");
            ciudadcontroller.crearCiudad("Rivera", "Uruguay");
            ciudadcontroller.crearCiudad("Salto", "Uruguay");
            ciudadcontroller.crearCiudad("San Jose de Mayo", "Uruguay");
            ciudadcontroller.crearCiudad("Mercedes", "Uruguay");
            ciudadcontroller.crearCiudad("Tacuarembo", "Uruguay");
            ciudadcontroller.crearCiudad("Treinta y Tres", "Uruguay");
            ciudadcontroller.crearCiudad("Rocha", "Uruguay");
        }

        if (serviciocontroller == null) {
            serviciocontroller = new ServicioControllerImpl();
            serviciocontroller.crearServicio("servicio1", "Malo", 100, "Artigas", "Rocha", new ArrayList<String>(), new ArrayList<String>(), "Sebita");
            serviciocontroller.crearServicio("servicio2", "Mediocre", 200, "Canelones", "Melo", new ArrayList<String>(), new ArrayList<String>(), "Agus");
            serviciocontroller.crearServicio("servicio3", "Regular", 300, "Melo", "Colonia de Sacramento", new ArrayList<String>(), new ArrayList<String>(), "Dami");
            serviciocontroller.crearServicio("servicio4", "Regular Bueno", 400, "Colonia de Sacramento", "Durazno", new ArrayList<String>(), new ArrayList<String>(), "Guru");
            serviciocontroller.crearServicio("servicio5", "Bueno Regular", 500, "Durazno", "Trinidad", new ArrayList<String>(), new ArrayList<String>(), "Bati");
            serviciocontroller.crearServicio("servicio6", "Bueno", 600, "Trinidad", "Florida", new ArrayList<String>(), new ArrayList<String>(), "Sebita");
            serviciocontroller.crearServicio("servicio7", "Bueno Muy Bueno", 700, "Florida", "Minas", new ArrayList<String>(), new ArrayList<String>(), "Agus");
            serviciocontroller.crearServicio("servicio8", "Muy Bueno Bueno", 800, "Minas", "Maldonado", new ArrayList<String>(), new ArrayList<String>(), "Dami");
            serviciocontroller.crearServicio("servicio9", "Muy Bueno Sote", 900, "Maldonado", "Montevideo", new ArrayList<String>(), new ArrayList<String>(), "Guru");
            serviciocontroller.crearServicio("servicio10", "Sote", 15000, "Montevideo", "Tacuarembo", new ArrayList<String>(), new ArrayList<String>(), "Sebita");
        }
        servicios = new ArrayList<>();
        proveedores = new ArrayList<>();
        proveedores.addAll(usuariocontroller.obtenerProveedores());
    }

    public void accionProveedor() {
        proveedor = usuariocontroller.obtenerProveedor(this.nickName);
    }

    public String getNombreServicio() {
        return nombreServicio;
    }

    public void setNombreServicio(String nombreServicio) {
        this.nombreServicio = nombreServicio;
    }

    public ArrayList<Proveedor> getProveedores() {
        return proveedores;
    }

    public void setProveedores(ArrayList<Proveedor> proveedores) {
        this.proveedores = proveedores;
    }

    public Proveedor getProveedor() {
        try {
            if (!this.proveedor.equals(null)) {
                proveedor = usuariocontroller.obtenerProveedor(this.nickName);
                return proveedor;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = null;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public ArrayList<Servicio> getServicios() {
         try {
            if (this.servicios.isEmpty()) {
                servicios.addAll(usuariocontroller.obtenerProveedor
(this.nickName).getServicios());
            }
            return servicios;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        
    }

    public ServicioControllerImpl getServiciocontroller() {
        return serviciocontroller;
    }

    public PaisControllerImpl getPaiscontroller() {
        return paiscontroller;
    }

    public CiudadControllerImpl getCiudadcontroller() {
        return ciudadcontroller;
    }

    public void setServicios(ArrayList<Servicio> servicios) {
        this.servicios = servicios;
    }

    public void setServiciocontroller(ServicioControllerImpl serviciocontroller) {
        this.serviciocontroller = serviciocontroller;
    }

    public void setPaiscontroller(PaisControllerImpl paiscontroller) {
        this.paiscontroller = paiscontroller;
    }

    public void setCiudadcontroller(CiudadControllerImpl ciudadcontroller) {
        this.ciudadcontroller = ciudadcontroller;
    }

    public void setUsuariocontroller(UsuarioControllerImpl usuariocontroller) {
        this.usuariocontroller = usuariocontroller;
    }

    public UsuarioControllerImpl getUsuariocontroller() {
        return usuariocontroller;
    }
}
