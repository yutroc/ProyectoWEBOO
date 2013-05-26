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
import javax.annotation.PostConstruct;
import utils.DAOException;

/**
 *
 * @author Javier Andana
 */
@ManagedBean
@RequestScoped
public class ProyectoBean {

    public Controlador controller = new Controlador();
    private ArrayList<ProductoDTO> productos = new ArrayList<ProductoDTO>();
    //variables para el login
    private String nombreUser = "";
    private String pass = "";
    private String msg = "";

    @PostConstruct
    public void init() {
        /*for (int i=0;i<6;i++){
         productos.add(new ProductoDTO(i,"nombre producto"+i,"Descrpcion product "+i,(byte) 1,3,2222,false));
         System.out.print(i+ productos.get(i).getNombre());
         }*/
    }

    public ProyectoBean() {
    }

    public ArrayList<ProductoDTO> getProductos() throws DAOException {
        productos = controller.obtenerTodosProductos();
        return productos;
    }

    public void setProductos(ArrayList<ProductoDTO> productos) {
        this.productos = productos;
    }

    public void imprimirAlgo() {
        System.out.println("hola");
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

    public String validarUsuario() throws DAOException {
        UsuarioDTO user = controller.validarUser(nombreUser, pass);
        if (user.getNombre().equals(nombreUser)) {
            return "loginExitoso";
        } else {
            msg = "Datos ingresados son erróneos. Por favor, inténtelo otra vez.";
            return "LoginFallido";
        }

    }
}