/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helper;

import Model.Rent;
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
public class RentHelper {

    public RentHelper() {}
    
    public List<Rent> getAll(){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        List<Rent> list = new ArrayList<Rent>();
        try{
            Transaction tx = session.beginTransaction();
            Query q = session.createQuery ("from Rent r");
            list = (List<Rent>) q.list();
        } catch(Exception ex){
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return list;
    }
    
    public List<Rent> getAllForUser(String user){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        List<Rent> list = new ArrayList<Rent>();
        try{
            Transaction tx = session.beginTransaction();
            Query q = session.createQuery ("from Rent r where r.user ='" + user + "'");
            list = (List<Rent>) q.list();
        } catch(Exception ex){
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return list;
    }
    
    public void add(Rent rent){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try{
            session.save(rent);
            tx.commit();
        } catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public void delete(int id){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try{
            Query q = session.createQuery("from Rent r where r.id='" + id + "'");
            Rent rent = (Rent)q.uniqueResult();
            session.delete(rent);
            tx.commit();
        } catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
