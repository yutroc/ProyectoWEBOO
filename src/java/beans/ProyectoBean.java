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

    /**
     * Creates a new instance of ProyectoBean
     */
    private ArrayList<ProductoDTO> productos = new ArrayList<ProductoDTO>();
    @PostConstruct
    public void init(){
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
}
