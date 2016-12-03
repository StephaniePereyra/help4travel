/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.cure.servidor.web;

import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import uy.edu.cure.servidor.central.dto.Factura;
import uy.edu.cure.servidor.central.dto.Promocion;
import uy.edu.cure.servidor.central.dto.Proveedor;
import uy.edu.cure.servidor.central.dto.Reserva;
import uy.edu.cure.servidor.central.dto.Servicio;
import uy.edu.cure.servidor.central.lib.jeringa.JeringaInjector;
import uy.edu.cure.servidor.central.soap.client.FacturaWS;
import uy.edu.cure.servidor.central.soap.client.FacturaWSImplService;
import uy.edu.cure.servidor.central.soap.client.ItemsFactura;
import uy.edu.cure.servidor.central.soap.client.ReservaWS;
import uy.edu.cure.servidor.central.soap.client.ReservaWSImplService;
import uy.edu.cure.servidor.central.soap.client.UsuarioWS;
import uy.edu.cure.servidor.central.soap.client.UsuarioWSImplService;

/**
 *
 * @author JuanD
 */
@ManagedBean
@ViewScoped
public class ProveedorMovil {

    @ManagedProperty(value = "#{datosSesion}")
    private DatosSesion datosSesion;
    private UsuarioWSImplService usuarioWSImplService;
    private UsuarioWS portUsuario;
    private ReservaWSImplService reservaWSImplService;
    private ReservaWS portReserva;
    private Converter convertidor;
    private String nickName;
    private Proveedor proveedor;
    private List<Servicio> servicios;
    private List<Promocion> promociones;
    private List<Reserva> reservas;
    private boolean vacioServicio;
    private boolean vacioPromocion;
    private boolean vacioReserva;
    private double precio = 0;
    
    public ProveedorMovil() throws MalformedURLException {
        try {
            convertidor = new Converter();
            usuarioWSImplService = new UsuarioWSImplService(new URL("http://localhost:8080/servidor-central-webapp/soap/UsuarioWSImplService?wsdl"));
            reservaWSImplService = new ReservaWSImplService(new URL("http://localhost:8080/servidor-central-webapp/soap/ReservaWSImplService?wsdl"));
            JeringaInjector.getInstance().inyectar(this);
        } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
        }
        portUsuario = usuarioWSImplService.getUsuarioWSImplPort();
        portReserva = reservaWSImplService.getReservaWSImplPort();
        promociones = new ArrayList();
        servicios = new ArrayList();
        reservas = new ArrayList();

    }

    @PostConstruct
    public void verServicioPromocion() {

        setNickName(datosSesion.getNickName());
        proveedor = convertidor.convertirProveedor(portUsuario.obtenerProveedorWS(nickName));
        this.servicios = proveedor.getServicios();

        List<uy.edu.cure.servidor.central.soap.client.Promocion> auxiliar = portUsuario.obtenerPormoProveedor(nickName);

        for (int i = 0; i < auxiliar.size(); i++) {
            promociones.add(convertidor.convertirPromocion(auxiliar.get(i)));
        }

        List<uy.edu.cure.servidor.central.soap.client.Reserva> auxReservas = portReserva.obtenerResevasProveedor(nickName);

        for (int i = 0; i < auxReservas.size(); i++) {
            reservas.add(convertidor.convertirReserva(auxReservas.get(i)));
        }

        if (servicios.isEmpty()) {
            vacioServicio = true;
        }

        if (promociones.isEmpty()) {
            vacioPromocion = true;
        }

        if (reservas.isEmpty()) {
            vacioReserva = true;
        }

    }

    public List<Servicio> obtenerServicios(Reserva reserva) {
        List<Servicio> serviciosAux = new ArrayList<Servicio>();
        for (int i = 0; i < reserva.getServicios().size(); i++) {
            if ((reserva.getServicios().get(i).getProveedor().getNickName()).equals(nickName)) {
                serviciosAux.add(reserva.getServicios().get(i));
                precio = precio + reserva.getServicios().get(i).getPrecio() * reserva.getCantidadServicios().get(i);
            }
        }
        return serviciosAux;
    }

    public List<Promocion> obtenerPromociones(Reserva reserva) {
        List<Promocion> promocionesAux = new ArrayList<Promocion>();
        for (int i = 0; i < reserva.getPromociones().size(); i++) {
            if ((reserva.getPromociones().get(i).getProveedor().getNickName()).equals(nickName)) {
                promocionesAux.add(reserva.getPromociones().get(i));
                precio = precio + reserva.getPromociones().get(i).getPrecioTotal() * reserva.getCantidadPromociones().get(i);
            }
        }
        return promocionesAux;
    }

    public int cantidadServicio(Reserva reserva, Servicio servicio) {
        int cantidad = 1;
        int posicion = reserva.getServicios().indexOf(servicio);
        cantidad = reserva.getCantidadServicios().get(posicion);
        
        return cantidad;
    }

    public int cantidadPromocion(Reserva reserva, Promocion promocion) {
        int cantidad = 1;
        int posicion = reserva.getPromociones().indexOf(promocion);
        cantidad = reserva.getCantidadPromociones().get(posicion);
        return cantidad;
    }
    
    public double precioReserva(){
        double total = 0;
        total = precio;
        this.precio = 0.0;
        return total;
    }
    
    public void recibePago(int numeroReserva) {
        portReserva.recibirPagoWS(numeroReserva, nickName);
        if (portReserva.obtenerReservaWS(numeroReserva).getEstado().equals("Facturada")) {
            FacturaWSImplService facturaWSImplService = null;
            try {
                facturaWSImplService = new FacturaWSImplService(new URL("http://localhost:8080/servidor-central-webapp/soap/FacturaWSImplService?wsdl"));
            } catch (MalformedURLException ex) {
                Logger.getLogger(ProveedorMovil.class.getName()).log(Level.SEVERE, null, ex);
            }
            FacturaWS portFactura = facturaWSImplService.getFacturaWSImplPort();
            List<ItemsFactura> items = new ArrayList();
            Factura facturaAuxiliar = new Factura();
            List<uy.edu.cure.servidor.central.soap.client.Servicio> serviciosReserva = portReserva.obtenerServiciosReservaWS(numeroReserva);
            List<uy.edu.cure.servidor.central.soap.client.Promocion> promocionesReserva = portReserva.obtenerPromocionesReservaWS(numeroReserva);

            for (int i = 0; i < serviciosReserva.size(); i++) {
                ItemsFactura item = new ItemsFactura();
                item.setCantidadItem(portReserva.obtenerReservaWS(numeroReserva).getCantidadServicios().get(i));
                item.setNickProveedor(serviciosReserva.get(i).getProveedor().getNickName());
                item.setNombreItem(serviciosReserva.get(i).getNombre());
                item.setTipoItem("servicio");
                items.add(item);
            }
            for (int u = 0; u < promocionesReserva.size(); u++) {
                ItemsFactura item = new ItemsFactura();
                item.setCantidadItem(portReserva.obtenerReservaWS(numeroReserva).getCantidadPromociones().get(u));
                item.setNickProveedor(promocionesReserva.get(u).getProveedor().getNickName());
                item.setNombreItem(promocionesReserva.get(u).getNombre());
                item.setTipoItem("promocion");
                items.add(item);
            }
            portFactura.persistirFactura(portReserva.obtenerReservaWS(numeroReserva).getCliente().getNickName(), numeroReserva, items);
            //
            uy.edu.cure.servidor.central.soap.client.Reserva reserva = portReserva.obtenerReservaWS(numeroReserva);
            List<uy.edu.cure.servidor.central.soap.client.Servicio> serviciosAux = portReserva.obtenerServiciosReservaWS(numeroReserva);
            List<uy.edu.cure.servidor.central.soap.client.Promocion> promocionesAux = portReserva.obtenerPromocionesReservaWS(numeroReserva);
            String correo = reserva.getCliente().getCorreo();
            String asunto = "H4T su compra ha sido facturada con exito";
            String cuerpo = "Estimado " + reserva.getCliente().getNickName() + " su compra ha sido realizada con exito. \n---Detalles de su compra:\n";
            if (serviciosAux.isEmpty()) {
                cuerpo = cuerpo + "-No tiene servicios contratados\n";
            } else {
                cuerpo = cuerpo + "-Servicios:\n";
                for (int i = 0; i < serviciosAux.size(); i++) {
                    uy.edu.cure.servidor.central.soap.client.Servicio servicio = serviciosAux.get(i);
                    int cantidad = reserva.getCantidadServicios().get(i);
                    cuerpo = cuerpo + "Nombre: " + servicio.getNombre() + " -Cantidad: " + cantidad + " -Precio: $"
                            + servicio.getPrecio() * cantidad + " -Proveedor: " + servicio.getProveedor().getNickName() + "\n";
                }
            }
            if (promocionesAux.isEmpty()) {
                cuerpo = cuerpo + "-No tiene promociones contratadas\n";
            } else {
                cuerpo = cuerpo + "-Promociones:\n";
                for (int i = 0; i < promocionesAux.size(); i++) {
                    uy.edu.cure.servidor.central.soap.client.Promocion promocion = promocionesAux.get(i);
                    int cantidad = reserva.getCantidadPromociones().get(i);
                    cuerpo = cuerpo + "Nombre: " + promocion.getNombre() + " -Cantidad: " + cantidad + " -Precio: $"
                            + promocion.getPrecioTotal() * cantidad + " -Proveedor: " + promocion.getProveedor().getNickName() + "\n";
                }
            }
            cuerpo = cuerpo + "Precio total: " + reserva.getPrecio() + "\n";
            cuerpo = cuerpo + "Gracias por su preferencia\n";
            cuerpo = cuerpo + "Saludos les envia el equipo de Help4Travel\n";
            EnviadorMail enviadorMail = new EnviadorMail(correo, asunto, cuerpo);
            //
        }
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public List<Servicio> getServicios() {
        return servicios;
    }

    public void setServicios(List<Servicio> servicios) {
        this.servicios = servicios;
    }

    public List<Promocion> getPromociones() {
        return promociones;
    }

    public void setPromociones(List<Promocion> promociones) {
        this.promociones = promociones;
    }

    public boolean isVacioServicio() {
        return vacioServicio;
    }

    public void setVacioServicio(boolean vacioServicio) {
        this.vacioServicio = vacioServicio;
    }

    public boolean isVacioPromocion() {
        return vacioPromocion;
    }

    public void setVacioPromocion(boolean vacioPromocion) {
        this.vacioPromocion = vacioPromocion;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {

        this.reservas = reservas;
    }

    public boolean isVacioReserva() {
        return vacioReserva;
    }

    public void setVacioReserva(boolean vacioReserva) {
        this.vacioReserva = vacioReserva;
    }

    public DatosSesion getDatosSesion() {
        return datosSesion;
    }

    public void setDatosSesion(DatosSesion datosSesion) {
        this.datosSesion = datosSesion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
    
}
