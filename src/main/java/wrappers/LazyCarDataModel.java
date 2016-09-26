package wrappers;

/**
 * Created by forte on 25/09/16.
 */
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import models.Producto;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 * Dummy implementation of LazyDataModel that uses a list to mimic a real datasource like a database.
 */
public class LazyCarDataModel extends LazyDataModel<Producto> {

    private List<Producto> datasource;

    public LazyCarDataModel(List<Producto> datasource) {
        this.datasource = datasource;
    }
    public LazyCarDataModel() {}

    @Override
    public Producto getRowData(String rowKey) {
        Long id = Long.parseLong(rowKey);

        return GestorProductos.getById(id);
    }

    @Override
    public Object getRowKey(Producto producto) {
        return producto.getId();
    }

    @Override
    public List<Producto> load(int first,
                               int pageSize,
                               String sortField,
                               SortOrder sortOrder,
                               Map<String,Object> filters) {

        setRowCount(GestorProductos.obtenerCantidadProductos().intValue());
        datasource = GestorProductos.getAll(first,pageSize);

        setPageSize(pageSize);

        return datasource;
    }
}
