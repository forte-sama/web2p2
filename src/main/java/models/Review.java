package models;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by forte on 28/09/16.
 */
@Entity
public class Review implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    Long id;
    String texto;
    Integer rating;
    @ManyToOne(fetch = FetchType.EAGER)
    Usuario usuario;
    @ManyToOne(fetch = FetchType.EAGER)
    Producto producto;

    public Review() { }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTexto() {
        return texto;
    }
    public void setTexto(String texto) {
        this.texto = texto;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public Producto getProducto() {
        return producto;
    }
    public void setProducto(Producto producto) {
        this.producto = producto;
    }
    public Integer getRating() {
        return rating;
    }
    public void setRating(Integer rating) {
        this.rating = rating;
    }
}
