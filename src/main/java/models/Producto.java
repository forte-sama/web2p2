package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.awt.image.BufferedImage;

/**
 * Created by forte on 22/09/16.
 */
@Entity
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    Long id;
    String nombre;
    String descripcion;
    Long cantidad;
    byte[] imagen;

    public Producto() { id = null; }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public byte[] getImagen() {
        return imagen;
    }
    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }
    public Long getCantidad() {
        return cantidad;
    }
    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }
}
