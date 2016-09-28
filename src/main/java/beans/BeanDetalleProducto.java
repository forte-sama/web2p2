package beans;

import com.sun.faces.context.flash.ELFlash;
import models.Producto;
import models.Review;
import org.primefaces.model.UploadedFile;
import wrappers.GestorDetalleVentas;
import wrappers.GestorProductos;
import wrappers.GestorReviews;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.event.ComponentSystemEvent;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by forte on 22/09/16.
 */
@ManagedBean
@SessionScoped
public class BeanDetalleProducto {
    @ManagedProperty(value="#{beanSesion}")
    BeanSesion beanSesion;

    //producto mostrado
    Producto productoMostrado;

    //comentario
    Integer rating;
    String nuevoComentario;

    public BeanDetalleProducto() { }

    public void validarReview(ComponentSystemEvent event) {
        FacesContext fc = FacesContext.getCurrentInstance();

        if(GestorReviews.yaHaPublicado(productoMostrado,beanSesion.getUsuario())) {
            FacesMessage message = new FacesMessage("Accion no completada.", "Ya publicaste para este producto");
            message.setSeverity(FacesMessage.SEVERITY_WARN);
            fc.addMessage(null, message);
        }

        fc.renderResponse();
    }

    public String mostrarDetallesProducto(Producto p) {
        productoMostrado = p;

        return "detallesProducto";
    }

    public String agregarReview() {

        Review nuevoReview = new Review();
        nuevoReview.setProducto(productoMostrado);
        nuevoReview.setUsuario(beanSesion.getUsuario());
        nuevoReview.setTexto(nuevoComentario);
        nuevoReview.setRating(rating);

        GestorReviews.guardar(nuevoReview);

        nuevoComentario = null;
        rating = null;

        Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
        //Funciona como un mapa.
        flash.putNow("mensaje", "Reseña agregada exitosamente.");
        //Otra forma directa.
        ELFlash.getFlash().put("otroForma", "Funciona");

        return "detallesProducto?faces-redirect=true";
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
    public Integer getRating() {
        return rating;
    }
    public void setRating(Integer rating) {
        this.rating = rating;
    }
    public void setBeanSesion(BeanSesion beanSesion) {
        this.beanSesion = beanSesion;
    }
    public boolean getPuedePublicar() {
        if (beanSesion.getUsuario() == null || productoMostrado == null) {
            return false;
        }
        else {
            return GestorDetalleVentas.loHaComprado(productoMostrado, beanSesion.getUsuario());
        }
    }
    public List<Review> getReviews() {
        return GestorReviews.getByProduct(productoMostrado);
    }
}
