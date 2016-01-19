/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Helper.UserHelper;
import Model.User;
import java.io.IOException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
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
@SessionScoped
public class UserBean {
    private UserHelper helper;
    private DataModel normalUserList;
    private DataModel employeeList;
    private DataModel adminList;
    private DataModel notConfirmed;
    
    public UserBean() {
        helper = new UserHelper();
    }

    public DataModel getNormalUserList() {
        normalUserList = new ListDataModel(helper.getNormal());
        return normalUserList;
    }

    public DataModel getEmployeeList() {
        employeeList = new ListDataModel(helper.getEmployee());
        return employeeList;
    }

    public DataModel getAdminList() {
        adminList = new ListDataModel(helper.getAdmin());
        return adminList;
    }
    
    public DataModel getNotConfirmed() {
        notConfirmed = new ListDataModel(helper.getNotConfirmed());
        return notConfirmed;
    }
    
    public void logIn(String login, String password) throws IOException{
        User user = helper.getByLoginPassword(login, password);
        if(user != null){
            HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
            HttpSession session = request.getSession(true);
            session.setAttribute("LoggedUser", user);
            if(user.getType().equals("admin")){
                FacesContext.getCurrentInstance().getExternalContext().redirect("admin/index.xhtml");
            }
            else if(user.getType().equals("emp")){
                FacesContext.getCurrentInstance().getExternalContext().redirect("employee/index.xhtml");
            }
            else if(user.getType().equals("normal")){
                FacesContext.getCurrentInstance().getExternalContext().redirect("normal/index.xhtml");
            }
        }
        else{
            FacesContext.getCurrentInstance().
                    addMessage("form:logMe", new FacesMessage("Wrong data! Try again."));
        }
    }
    
    public void register(String login, String address, String password, String type) throws IOException{
        User user = new User(login, password, type, "no", address);
        helper.add(user);
        FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
    }
    
    public void confirm(String id){
        helper.confirm(Integer.parseInt(id));
    }
    
    public void delete(String id) throws IOException{
        helper.delete(Integer.parseInt(id));
        FacesContext.getCurrentInstance().getExternalContext().redirect("users.xhtml");
    }
}
