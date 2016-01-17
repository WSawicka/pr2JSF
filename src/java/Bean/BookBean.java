package Bean;

import Helper.BookHelper;
import Model.Book;
import java.io.IOException;
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
public class BookBean {
    private DataModel bookTitles;
    private BookHelper helper;
    
    public BookBean() {
        helper = new BookHelper();
    }
    
    public DataModel getBookTitles() {
        bookTitles = new ListDataModel(helper.getAll());
        return bookTitles;
    }
    
    public void delete(String id) throws IOException{
        helper.delete(Integer.parseInt(id));
    }
    
    public void add(String isbn, String author, String title, String description, String state, String category) throws IOException{
        Book book = new Book(Integer.parseInt(isbn), author, title, description, state, category);
        helper.add(book);
        FacesContext.getCurrentInstance().getExternalContext().redirect("admin/index.xhtml");
    }
}
