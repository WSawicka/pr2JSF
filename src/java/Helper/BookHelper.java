package Helper;

import Model.Book;
import Model.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author mloda
 */
public class BookHelper {

    public BookHelper() {
    }

    public List<Book> getAll() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        List<Book> list = new ArrayList<Book>();
        try {
            Transaction tx = session.beginTransaction();
            Query q = session.createQuery("from Book b");
            list = (List<Book>) q.list();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return list;
    }

    public Book getById(int id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Book book = null;
        try {
            Transaction tx = session.beginTransaction();
            Query q = session.createQuery("from Book b where b.id = '" + id + "'");
            book = (Book) q.uniqueResult();
            tx = null;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return book;
    }
    
    public List<Book> getLastThree(){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        List<Book> books = null;
        try {
            Transaction tx = session.beginTransaction();
            Query q = session.createQuery("select max(bb.id) from Book bb");
            int s = (Integer) q.uniqueResult() - 3;
            Query q2 = session.createQuery("from Book b where b.id >" + s);
            books = (List<Book>) q2.list();
            tx = null;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return books;
    }

    public List<Book> getByNameAuthorISBN(String text) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        String[] split = text.split(" ");
        List<Book> books = new ArrayList<Book>();
        for (int i = 0; i > split.length; i++) {
            try {
                Transaction tx = session.beginTransaction();
                Query q = session.createQuery("from Book b where b.name = '" + split[i] + "'");
                books.add((Book) q.list());
                tx = null;
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                session.close();
            }

            try {
                Transaction tx = session.beginTransaction();
                Query q = session.createQuery("from Book b where b.author = '" + split[i] + "'");
                books.add((Book) q.list());
                tx = null;
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                session.close();
            }

            try {
                Transaction tx = session.beginTransaction();
                Query q = session.createQuery("from Book b where b.isbn = '" + split[i] + "'");
                books.add((Book) q.list());
                tx = null;
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                session.close();
            }
        }
        return books;
    }

    public void add(Book book) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            Query q = session.createQuery("from Book b where b.isbn='" + book.getIsbn() + "'");
            if ((Book) q.uniqueResult() == null) {
                session.save(book);
            }
            tx.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void edit(Book book) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            Query q = session.createQuery("from Book b where b.id='" + book.getId() + "'");
            Book bookOld = (Book) q.uniqueResult();
            if (bookOld != null) {
                if (bookOld.getId().equals(book.getId()) != true) {
                    bookOld.setId(book.getId());
                }
                if (bookOld.getIsbn() != (book.getIsbn())) {
                    bookOld.setIsbn(book.getIsbn());
                }
                if (bookOld.getAuthor().equals(book.getAuthor()) != true) {
                    bookOld.setAuthor(book.getAuthor());
                }
                if (bookOld.getTitle().equals(book.getTitle()) != true) {
                    bookOld.setTitle(book.getTitle());
                }
                if (bookOld.getDescription().equals(book.getDescription()) != true) {
                    bookOld.setDescription(book.getDescription());
                }
                if (bookOld.getState().equals(book.getState()) != true) {
                    bookOld.setState(book.getState());
                }
                if (bookOld.getCategory().equals(book.getCategory()) != true) {
                    bookOld.setCategory(book.getCategory());
                }
                session.update(bookOld);
            }
            tx.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void delete(int id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            Query q = session.createQuery("from Book b where b.id='" + id + "'");
            Book book = (Book) q.uniqueResult();
            session.delete(book);
            tx.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
