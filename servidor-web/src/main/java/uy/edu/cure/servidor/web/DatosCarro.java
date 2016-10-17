/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.cure.servidor.web;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import uy.edu.cure.servidor.central.dto.Promocion;
import uy.edu.cure.servidor.central.dto.Servicio;
import uy.edu.cure.servidor.central.lib.UsuarioControllerImpl;
import uy.edu.cure.servidor.central.lib.jeringa.Jeringa;
import uy.edu.cure.servidor.central.lib.jeringa.JeringaInjector;


/**
 *
 * @author SCN
 */
@ManagedBean
@RequestScoped
public class DatosCarro implements Serializable {
    
    @ManagedProperty(value = "#{datosUser}")
    private DatosUser datosuser;
    @Jeringa(value="usuariocontroller")
    private UsuarioControllerImpl usuariocontroller;
    private List<Servicio> servicios;
    private List<Promocion> promociones;
    private boolean carritoEmpty;
    private String nickSession;
    
    public DatosCarro (){
                try {
            JeringaInjector.getInstance().inyectar(this);
        } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    public List<Servicio> getServicios() {
        return servicios;
    }

    public void setServicios(List<Servicio> servicios) {
        this.servicios = servicios;
    }


    public boolean isCarritoEmpty() {
        return carritoEmpty;
    }

    public void setCarritoEmpty(boolean carritoEmpty) {
        this.carritoEmpty = carritoEmpty;
    }

    public String getNickSession() {
        return nickSession;
    }

    public void setNickSession(String nickSession) {
        this.nickSession = nickSession;
    }

    public DatosUser getDatosuser() {
        return datosuser;
    }

    public void setDatosuser(DatosUser datosuser) {
        this.datosuser = datosuser;
    }

    public UsuarioControllerImpl getUsuariocontroller() {
        return usuariocontroller;
    }

    public void setUsuariocontroller(UsuarioControllerImpl usuariocontroller) {
        this.usuariocontroller = usuariocontroller;
    }

    public List<Promocion> getPromociones() {
        return promociones;
    }

    public void setPromociones(List<Promocion> promociones) {
        this.promociones = promociones;
    }
    
    @PostConstruct
    public void cargarArray (){
        
        servicios = new ArrayList();
        promociones = new ArrayList();
        
        //nickSession = datosuser.getNickName();
        //servicios = usuariocontroller.obtenerCliente(nickSession).getCarrito().getServicios();
        //promociones = usuariocontroller.obtenerCliente(nickSession).getCarrito().getPromociones();
        
        Servicio s = new Servicio();
        Servicio s1 = new Servicio();
        s.setNombre("servicio1");s.setPrecio(999);s.setDescripcion("Es un servicio de prueba");
        s1.setNombre("servicio2");s1.setPrecio(1200);s.setDescripcion("Es un servicio de prueba x2");
        servicios.add(s);
        servicios.add(s1);
        
        Promocion p1 = new Promocion();
        Promocion p2 = new Promocion();
        p1.setNombre("SuperPromo");p1.setPrecioTotal(852);
        p2.setNombre("RESuperPromo");p2.setPrecioTotal(1525);
        promociones.add(p1);
        promociones.add(p2);
        
        if(servicios.size() == 0 && promociones.size() == 0){
            setCarritoEmpty(true);
        }
    }
    
    
}
