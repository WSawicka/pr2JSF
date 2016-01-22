/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Helper.BookHelper;
import Helper.RentHelper;
import Helper.RentHistoryHelper;
import Model.Book;
import Model.Rent;
import Model.User;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
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
    private RentHistoryHelper historyHelper;
    private BookHelper bookHelper;
    private DataModel allRents;
    private DataModel userRents;

    public RentBean() {
        this.helper = new RentHelper();
        this.historyHelper = new RentHistoryHelper();
        this.bookHelper = new BookHelper();
    }

    public DataModel getAllRents() {
        allRents = new ListDataModel(helper.getAll());
        return allRents;
    }

    public DataModel getUserRents() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession session = request.getSession(true);
        userRents = new ListDataModel(helper.getAllForUser(session.getAttribute("LoggedUser")));
        return userRents;
    }

    public void confirmRentingBook(Book book) {
        if (book != null) {
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            HttpSession session = request.getSession(true);
            User user = (User) session.getAttribute("LoggedUser");
            Date date = new Date();
            boolean result = checkIfFree(book.getId());
            if (result == true) {
                add(user.getLogin(), book.getId(), date);
                historyHelper.add(user.getLogin(), book.getId(), date);
                userRents = new ListDataModel(helper.getAllForUser(user));
                List<Book> booksInBasket = (List<Book>) session.getAttribute("Basket");
                booksInBasket.remove(book);
                session.setAttribute("Basket", booksInBasket);
            } else {
                FacesContext.getCurrentInstance().
                        addMessage("form:basketTable", new FacesMessage("Sorry, this book is already taken."));
            }
        }
    }
    
    public void endRent(Rent rent){
        bookHelper.setStateFree(rent.getBookId());
        helper.delete(rent.getId());
    }

    public boolean checkIfFree(Integer bookId) {
        if (helper.rentBook(bookId) == true) {
            return true;
        } else {
            return false;
        }
    }

    public void add(String login, Integer bookId, Date data) {
        helper.add(login, bookId, data);
    }
}
