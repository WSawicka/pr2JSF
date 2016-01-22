/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helper;

import Model.Message;
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
public class MessageHelper {

    public MessageHelper() {
    }

    public List<Message> getAll() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        List<Message> list = new ArrayList<Message>();
        try {
            Transaction tx = session.beginTransaction();
            Query q = session.createQuery("from Message m");
            list = (List<Message>) q.list();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return list;
    }

    public void add(Message message) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            session.save(message);
            tx.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void delete(int id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            Query q = session.createQuery("from Message m where m.id='" + id + "'");
            Message message = (Message) q.uniqueResult();
            session.delete(message);
            tx.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
