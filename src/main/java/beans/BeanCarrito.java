package beans;

import models.DetalleVenta;
import models.Producto;
import models.Usuario;
import models.Venta;
import org.hibernate.tuple.entity.EntityMetamodel;
import wrappers.GestorDetalleVentas;
import wrappers.GestorProductos;
import wrappers.GestorUsuarios;
import wrappers.GestorVentas;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import java.util.*;

/**
 * Created by forte on 26/09/16.
 */
@ManagedBean
@SessionScoped
public class BeanCarrito {
    Producto actual;
    Long cantidad;
    HashMap<Producto,Long> productos;
    @ManagedProperty(value = "#{beanSesion}")
    private BeanSesion beanSesion;

    public BeanCarrito() {
        productos = new HashMap<>();
    }

    public String agregarACarrito() {
        if(actual != null) {
            //hacer toda
            productos.put(actual,cantidad);

            cantidad = null;
            actual = null;
        }

        return "productosDisponibles";
    }

    public String quitarDeCarrito(Producto target) {

        if(productos.containsKey(target)) {
            productos.remove(target);
        }

        return "carrito";
    }

    public String formCarrito(Producto p) {
        actual = p;

        return "agregarProducto";
    }

    public String comprar() {
        //crear instancia de nueva venta
        Venta nuevaVenta = new Venta();
        nuevaVenta.setFecha(new Date());
        nuevaVenta.setUsuario(beanSesion.getUsuario());
        //crear lista de detalles para la venta
        List<DetalleVenta> detalles = new ArrayList<>();

        Double total = 0D;
        //reducir existencia en cada producto y guardar detalle de venta para cada producto
        for(Map.Entry<Producto,Long> entry : productos.entrySet()) {
            Producto producto = entry.getKey();
            Long cant = entry.getValue();
            //reducir existencia y persistir
            GestorProductos.reduceExistence(producto,cant);
            //crear nuevo detalle de venta
            DetalleVenta nuevoDV = new DetalleVenta();
            nuevoDV.setProducto(producto);
            nuevoDV.setCantidad(cant);
            nuevoDV.setVenta(nuevaVenta);
            detalles.add(nuevoDV);
            //actualizar monto
            total += producto.getPrecio() * cant;
        }

        //asignar monto total
        nuevaVenta.setMonto(total);
        //persistir nueva venta
        GestorVentas.guardar(nuevaVenta);

        //guardar detalles de la venta
        for(DetalleVenta dv : detalles) {
            GestorDetalleVentas.guardar(dv);
        }

        limpiarDatos();

        return "productosDisponibles?faces-redirect=true";
    }

    private void limpiarDatos() {
        actual = null;
        cantidad = null;
        productos = new HashMap<>();
    }

    public void validarExistencia(ComponentSystemEvent event) {

        UIComponent components = event.getComponent();

        //get email value from component
        UIInput productoIdInput = (UIInput)components.findComponent("productoIdHidden");
        String rawProductoId = productoIdInput.getLocalValue() == null ? "" : productoIdInput.getLocalValue().toString();

        //get password value from component
        UIInput cantidadInput = (UIInput)components.findComponent("inputCantidad");
        String rawCantidad = cantidadInput.getLocalValue() == null ? "" : cantidadInput.getLocalValue().toString();

        Producto found;
        try {
            found = GestorProductos.getById(Long.parseLong(rawProductoId));
        } catch (NumberFormatException e) {
            found = null;
        }

        if(found == null) {
            System.out.println("NO SE ENCUENTRA");
            //no encontro nada
            FacesMessage message = new FacesMessage("Fallo.",
                    "No se encontro producto");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);

            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null,message);

            fc.renderResponse();
        }
        else {
            Long cantidad;
            try {
                cantidad = Long.parseLong(rawCantidad);
            } catch (NumberFormatException e) {
                cantidad = 0L;
            }

            if(found.getCantidad() < cantidad) {
                System.out.println("NO HAY EXISTENCIA SUFICIENTE PARA ID:" + found.getId());
                //no hay suficiente
                FacesMessage message = new FacesMessage("Fallo en operacion.",
                        "No hay existencia suficiente");
                message.setSeverity(FacesMessage.SEVERITY_WARN);

                FacesContext fc = FacesContext.getCurrentInstance();
                fc.addMessage(null,message);

                fc.renderResponse();
            }
        }
    }

    public void validarCarrito(ComponentSystemEvent event) {
        boolean fail = false;

        FacesContext fc = FacesContext.getCurrentInstance();

        //validar existencia suficiente para cada producto
        for(Map.Entry<Producto,Long> entry : productos.entrySet()) {
            Long cantidadItem = GestorProductos.getCantidadById(entry.getKey().getId());
            cantidadItem = cantidadItem == null ? 0L : cantidadItem;

            if(entry.getValue() > cantidadItem) {
                FacesMessage message = new FacesMessage("Fallo en operacion.",
                        "No hay suficientes " + entry.getKey().getNombre());
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                fc.addMessage(null,message);
            }
        }

        fc.renderResponse();
    }

    public Producto getActual() {
        return actual;
    }
    public void setActual(Producto actual) {
        this.actual = actual;
    }
    public Long getCantidad() {
        return cantidad;
    }
    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }
    public List<Map.Entry<Producto, Long>> getProductos() {
        Set<Map.Entry<Producto, Long>> productSet = productos.entrySet();

        return new ArrayList<>(productSet);
    }
    public void setProductos(HashMap<Producto, Long> productos) {
        this.productos = productos;
    }
    public void setBeanSesion(BeanSesion beanSesion) {
        this.beanSesion = beanSesion;
    }
}
