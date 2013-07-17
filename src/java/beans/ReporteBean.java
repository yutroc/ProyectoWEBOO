/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.IOException;
import java.util.HashMap;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import persistance.DAOController;
import utils.DAOException;

/**
 *
 * @author Davros
 */
@ManagedBean
@SessionScoped
public class ReporteBean {

    private JasperPrint jasperPrint;

    public ReporteBean() {
    }

    public void reportBuilder() throws JRException, DAOException {
        ProyectoBean p = new ProyectoBean();
        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(p.getProductos());
        String report = FacesContext.getCurrentInstance().getExternalContext().getRealPath("reportProductos.jasper");
        DAOController dc = new DAOController();
        jasperPrint = JasperFillManager.fillReport(report, null, dc.getConnection()); //JasperFillManager.fillReport(report, new HashMap(),beanCollectionDataSource);

    }

    public String productos( ) throws IOException, JRException, DAOException {

        String report = FacesContext.getCurrentInstance().getExternalContext().getRealPath("reportProductos.jasper");
        DAOController dc = new DAOController();
        jasperPrint = JasperFillManager.fillReport(report, null, dc.getConnection()); //JasperFillManager.fillReport(report, new HashMap(),beanCollectionDataSource);

        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();

        httpServletResponse.addHeader("Content-disposition", "inline; filename=Productos.pdf");

        ServletOutputStream servletStream = httpServletResponse.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, servletStream);
        //JasperExportManager.exportReportToPdfStream("reportProductos.jasper", servletStream);

        FacesContext.getCurrentInstance().responseComplete();

        return "r?faces-redirect=true";

    }

    public String usuarios( ) throws IOException, JRException, DAOException {

        String report = FacesContext.getCurrentInstance().getExternalContext().getRealPath("reportUsuarios.jasper");
        DAOController dc = new DAOController();
        jasperPrint = JasperFillManager.fillReport(report, null, dc.getConnection()); //JasperFillManager.fillReport(report, new HashMap(),beanCollectionDataSource);

        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();

        httpServletResponse.addHeader("Content-disposition", "inline; filename=Usuarios.pdf");

        ServletOutputStream servletStream = httpServletResponse.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, servletStream);
        //JasperExportManager.exportReportToPdfStream("reportProductos.jasper", servletStream);

        FacesContext.getCurrentInstance().responseComplete();

        return "r?faces-redirect=true";

    }
    
     public String compras( ) throws IOException, JRException, DAOException {

        String report = FacesContext.getCurrentInstance().getExternalContext().getRealPath("VentasAnuales.jasper");
        DAOController dc = new DAOController();
        jasperPrint = JasperFillManager.fillReport(report, null, dc.getConnection()); //JasperFillManager.fillReport(report, new HashMap(),beanCollectionDataSource);

        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();

        httpServletResponse.addHeader("Content-disposition", "inline; filename=Compras.pdf");

        ServletOutputStream servletStream = httpServletResponse.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, servletStream);
        //JasperExportManager.exportReportToPdfStream("reportProductos.jasper", servletStream);

        FacesContext.getCurrentInstance().responseComplete();

        return "r?faces-redirect=true";

    }
}
