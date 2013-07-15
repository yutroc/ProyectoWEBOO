/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import DTO.UsuarioDTO;
import Controler.Controlador;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.component.html.HtmlDataTable;
import utils.DAOException;

/**
 *
 * @author Felipe
 */
@ManagedBean
@SessionScoped
public class UserBean {

    /**
     * Creates a new instance of UserBean
     */
    public UserBean() {
    }
    public Controlador controller = new Controlador();
    public String nombreUsuario;
    public String APaternoUsuario;
    public String AMaternoUsuario;
    public String rutUsuario;
    public String comunaUsuario;
    public String ciudadUsuario;
    public String direccionUsuario;
    public String emailUsuario;
    public String passUsuario;
    public String telefonoUsuario;
    public int idTipoUsuario;
    public int idUsuario;
    public ArrayList<UsuarioDTO> user;
    private HtmlDataTable tablaUsuarios;
    private String name = "";
    // construdctores agregar y modificar usuario

    public HtmlDataTable getTablaUsuarios() {
        return tablaUsuarios;
    }

    public void setTablaUsuarios(HtmlDataTable tablaUsuarios) {
        this.tablaUsuarios = tablaUsuarios;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public ArrayList<UsuarioDTO> getUser() throws DAOException, SQLException {
        return controller.obtenerUsuarios(name);
    }

    public void setUser(ArrayList<UsuarioDTO> user) {
        this.user = user;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getAPaternoUsuario() {
        return APaternoUsuario;
    }

    public void setAPaternoUsuario(String APaternoUsuario) {
        this.APaternoUsuario = APaternoUsuario;
    }

    public String getAMaternoUsuario() {
        return AMaternoUsuario;
    }

    public void setAMaternoUsuario(String AMaternoUsuario) {
        this.AMaternoUsuario = AMaternoUsuario;
    }

    public String getRutUsuario() {
        return rutUsuario;
    }

    public void setRutUsuario(String rutUsuario) {
        this.rutUsuario = rutUsuario;
    }

    public String getComunaUsuario() {
        return comunaUsuario;
    }

    public void setComunaUsuario(String comunaUsuario) {
        this.comunaUsuario = comunaUsuario;
    }

    public String getCiudadUsuario() {
        return ciudadUsuario;
    }

    public void setCiudadUsuario(String ciudadUsuario) {
        this.ciudadUsuario = ciudadUsuario;
    }

    public String getDireccionUsuario() {
        return direccionUsuario;
    }

    public void setDireccionUsuario(String direccionUsuario) {
        this.direccionUsuario = direccionUsuario;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public String getPassUsuario() {
        return passUsuario;
    }

    public void setPassUsuario(String passUsuario) {
        this.passUsuario = passUsuario;
    }

    public String getTelefonoUsuario() {
        return telefonoUsuario;
    }

    public void setTelefonoUsuario(String telefonoUsuario) {
        this.telefonoUsuario = telefonoUsuario;
    }

    public int getIdTipoUsuario() {
        return idTipoUsuario;
    }

    public void setIdTipoUsuario(int idTipoUsuario) {
        this.idTipoUsuario = idTipoUsuario;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void refrescarVariables() {
         nombreUsuario = "";
         APaternoUsuario = "";
         AMaternoUsuario = "";
         rutUsuario = "";
         comunaUsuario = "";
         ciudadUsuario = "";
         direccionUsuario = "";
         emailUsuario = "";
         passUsuario = "";
         telefonoUsuario = "";
         
    }

    public String crearUsuario() throws DAOException, SQLException {
       
        Calendar cal = Calendar.getInstance();
        System.out.println(cal.get(Calendar.YEAR));
        // String fecha = cal.get(Calendar.YEAR)+"-"+(cal.get(Calendar.MONTH)+1)+"-"+cal.get(Calendar.DATE);
        cal.set(Calendar.YEAR, Calendar.MONTH + 1, Calendar.DATE);
        Date fechaD = cal.getTime();
        System.out.println(fechaD);
        UsuarioDTO user = new UsuarioDTO(1, nombreUsuario, APaternoUsuario, AMaternoUsuario, rutUsuario, direccionUsuario,
                comunaUsuario, ciudadUsuario, emailUsuario, fechaD, passUsuario, telefonoUsuario, idTipoUsuario);
        System.out.println(user.toString());

        return controller.crearUsuario(user);

        //System.out.println(user.getIdTipo());
        //return "index.xhtml";
    }

    public String actualizarUsuario() throws DAOException, SQLException {
        Calendar cal = Calendar.getInstance();
        System.out.println(cal.get(Calendar.YEAR));
        // String fecha = cal.get(Calendar.YEAR)+"-"+(cal.get(Calendar.MONTH)+1)+"-"+cal.get(Calendar.DATE);
        cal.set(Calendar.YEAR, Calendar.MONTH + 1, Calendar.DATE);
        Date fechaD = cal.getTime();
        System.out.println(fechaD);
        UsuarioDTO user = new UsuarioDTO(idUsuario, nombreUsuario, APaternoUsuario, AMaternoUsuario, rutUsuario, direccionUsuario,
                comunaUsuario, ciudadUsuario, emailUsuario, fechaD, passUsuario, telefonoUsuario, idTipoUsuario);
        System.out.println(user.toString());
 refrescarVariables();
        return controller.actualizarUsuario(user);
    }

    public String seleccionUsuario() {
        UsuarioDTO usuarioSeleccionado = (UsuarioDTO) tablaUsuarios.getRowData();
        this.idUsuario = usuarioSeleccionado.getIdUsuario();
        this.nombreUsuario = usuarioSeleccionado.getNombre();
        this.APaternoUsuario = usuarioSeleccionado.getAPaterno();
        this.AMaternoUsuario = usuarioSeleccionado.getAMaterno();
        this.rutUsuario = usuarioSeleccionado.getRut();
        this.direccionUsuario = usuarioSeleccionado.getDireccion();
        this.comunaUsuario = usuarioSeleccionado.getComuna();
        this.ciudadUsuario = usuarioSeleccionado.getCiudad();
        this.emailUsuario = usuarioSeleccionado.getEmail();
        this.passUsuario = usuarioSeleccionado.getContraseña();
        this.telefonoUsuario = usuarioSeleccionado.getTelefono();
        this.idTipoUsuario = usuarioSeleccionado.getIdTipo();
        //System.out.print("jojojooojojooj" + idCategoriaSelecionada + nombreCategoriaSeleccionada + imagenCategoriaSeleccionada);
        return "editarUsuario.xhtml";
    }

    public String eliminarUsuario() throws DAOException, SQLException {
        UsuarioDTO usuarioSeleccionado = (UsuarioDTO) tablaUsuarios.getRowData();
        this.idUsuario = usuarioSeleccionado.getIdUsuario();
        this.nombreUsuario = usuarioSeleccionado.getNombre();
        this.APaternoUsuario = usuarioSeleccionado.getAPaterno();
        this.AMaternoUsuario = usuarioSeleccionado.getAMaterno();
        this.rutUsuario = usuarioSeleccionado.getRut();
        this.direccionUsuario = usuarioSeleccionado.getDireccion();
        this.comunaUsuario = usuarioSeleccionado.getComuna();
        this.ciudadUsuario = usuarioSeleccionado.getCiudad();
        this.emailUsuario = usuarioSeleccionado.getEmail();
        this.passUsuario = usuarioSeleccionado.getContraseña();
        this.telefonoUsuario = usuarioSeleccionado.getTelefono();
        this.idTipoUsuario = usuarioSeleccionado.getIdTipo();
        controller.eliminarUsuario(usuarioSeleccionado);
        return "mostrarUsuario.xhtml";
    }
}
