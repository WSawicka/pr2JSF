package Bean;

import Helper.BookHelper;
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
public class BookBean {
    private Integer id;
    private int isbn;
    private String author;
    private String title;
    private String description;
    private String state;
    private String category;
    DataModel bookTitles;
    BookHelper helper;
    
    public BookBean() {
        helper = new BookHelper();
    }
    
    public DataModel getBookTitles() {
        if (bookTitles == null) {
            bookTitles = new ListDataModel(helper.getAll());
        }
        return bookTitles;
    }
    
    public void delete(int id){
        helper.delete(id);
    }
}
