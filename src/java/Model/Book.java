package Model;
// Generated 2016-01-09 13:04:29 by Hibernate Tools 4.3.1



/**
 * Book generated by hbm2java
 */
public class Book  implements java.io.Serializable {


     private Integer id;
     private int isbn;
     private String author;
     private String title;
     private String description;
     private String state;
     private String category;

    public Book() {
    }

    public Book(int isbn, String author, String title, String description, String state, String category) {
       this.isbn = isbn;
       this.author = author;
       this.title = title;
       this.description = description;
       this.state = state;
       this.category = category;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public int getIsbn() {
        return this.isbn;
    }
    
    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }
    public String getAuthor() {
        return this.author;
    }
    
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    public String getState() {
        return this.state;
    }
    
    public void setState(String state) {
        this.state = state;
    }
    public String getCategory() {
        return this.category;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }




}


