/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Helper.RentHistoryHelper;
import Helper.UserHelper;
import Model.User;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

/**
 *
 * @author mloda
 */
@ManagedBean
@RequestScoped
public class RentHistoryBean {

    private RentHistoryHelper helper;
    private UserHelper userHelper;
    private DataModel userRents;

    public RentHistoryBean() {
        helper = new RentHistoryHelper();
        userHelper = new UserHelper();
    }

    public DataModel getUserRents() {
        return userRents;
    }

    public void getAllForUser(String selectedLogin) {
        User user = userHelper.getByLogin(selectedLogin);
        userRents = new ListDataModel(helper.getAllForUser(user));
    }
}
