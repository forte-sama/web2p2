package beans;

import models.Producto;
import org.primefaces.model.UploadedFile;
import wrappers.GestorProductos;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

/**
 * Created by forte on 22/09/16.
 */
@ManagedBean
@ViewScoped
public class BeanDetalleProducto {
    //producto mostrado
    Long idProductoMostrado;
    Producto productoMostrado;

    //comentario
    String nuevoComentario;

    public BeanDetalleProducto() { }

    public String detalleProducto(Producto p) {
        productoMostrado = p;

        return "detallesProducto";
    }
    public String agregarReview(Producto producto) {

        return null;
    }

    public Long getIdProductoMostrado() {
        return idProductoMostrado;
    }

    public void setIdProductoMostrado(Long idProductoMostrado) {
        this.idProductoMostrado = idProductoMostrado;
    }

    public Producto getProductoMostrado() {
        return productoMostrado;
    }

    public void setProductoMostrado(Producto productoMostrado) {
        this.productoMostrado = productoMostrado;
    }

    public String getNuevoComentario() {
        return nuevoComentario;
    }

    public void setNuevoComentario(String nuevoComentario) {
        this.nuevoComentario = nuevoComentario;
    }
}
