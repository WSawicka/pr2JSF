/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helper;

import Model.HibernateUtil;
import Model.RentHistory;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author mloda
 */
public class RentHistoryHelper {
    public RentHistoryHelper() {}
    
    public List<RentHistory> getAll(){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        List<RentHistory> list = new ArrayList<RentHistory>();
        try{
            Transaction tx = session.beginTransaction();
            Query q = session.createQuery ("from RentHistory rh");
            list = (List<RentHistory>) q.list();
        } catch(Exception ex){
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return list;
    }
    
    public List<RentHistory> getAllForUser(String user){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        List<RentHistory> list = new ArrayList<RentHistory>();
        try{
            Transaction tx = session.beginTransaction();
            Query q = session.createQuery ("from RentHistory rh where rh.user ='" + user + "'");
            list = (List<RentHistory>) q.list();
        } catch(Exception ex){
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return list;
    }
    
    public void add(RentHistory rentHistory){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try{
            session.save(rentHistory);
            tx.commit();
        } catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
