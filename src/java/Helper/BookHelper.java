package Helper;

import Model.Book;
import Model.HibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author mloda
 */
public class BookHelper {
    Session session = null;
    
    public BookHelper(){
        this.session = HibernateUtil.getSessionFactory().getCurrentSession();
    }
    
    public List getAll(){
        List<Book> list = null;
        try{
            Transaction tx = session.beginTransaction();
            Query q = session.createQuery ("from Book b");
            list = (List<Book>) q.list();
        } catch(Exception ex){
            ex.printStackTrace();
        }
        return list;
    }
    
    public Book getById(int id){
        Book book = null;
        try{
            Transaction tx = session.beginTransaction();
            Query q = session.createQuery ("from Book b where b.id = '" + id + "'");
            book = (Book)q.uniqueResult();
        } catch(Exception ex){
            ex.printStackTrace();
        }
        return book;
    }
    
    public void delete(int id){
        try{
            Transaction tx = session.beginTransaction();
            Query q = session.createQuery("delete Book b where b.id='" + id + "'");
            q.executeUpdate();
        } catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
