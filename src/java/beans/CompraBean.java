/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import Controler.Controlador;
import DTO.CompraDTO;
import DTO.ProductoDTO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.html.HtmlDataTable;
import utils.DAOException;

/**
 *
 * @author Sebastian
 */
@ManagedBean
@RequestScoped
public class CompraBean {

    public Controlador controller = new Controlador();
    //atributos
    private int idCarro; // este deberia ser serial, automatico
    private String idUsuario;
    private int estado;
    private Date fechaCreacion;
    private Date fechaFinalizacion;
    private ArrayList<CompraDTO> compras = null;
    private HtmlDataTable tablaCompras;
    private CompraDTO compraSeleccionada;

    /**
     * Creates a new instance of CompraBean
     */
    public CompraBean() {
    }

    public ArrayList<CompraDTO> getCompras() throws DAOException {
        if (compras == null) {
            compras = controller.obtenerTodasCompras();
        }
        return compras;
    }

    public void seleccionCompra() {
        compraSeleccionada = (CompraDTO) tablaCompras.getRowData();
        this.idCarro = compraSeleccionada.getIdCarro();
        this.idUsuario = compraSeleccionada.getIdUsuario();
        this.estado = compraSeleccionada.getEstado();
        this.fechaCreacion = compraSeleccionada.getFechaCreacion();
        this.fechaFinalizacion = compraSeleccionada.getFechaFinalizacion();
    }

    public HtmlDataTable getTablaCompras() {
        return tablaCompras;
    }

    public void setTablaCompras(HtmlDataTable tablaCompras) {
        this.tablaCompras = tablaCompras;
    }

    public CompraDTO getCompraSeleccionada() {
        return compraSeleccionada;
    }

    public void setCompraSeleccionada(CompraDTO compraSeleccionada) {
        this.compraSeleccionada = compraSeleccionada;
    }

    public Controlador getController() {
        return controller;
    }

    public void setController(Controlador controller) {
        this.controller = controller;
    }

    public int getIdCarro() {
        return idCarro;
    }

    public void setIdCarro(int idCarro) {
        this.idCarro = idCarro;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaFinalizacion() {
        return fechaFinalizacion;
    }

    public void setFechaFinalizacion(Date fechaFinalizacion) {
        this.fechaFinalizacion = fechaFinalizacion;
    }
    
     public void eliminarCompra() throws DAOException, SQLException {
        compraSeleccionada = (CompraDTO) tablaCompras.getRowData();
        this.idCarro = compraSeleccionada.getIdCarro();
        this.idUsuario = compraSeleccionada.getIdUsuario();
        this.estado = compraSeleccionada.getEstado();
        this.fechaCreacion = compraSeleccionada.getFechaCreacion();
        this.fechaFinalizacion = compraSeleccionada.getFechaFinalizacion();
        CompraDTO c = new CompraDTO(idCarro, idUsuario, estado, fechaCreacion, fechaFinalizacion);
        controller.eliminarCompra(c);
        //return "Categoria";
    }
}
