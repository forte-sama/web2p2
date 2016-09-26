package beans;

import models.Producto;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.UploadedFile;
import wrappers.GestorProductos;
import wrappers.LazyCarDataModel;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.awt.image.BufferedImage;
import java.util.List;

/**
 * Created by forte on 22/09/16.
 */
@ManagedBean
@SessionScoped
public class BeanProductos {
    Long id;
    String nombre;
    String descripcion;
    Long cantidad;
    UploadedFile imagenSubida;
    byte[] dataImagen;

    public BeanProductos() { }

    public String editarProducto(Producto producto) {
        Long providedId = producto.getId();

        if(providedId != null) {
            id = providedId;
            nombre = producto.getNombre();
            descripcion = producto.getDescripcion();
            cantidad = producto.getCantidad();
            //setear la imagen
        }

        return "registroProducto";
    }

    public String guardarProducto() {
        //si es diferente de null, es una modificacion
        //en caso contrario, es agregar producto nuevo
        Producto producto = new Producto();

        if(id != null) {
            producto.setId(id);
        }
        //setear atributos
        producto.setNombre(nombre);
        producto.setDescripcion(descripcion);
        producto.setCantidad(cantidad);
        //setear imagen

        GestorProductos.guardar(producto);
        limpiarDatos();

        return "productosDisponibles?faces-redirect=true";
    }

    private void limpiarDatos() {
        //igualaciones hechas este orden por el tipo de dato
        id = cantidad = null;
        nombre = descripcion = null;
        imagenSubida = null;
    }

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
    public Long getCantidad() {
        return cantidad;
    }
    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }
    public UploadedFile getImagenSubida() {
        return imagenSubida;
    }
    public void setImagenSubida(UploadedFile imagenSubida) {
        this.imagenSubida = imagenSubida;
    }

    public byte[] getDataImagen() {
        return dataImagen;
    }

    public void setDataImagen(byte[] dataImagen) {
        this.dataImagen = dataImagen;
    }
}
