/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import DTO.ProductoDTO;
import Controler.Controlador;
import DTO.CategoriaDTO;
import DTO.UsuarioDTO;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import javax.annotation.PostConstruct;
import javax.faces.component.html.HtmlDataTable;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import utils.DAOException;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author Javier Andana
 */
@ManagedBean
@SessionScoped
public class ProyectoBean {

    public Controlador controller = new Controlador();
    private ArrayList<ProductoDTO> productos = null;
    //variables para seleecionar un producto
    private ProductoDTO productoSeleccionado;
    private ArrayList<ProductoDTO> productosSimilares;
    //variables para el login
    private String nombreUser = "";
    private String pass = "";
    private String msg = "";
    //tablas
    private HtmlDataTable tablaProductos;
    private HtmlDataTable tablaCategorias;
    //variables para crear un producto
    private int idPN;
    private String nombrePN;
    private String descripcionNP;
    private int stockPN;
    private int ofertaPN;
    private boolean ofertaActivaPN;
    private int precioPN;
    public String p;
    //variables para selecionar un producto
    private int idProducto;
    private String nombreProducto;
    private String descripcionProducto;
    //lista de categorias
    private ArrayList<CategoriaDTO> categorias = null;
    //variables para crear categorias
    private int idCategoria;
    private String nombre;
    private Byte imagen = 0;
    //varibleas para selecionar una categoria
    private CategoriaDTO categoriaSeleccionada;
    private int idCategoriaSelecionada;
    private String nombreCategoriaSeleccionada;
    private Byte imagenCategoriaSeleccionada;
    private boolean crearVisible = false;
    private boolean editarVisible = false;

    public boolean isCrearVisible() {
        return crearVisible;
    }

    public void setCrearVisible(boolean crearVisible) {
        this.crearVisible = crearVisible;
    }

    @PostConstruct
    public void init() {
        /*for (int i=0;i<6;i++){
         productos.add(new ProductoDTO(i,"nombre producto"+i,"Descrpcion product "+i,(byte) 1,3,2222,false));
         System.out.print(i+ productos.get(i).getNombre());
         }*/
    }

    public ProyectoBean() {
        crearVisible = false;
        editarVisible = false;
    }

    public void seleccionar(ProductoDTO producto) {
        productoSeleccionado = producto;
        System.out.println("Seleccionado: " + productoSeleccionado.toString());
    }

    public void seleccionProducto() {
        productoSeleccionado = (ProductoDTO) tablaProductos.getRowData();
//        this.editarVisible = true;


//        this.idProducto = productoSeleccionado.getIdProducto();
//        this.nombreProducto = productoSeleccionado.getNombre();
//        this.descripcionProducto = productoSeleccionado.getDescripcion();
    }

    public ArrayList<ProductoDTO> getProductosF() throws DAOException {
        productos = controller.obtenerTodosProductos();
        return productos;
    }

    public ArrayList<ProductoDTO> getProductos() throws DAOException {
        if (productos == null) {
            productos = controller.obtenerTodosProductos();
        }
        return productos;
    }

    public ArrayList<ProductoDTO> getProductosSimilares(int idCategoria) throws DAOException {
        if (productosSimilares == null) {
            productosSimilares = controller.obtenerProductosSimilares(idCategoria);
        }
        return productosSimilares;
    }

    public void setProductos(ArrayList<ProductoDTO> productos) {
        this.productos = productos;
    }

    public ArrayList<CategoriaDTO> getCategorias() throws DAOException {
        return categorias = controller.obtenerTodosCategorias();
    }

    public void setCategorias(ArrayList<CategoriaDTO> categorias) {
        this.categorias = categorias;
    }

    public String seleccionCategoria() {
        categoriaSeleccionada = (CategoriaDTO) tablaCategorias.getRowData();
        this.idCategoriaSelecionada = categoriaSeleccionada.getIdCategoria();
        this.nombreCategoriaSeleccionada = categoriaSeleccionada.getNombre();
        this.imagenCategoriaSeleccionada = categoriaSeleccionada.getImagen();
        System.out.print("jojojooojojooj" + idCategoriaSelecionada + nombreCategoriaSeleccionada + imagenCategoriaSeleccionada);
        return "EditarCategoria";
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

    public boolean isEditarVisible() {
        return editarVisible;
    }

    public void setEditarVisible(boolean editarVisible) {
        this.editarVisible = editarVisible;
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

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Byte getImagen() {
        return imagen;
    }

    public void setImagen(Byte imagen) {
        this.imagen = imagen;
    }

    public HtmlDataTable getTablaCategorias() {
        return tablaCategorias;
    }

    public void setTablaCategorias(HtmlDataTable tablaCategorias) {
        this.tablaCategorias = tablaCategorias;
    }

    public int getIdCategoriaSelecionada() {
        return idCategoriaSelecionada;
    }

    public void setIdCategoriaSelecionada(int idCategoriaSelecionada) {
        this.idCategoriaSelecionada = idCategoriaSelecionada;
    }

    public String getNombreCategoriaSeleccionada() {
        return nombreCategoriaSeleccionada;
    }

    public void setNombreCategoriaSeleccionada(String nombreCategoriaSeleccionada) {
        this.nombreCategoriaSeleccionada = nombreCategoriaSeleccionada;
    }

    public Byte getImagenCategoriaSeleccionada() {
        return imagenCategoriaSeleccionada;
    }

    public void setImagenCategoriaSeleccionada(Byte imagenCategoriaSeleccionada) {
        this.imagenCategoriaSeleccionada = imagenCategoriaSeleccionada;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getDescripcionProducto() {
        return descripcionProducto;
    }

    public void setDescripcionProducto(String descripcionProducto) {
        this.descripcionProducto = descripcionProducto;
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

    public void crearProducto() throws DAOException, SQLException {
        ProductoDTO p = new ProductoDTO(0, nombrePN, descripcionNP, "sin imagen", stockPN, precioPN, ofertaPN, ofertaActivaPN, idCategoriaSelecionada);
        controller.crearProducto(p);
    }

    public void editarProducto() throws DAOException, SQLException {
        //ProductoDTO p = new ProductoDTO(0, nombrePN, descripcionNP, "sin imagen", stockPN, precioPN, ofertaPN, ofertaActivaPN, idCategoriaSelecionada);
        controller.editarProducto(this.productoSeleccionado);
    }

    public String crearCategoria() throws DAOException, SQLException {
        CategoriaDTO p = new CategoriaDTO(idCategoria, nombre, imagen);
        controller.crearCategoria(p);
        return "Categoria";

    }

    public String actualizarCategoria() throws DAOException, SQLException {
        CategoriaDTO p = new CategoriaDTO(idCategoriaSelecionada, nombreCategoriaSeleccionada, imagen);
        System.out.println(idCategoriaSelecionada + "pppppp" + nombreCategoriaSeleccionada);
        controller.actualizarCategoria(p);
        return "Categoria";

    }

    public void eliminarCategoria() throws DAOException, SQLException {
        categoriaSeleccionada = (CategoriaDTO) tablaCategorias.getRowData();
        this.idCategoriaSelecionada = categoriaSeleccionada.getIdCategoria();
        this.nombreCategoriaSeleccionada = categoriaSeleccionada.getNombre();
        this.imagenCategoriaSeleccionada = categoriaSeleccionada.getImagen();
        System.out.print("jojojooojojooj" + idCategoriaSelecionada + nombreCategoriaSeleccionada + imagenCategoriaSeleccionada);
        CategoriaDTO p = new CategoriaDTO(idCategoriaSelecionada, nombreCategoriaSeleccionada, imagen);
        System.out.println(idCategoriaSelecionada + "pppppp" + nombreCategoriaSeleccionada);
        controller.eliminarCategoria(p);
        //return "Categoria";

    }

    public void handleFileUpload(FileUploadEvent event) {

        //get uploaded file from the event
        UploadedFile uploadedFile = (UploadedFile) event.getFile();

        //create an InputStream from the uploaded file
        InputStream inputStr = null;
        try {
            inputStr = uploadedFile.getInputstream();
        } catch (IOException e) {
            //log error
        }

        //create destination File

        String destPath = "C:/";
        File destFile = new File(destPath);

        //use org.apache.commons.io.FileUtils to copy the File
        try {
            FileUtils.copyInputStreamToFile(inputStr, destFile);
        } catch (IOException e) {
            //log error
        }
    }
    
    
    
    
    
}