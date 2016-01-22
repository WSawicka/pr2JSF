/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helper;

import Model.Book;
import Model.Rent;
import Model.HibernateUtil;
import Model.User;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author mloda
 */
public class RentHelper {

    public RentHelper() {
    }

    public List<Rent> getAll() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        List<Rent> list = new ArrayList<Rent>();
        try {
            Transaction tx = session.beginTransaction();
            Query q = session.createQuery("from Rent r");
            list = (List<Rent>) q.list();
            tx.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public List<Rent> getAllForUser(Object object) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        List<Rent> list = new ArrayList<Rent>();
        User user = (User) object;
        try {
            Transaction tx = session.beginTransaction();
            Query q = session.createQuery("from Rent r where r.user ='" + user.getLogin() + "'");
            list = (List<Rent>) q.list();
            tx.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public boolean rentBook(Integer bookId) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        boolean result = false;
        try {
            Transaction tx = session.beginTransaction();
            Query q = session.createQuery("from Book b where b.id ='" + bookId + "'");
            Book book = (Book) q.uniqueResult();
            if (book.getState().equals("free")) {
                book.setState("taken");
                session.update(book);
                result = true;
            }
            tx.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public void add(String user, Integer bookId, Date date) {
        Rent rent = new Rent(user, bookId, date);
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            Transaction tx = session.beginTransaction();
            session.save(rent);
            tx.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void delete(int id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            Query q = session.createQuery("from Rent r where r.id='" + id + "'");
            Rent rent = (Rent) q.uniqueResult();
            session.delete(rent);
            tx.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
