package beans;

import models.Producto;
import org.primefaces.model.UploadedFile;
import wrappers.GestorProductos;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import java.awt.image.BufferedImage;

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

    public BeanProductos() { }

    //TODO usar producto en lugar de ID cuando vista productosDisponibles este lista
//    public String editarProducto(Producto producto) {
    public String editarProducto(Long providedId) {
//        id = producto.getId();
//        nombre = producto.getNombre();
//        descripcion = producto.getDescripcion();
//        cantidad = producto.getCantidad();
//        imagen = producto.getImagen();
        if(providedId != null) {
            id = providedId;
            Producto producto = GestorProductos.getById(providedId);
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
}
