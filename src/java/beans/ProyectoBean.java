/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import DTO.ProductoDTO;
import Controler.Controlador;
import DTO.UsuarioDTO;
import java.sql.SQLException;
import javax.annotation.PostConstruct;
import javax.faces.component.html.HtmlDataTable;
import utils.DAOException;

/**
 *
 * @author Javier Andana
 */
@ManagedBean
@RequestScoped
public class ProyectoBean {

    public Controlador controller = new Controlador();
    private ArrayList<ProductoDTO> productos = null;
    //variables para seleecionar un producto
    private ProductoDTO productoSeleccionado;
    private ArrayList<ProductoDTO> productosSimilares;

    private int idProducto;
    private String nombreProducto;
    private String descripcionProducto;
    //variables para el login
    private String nombreUser = "";
    private String pass = "";
    private String msg = "";
    private HtmlDataTable tablaProductos;
    //variables para crear un producto
    private int idPN;
    private String nombrePN;
     private String descripcionNP;
    private int stockPN;
    private int ofertaPN;
    private boolean ofertaActivaPN;
    private int precioPN; 
    public String p;

    @PostConstruct
    public void init() {
        /*for (int i=0;i<6;i++){
         productos.add(new ProductoDTO(i,"nombre producto"+i,"Descrpcion product "+i,(byte) 1,3,2222,false));
         System.out.print(i+ productos.get(i).getNombre());
         }*/
    }

    public ProyectoBean() {
    }

    public void seleccionar(ProductoDTO producto){
        productoSeleccionado = producto;
        System.out.println("Seleccionado: " + productoSeleccionado.toString());
    }
    
    public void seleccionProducto() {
        productoSeleccionado = (ProductoDTO) tablaProductos.getRowData();
        this.idProducto = productoSeleccionado.getIdProducto();
        this.nombreProducto = productoSeleccionado.getNombre();
        this.descripcionProducto = productoSeleccionado.getDescripcion();
    }

    public ArrayList<ProductoDTO> getProductos() throws DAOException {
        if(productos == null)
            productos = controller.obtenerTodosProductos();
        return productos;
    }
    
    public ArrayList<ProductoDTO> getProductosSimilares(int idCategoria) throws DAOException {
        if(productosSimilares == null)
            productosSimilares = controller.obtenerProductosSimilares(idCategoria);
        return productosSimilares;
    }

    public void setProductos(ArrayList<ProductoDTO> productos) {
        this.productos = productos;
    }

    public String getNombreUser() {
        return nombreUser;
    }

    public void setNombreUser(String nombreUser) {
        this.nombreUser = nombreUser;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public HtmlDataTable getTablaProductos() {
        return tablaProductos;
    }

    public void setTablaProductos(HtmlDataTable tablaProductos) {
        this.tablaProductos = tablaProductos;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    
    public ProductoDTO getProductoSeleccionado() {
        return productoSeleccionado;
    }

    public void setProductoSeleccionado(ProductoDTO productoSeleccionado) {
        this.productoSeleccionado = productoSeleccionado;
    }

    public int getIdPN() {
        return idPN;
    }

    public void setIdPN(int idPN) {
        this.idPN = idPN;
    }

    public String getNombrePN() {
        return nombrePN;
    }

    public void setNombrePN(String nombrePN) {
        this.nombrePN = nombrePN;
    }

    public String getDescripcionNP() {
        return descripcionNP;
    }

    public void setDescripcionNP(String descripcionNP) {
        this.descripcionNP = descripcionNP;
    }

    public int getStockPN() {
        return stockPN;
    }

    public void setStockPN(int stockPN) {
        this.stockPN = stockPN;
    }

    public int getOfertaPN() {
        return ofertaPN;
    }

    public void setOfertaPN(int ofertaPN) {
        this.ofertaPN = ofertaPN;
    }

    public boolean isOfertaActivaPN() {
        return ofertaActivaPN;
    }

    public void setOfertaActivaPN(boolean ofertaActivaPN) {
        this.ofertaActivaPN = ofertaActivaPN;
    }

    public int getPrecioPN() {
        return precioPN;
    }

    public void setPrecioPN(int precioPN) {
        this.precioPN = precioPN;
    }

    
    public String validarUsuario() throws DAOException {
        UsuarioDTO user = controller.validarUser(nombreUser, pass);
        if (user.getNombre().equals(nombreUser)) {
            return "loginExitoso";
        } else {
            msg = "Datos ingresados son erróneos. Por favor, inténtelo otra vez.";
            return "LoginFallido";
        }

    }
    public void crearProducto() throws DAOException, SQLException{
        ProductoDTO p=new ProductoDTO(idPN,nombrePN,descripcionNP,"sin imagen",stockPN,precioPN,ofertaPN,ofertaActivaPN,0);
        controller.crearProducto(p);
   
    }
}