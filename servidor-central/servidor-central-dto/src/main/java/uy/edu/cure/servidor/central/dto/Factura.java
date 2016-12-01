/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.cure.servidor.central.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.sun.istack.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author guido
 */
@Entity
@Table (name = "facturas")
@NamedQueries(value = { @NamedQuery(name = "getFacturaXnumeroReserva", query = "select f from Factura f where numeroReserva = :numeroReserva") })
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
public class Factura {

@Id
@GeneratedValue (strategy = GenerationType.SEQUENCE)
private int id;

@NotNull
private String nickCliente;

private Date fecha;

@NotNull
private int numeroReserva;

@OneToMany(mappedBy = "factura", fetch = FetchType.EAGER)
private List<ItemsFactura> items;

public Factura (){
    fecha = new Date();
}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickCliente() {
        return nickCliente;
    }

    public void setNickCliente(String nickCliente) {
        this.nickCliente = nickCliente;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getNumeroReserva() {
        return numeroReserva;
    }

    public void setNumeroReserva(int numeroReserva) {
        this.numeroReserva = numeroReserva;
    }
    
    public List<ItemsFactura> getItems() {
        return items;
    }

    public void setItems(List<ItemsFactura> items) {
        this.items = items;
    }


}
