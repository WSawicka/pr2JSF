/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Helper.MessageHelper;
import Model.Message;
import java.io.IOException;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

/**
 *
 * @author mloda
 */
@ManagedBean
@SessionScoped
public class MessageBean {

    private DataModel messages;
    private MessageHelper helper;

    public MessageBean() {
        helper = new MessageHelper();
    }

    public DataModel getMessages() {
        messages = new ListDataModel(helper.getAll());
        return messages;
    }

    public void delete(String id) throws IOException {
        helper.delete(Integer.parseInt(id));
        FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
    }

    public void add(String text) throws IOException {
        Date date = new Date();
        Message message = new Message(text, date);
        helper.add(message);
        FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
    }
}
