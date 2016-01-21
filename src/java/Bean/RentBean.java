/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Helper.RentHelper;
import Model.Rent;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author mloda
 */
@ManagedBean
@RequestScoped
public class RentBean {
    private RentHelper helper;
    private DataModel userRents;

    public RentBean() {
        this.helper = new RentHelper();
    }

    public DataModel getUserRents() {
        HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession session = request.getSession(true);
        userRents = new ListDataModel(helper.getAllForUser(session.getAttribute("LoggedUser")));
        return userRents;
    }
    
}
