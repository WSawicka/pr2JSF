package Model;
// Generated 2016-01-09 13:04:29 by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * Renthistory generated by hbm2java
 */
public class Renthistory  implements java.io.Serializable {


     private Integer id;
     private String user;
     private int bookId;
     private Date date;

    public Renthistory() {
    }

    public Renthistory(String user, int bookId, Date date) {
       this.user = user;
       this.bookId = bookId;
       this.date = date;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public String getUser() {
        return this.user;
    }
    
    public void setUser(String user) {
        this.user = user;
    }
    public int getBookId() {
        return this.bookId;
    }
    
    public void setBookId(int bookId) {
        this.bookId = bookId;
    }
    public Date getDate() {
        return this.date;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }




}


