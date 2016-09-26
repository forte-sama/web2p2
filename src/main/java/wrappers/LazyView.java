package wrappers;

import models.Producto;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.util.List;

/**
 * Created by forte on 25/09/16.
 */
@ManagedBean
@ViewScoped
public class LazyView {
    private LazyDataModel<Producto> lazyModel = null;
    private List<Producto> productos;
    private Producto selectedProd;

    public void onRowSelect(SelectEvent event) {
        selectedProd = (Producto)event.getObject();
        FacesMessage msg = new FacesMessage("Producto Seleccionado", selectedProd.getId() + "");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public LazyDataModel<Producto> getLazyModel() {
        if(lazyModel == null) {
            lazyModel = new LazyCarDataModel();
        }

        return lazyModel;
    }

    public Producto getSelectedProd() {
        return selectedProd;
    }

    public void setSelectedProd(Producto selectedProd) {
        this.selectedProd = selectedProd;
    }

    public List<Producto> getProductos() {

        if(productos == null) {
            productos = GestorProductos.getAll();
        }

        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
}
