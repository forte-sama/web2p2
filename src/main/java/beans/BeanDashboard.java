package beans;

import javafx.util.Pair;
import models.DetalleVenta;
import models.Producto;
import models.Venta;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.PieChartModel;
import wrappers.GestorDetalleVentas;
import wrappers.GestorProductos;
import wrappers.GestorVentas;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by forte on 29/09/16.
 */
@ManagedBean
@RequestScoped
public class BeanDashboard {
    public BeanDashboard() { }

    public PieChartModel getGraficoPie() {
        PieChartModel graficoPie = new PieChartModel();

        int totalVentas = GestorDetalleVentas.cantidadProductosVendidos();

        List<Producto> productos = GestorProductos.getAll();

        for(Producto p : productos) {
            double porcentaje = (GestorDetalleVentas.cantidadConsumida(p) / (double)totalVentas) * 100;
            graficoPie.set(p.getNombre(),porcentaje);
        }

        graficoPie.setTitle("Porcentaje de consumo de productos");
        graficoPie.setLegendPosition("w");

        return graficoPie;
    }

    public BarChartModel getGraficoBarra() {
        BarChartModel graficoBarra = new BarChartModel();

        List<Venta> ventas = GestorVentas.getAll();
        HashMap<String,Double> entries = new HashMap<>();

        ChartSeries sales = new ChartSeries();
        sales.setLabel("Ventas");

        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
        for(Venta v : ventas) {
            String fechaBeauty = formatter.format(v.getFecha());

            if(entries.containsKey(fechaBeauty)) {
                Double montoViejo = entries.get(fechaBeauty);

                entries.put(fechaBeauty,montoViejo + v.getMonto());
            }
            else {

                entries.put(fechaBeauty,v.getMonto());
            }
        }

        for(Map.Entry<String,Double> entry : entries.entrySet()) {
            sales.set(entry.getKey(),entry.getValue());
        }

        graficoBarra.addSeries(sales);
        graficoBarra.setTitle("Porcentaje de consumo de productos");
        graficoBarra.setLegendPosition("s");

        return graficoBarra;
    }
}
