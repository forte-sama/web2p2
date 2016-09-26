package beans;

import models.Producto;
import org.primefaces.model.UploadedFile;
import wrappers.GestorProductos;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

/**
 * Created by forte on 22/09/16.
 */
@ManagedBean
@RequestScoped
public class BeanDetalleProducto {
    //producto mostrado
    Producto productoMostrado;

    //comentario
    String nuevoComentario;

    public BeanDetalleProducto() { }

    public String mostrarDetallesProducto(Producto p) {
        productoMostrado = p;

        return "detallesProducto";
    }

    public String agregarReview(Producto producto) {

        return null;
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
