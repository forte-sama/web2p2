package beans;

import models.DetalleVenta;
import models.Producto;
import wrappers.GestorDetalleVentas;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.List;

/**
 * Created by forte on 29/09/16.
 */
@ManagedBean
@RequestScoped
public class BeanHistorial {
    List<DetalleVenta> ventas;

    public BeanHistorial() { }

    public String mostrarHistorial(Producto p) {
        ventas = GestorDetalleVentas.historialVentas(p);

        return "historial";
    }

    public List<DetalleVenta> getVentas() {
        return ventas;
    }
    public void setVentas(List<DetalleVenta> ventas) {
        this.ventas = ventas;
    }
}
