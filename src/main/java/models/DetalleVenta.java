package models;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by forte on 28/09/16.
 */
@Entity
public class DetalleVenta implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    Long id;
    Long cantidad;
    @OneToOne
    Producto producto;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    Venta venta;

    public DetalleVenta() { }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Producto getProducto() {
        return producto;
    }
    public void setProducto(Producto producto) {
        this.producto = producto;
    }
    public Long getCantidad() {
        return cantidad;
    }
    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }
    public Venta getVenta() {
        return venta;
    }
    public void setVenta(Venta venta) {
        this.venta = venta;
    }
}