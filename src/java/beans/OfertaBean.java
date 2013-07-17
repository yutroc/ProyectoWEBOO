/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import Controler.Controlador;
import DTO.ProductoDTO;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.html.HtmlDataTable;
import utils.DAOException;

/**
 *
 * @author Javier Andana
 */
@ManagedBean
@SessionScoped
public class OfertaBean {

    private ArrayList<ProductoDTO> productos =null;  
    private HtmlDataTable tablaProductos;
     private Controlador controller = new Controlador();
     private ProductoDTO productoSeleccionado;
     private boolean t=true;
     
     int idProducto;
      String nombreProducto;
      int precio ;
      int valorOferta;
      boolean  activada;
      String nameProducto="";  
    public OfertaBean() {
      
    }

    public ArrayList<ProductoDTO> getProductos() throws DAOException {
        productos=controller.obtenerTodosProductos(nameProducto);
        for(ProductoDTO p :productos ){
            System.out.println(p.toString());
        }
      return productos;
    }

    public void setProductos(ArrayList<ProductoDTO> productos) {
        this.productos = productos;
    }

    public HtmlDataTable getTablaProductos() {
        return tablaProductos;
    }

    public void setTablaProductos(HtmlDataTable tablaProductos) {
        this.tablaProductos = tablaProductos;
    }

    public boolean isT() {
        return t;
    }

    public void setT(boolean t) {
        this.t = t;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getValorOferta() {
        return valorOferta;
    }

    public void setValorOferta(int valorOferta) {
        this.valorOferta = valorOferta;
    }

    public boolean isActivada() {
        return activada;
    }

    public void setActivada(boolean activada) {
        this.activada = activada;
    }

    public String getNameProducto() {
        return nameProducto;
    }

    public void setNameProducto(String nameProducto) {
        this.nameProducto = nameProducto;
    }
    
    public String seleccionarProducto(){
         
        productoSeleccionado = (ProductoDTO) tablaProductos.getRowData();
       


        this.idProducto = productoSeleccionado.getIdProducto();
        this.nombreProducto = productoSeleccionado.getNombre();
        this.precio = productoSeleccionado.getPrecio();
        this.valorOferta=productoSeleccionado.getValorOferta();
        this.activada=productoSeleccionado.isOfertaActiva();
        return "EditarOferta";
    }
    
    public String actualizarOferta() throws DAOException, SQLException{
        ProductoDTO p=new ProductoDTO(idProducto,nombreProducto,precio,valorOferta,activada);
         controller.actualizarOferta(p);
         return "MantenedorOfertas.xhtml";
    }
}
