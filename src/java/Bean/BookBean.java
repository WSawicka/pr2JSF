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
    private DataModel newBooks;
    private DataModel searchResults;
    private BookHelper helper;
    private int isbn;
    private String author;
    private String title;
    private String description;
    private String state;
    private String category;
    private boolean editing;

    public BookBean() {
        helper = new BookHelper();
        isbn = 0;
        author = null;
        title = null;
        description = null;
        state = null;
        category = null;
    }

    public DataModel getBookTitles() {
        bookTitles = new ListDataModel(helper.getAll());
        return bookTitles;
    }
    
    public DataModel getLastThree(){
        this.newBooks = new ListDataModel(helper.getLastThree());
        return newBooks;
    }

    public DataModel getSearchResults() {
        return searchResults;
    }
    
    public void search(String text) throws IOException{
        this.searchResults = new ListDataModel(helper.getByNameAuthorISBN(text));
        FacesContext.getCurrentInstance().getExternalContext().redirect("/pr2/searchResult.xhtml");
    }
    
    public void delete(String id) throws IOException {
        helper.delete(Integer.parseInt(id));
    }

    public void edit(String id) throws IOException {
        Book b = helper.getById(Integer.parseInt(id));
        this.author = b.getAuthor();
        this.category = b.getCategory();
        this.description = b.getDescription();
        this.isbn = b.getIsbn();
        this.state = b.getState();
        this.title = b.getTitle();
        this.editing = true;
        FacesContext.getCurrentInstance().getExternalContext().redirect("addBook.xhtml");
    }

    public void add(String isbn, String author, String title, String description, String state, String category) throws IOException {
        Book book = new Book(Integer.parseInt(isbn), author, title, description, state, category);
        if (editing == true) {
            helper.edit(book);
            editing = false;
        } else {
            helper.add(book);
        }
        FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
    }

    public int getIsbn() {
        return isbn;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getState() {
        return state;
    }

    public String getCategory() {
        return category;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
