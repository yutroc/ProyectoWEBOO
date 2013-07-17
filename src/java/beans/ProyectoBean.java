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
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.jsp.JspFactory;
import javax.servlet.jsp.PageContext;
//import org.primefaces.event.FileUploadEvent;
//import org.primefaces.model.UploadedFile;
import utils.DAOException;
//import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.myfaces.custom.fileupload.UploadedFile;

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
    private UploadedFile uploadedFile;
    private String fileName;
    //carga de productos por categoria 
    CategoriaDTO cat;
    //
    String nameCategoria = "";
    String nameProducto = "";

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

    public void seleccionarCat(CategoriaDTO c) throws DAOException {
        cat = c;
        productos = controller.obtenerProductosPorCategoria(cat.getIdCategoria());
        //System.out.println("Seleccionado: " + productoSeleccionado.toString());
    }

    public void seleccionar(ProductoDTO producto) {
        productoSeleccionado = producto;
        //System.out.println("Seleccionado: " + productoSeleccionado.toString());
    }

    public void seleccionProducto() {
        //UIComponent myComponent = FacesContext.getCurrentInstance().getViewRoot().findComponent("dialog2");
        productoSeleccionado = (ProductoDTO) tablaProductos.getRowData();
//        this.editarVisible = true;


        this.idProducto = productoSeleccionado.getIdProducto();
        this.nombreProducto = productoSeleccionado.getNombre();
        this.descripcionProducto = productoSeleccionado.getDescripcion();
    }

    public void refrescarVariablesCategoria(){
        nombre="";
    }

    public ArrayList<ProductoDTO> getProductosF() throws DAOException {
        productos = controller.obtenerTodosProductos(nameProducto);
        return productos;
    }

    public ArrayList<ProductoDTO> getProductos() throws DAOException {
        if (productos == null) {

            productos = controller.obtenerTodosProductos("");


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

    public String getNameProducto() {
        return nameProducto;
    }

    public void setNameProducto(String nameProducto) {
        this.nameProducto = nameProducto;
    }

    public ArrayList<CategoriaDTO> getCategorias() throws DAOException {
        return categorias = controller.obtenerTodosCategorias(nameCategoria);
    }

    public void setCategorias(ArrayList<CategoriaDTO> categorias) {
        this.categorias = categorias;
    }

    public String seleccionCategoria() {
        categoriaSeleccionada = (CategoriaDTO) tablaCategorias.getRowData();
        this.idCategoriaSelecionada = categoriaSeleccionada.getIdCategoria();
        this.nombreCategoriaSeleccionada = categoriaSeleccionada.getNombre();
        this.imagenCategoriaSeleccionada = categoriaSeleccionada.getImagen();
        //System.out.print("jojojooojojooj" + idCategoriaSelecionada + nombreCategoriaSeleccionada + imagenCategoriaSeleccionada);
        return "EditarCategoria";
    }

    public void submit() {

        // Just to demonstrate what information you can get from the uploaded file.
        System.out.println("File type: " + uploadedFile.getContentType());
        System.out.println("File name: " + uploadedFile.getName());
        System.out.println("File size: " + uploadedFile.getSize() + " bytes");

        // Prepare filename prefix and suffix for an unique filename in upload folder.
        String prefix = FilenameUtils.getBaseName(uploadedFile.getName());
        String suffix = FilenameUtils.getExtension(uploadedFile.getName());

        // Prepare file and outputstream.
        File file = null;
        OutputStream output = null;

        try {
            // Create file with unique name in upload folder and write to it.
            ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
            String serverRealPath = servletContext.getRealPath("/");
            String serverContextPath = servletContext.getContextPath();
            file = File.createTempFile(prefix + "_", "." + suffix, new File(serverRealPath + "/imagenes/productos"));
            file.renameTo(new File(serverRealPath + "/imagenes/productos/" + this.productoSeleccionado.getIdProducto() + ".jpg"));
            System.out.println("getAbsolutePath " + serverRealPath + "/imagenes/productos/" + this.productoSeleccionado.getIdProducto() + ".jpg");
            output = new FileOutputStream(new File(serverRealPath + "/imagenes/productos/" + this.productoSeleccionado.getIdProducto() + ".jpg"));
            IOUtils.copy(uploadedFile.getInputStream(), output);
            fileName = file.getName();

            // Show succes message.
            FacesContext.getCurrentInstance().addMessage("uploadForm", new FacesMessage(
                    FacesMessage.SEVERITY_INFO, "File upload succeed!", null));
        } catch (IOException e) {
            // Cleanup.
            if (file != null) {
                file.delete();
            }

            // Show error message.
            FacesContext.getCurrentInstance().addMessage("uploadForm", new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, "File upload failed with I/O error.", null));

            // Always log stacktraces (with a real logger).
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(output);
        }
    }

    public UploadedFile getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(UploadedFile uploadedFile) {
        this.uploadedFile = uploadedFile;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
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

    public CategoriaDTO getCat() {
        return cat;
    }

    public void setCat(CategoriaDTO cat) {
        this.cat = cat;
    }

    public String getDescripcionProducto() {
        return descripcionProducto;
    }

    public void setDescripcionProducto(String descripcionProducto) {
        this.descripcionProducto = descripcionProducto;
    }

    public String getNameCategoria() {
        return nameCategoria;
    }

    public void setNameCategoria(String nameCategoria) {
        this.nameCategoria = nameCategoria;
    }

    public String validarUsuario() throws DAOException {
        UsuarioDTO user = controller.validarUser(nombreUser, pass);
        System.out.println("user " + user.getIdUsuario());
        if (user.getNombre().equals(nombreUser)) {
            if (user.getIdUsuario() == 1) {
                return "loginExitoso";//admin
            } else {
                return "LoginNormal";
            }
        } else {
            msg = "Datos ingresados son erróneos. Por favor, inténtelo otra vez.";
            return "LoginFallido";
        }

    }

    private void resetProducto() {
        nombrePN = "";
        descripcionNP = "";
        stockPN = 0;
        precioPN = 0;
        ofertaPN = 0;
        ofertaActivaPN = false;
        idCategoriaSelecionada = 1;

    }

    public void crearProducto() throws DAOException, SQLException {
        ProductoDTO p = new ProductoDTO(0, nombrePN, descripcionNP, "sin imagen", stockPN, precioPN, ofertaPN, ofertaActivaPN, idCategoriaSelecionada);
        controller.crearProducto(p);
        resetProducto();
    }

    public void editarProducto() throws DAOException, SQLException {
        if (!this.uploadedFile.getName().equals(null)) {
            submit();
        }

        //ProductoDTO p = new ProductoDTO(0, nombrePN, descripcionNP, "sin imagen", stockPN, precioPN, ofertaPN, ofertaActivaPN, idCategoriaSelecionada);
        controller.editarProducto(this.productoSeleccionado);
        resetProducto();
    }

    public String crearCategoria() throws DAOException, SQLException {
        CategoriaDTO p = new CategoriaDTO(idCategoria, nombre, imagen);
        controller.crearCategoria(p);
        return "MantenedorCategorias";

    }

    public String actualizarCategoria() throws DAOException, SQLException {
        CategoriaDTO p = new CategoriaDTO(idCategoriaSelecionada, nombreCategoriaSeleccionada, imagen);
        //System.out.println(idCategoriaSelecionada + "pppppp" + nombreCategoriaSeleccionada);
        controller.actualizarCategoria(p);
        return "Categoria";

    }

    public void eliminarCategoria() throws DAOException, SQLException {
        categoriaSeleccionada = (CategoriaDTO) tablaCategorias.getRowData();
        this.idCategoriaSelecionada = categoriaSeleccionada.getIdCategoria();
        this.nombreCategoriaSeleccionada = categoriaSeleccionada.getNombre();
        this.imagenCategoriaSeleccionada = categoriaSeleccionada.getImagen();
        //System.out.print("jojojooojojooj" + idCategoriaSelecionada + nombreCategoriaSeleccionada + imagenCategoriaSeleccionada);
        CategoriaDTO p = new CategoriaDTO(idCategoriaSelecionada, nombreCategoriaSeleccionada, imagen);
        //System.out.println(idCategoriaSelecionada + "pppppp" + nombreCategoriaSeleccionada);
        controller.eliminarCategoria(p);
        //return "Categoria";

    }
//    public void handleFileUpload(FileUploadEvent event) {
//
//        //get uploaded file from the event
//        UploadedFile uploadedFile = (UploadedFile) event.getFile();
//
//        //create an InputStream from the uploaded file
//        InputStream inputStr = null;
//        try {
//            inputStr = uploadedFile.getInputstream();
//        } catch (IOException e) {
//            //log error
//        }
//
//        //create destination File
//
//        String destPath = "C:/";
//        File destFile = new File(destPath);
//
//        //use org.apache.commons.io.FileUtils to copy the File
//        try {
//            FileUtils.copyInputStreamToFile(inputStr, destFile);
//        } catch (IOException e) {
//            //log error
//        }
//    }
}