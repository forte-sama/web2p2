package models;

import org.hibernate.annotations.Cascade;
import wrappers.GestorDetalleVentas;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by forte on 28/09/16.
 */
@Entity
public class Venta implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    Long id;
    Date fecha;
    Double monto;
    @ManyToOne(fetch = FetchType.EAGER)
    Usuario usuario;

    public Venta() { }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Date getFecha() {
        return fecha;
    }
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    public Double getMonto() {
        return monto;
    }
    public void setMonto(Double monto) {
        this.monto = monto;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
