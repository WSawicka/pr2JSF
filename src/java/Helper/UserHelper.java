/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helper;

import Model.HibernateUtil;
import Model.User;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author mloda
 */
public class UserHelper {

    public UserHelper() {
    }

    public List<User> getAll() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        List<User> list = new ArrayList<User>();
        try {
            Transaction tx = session.beginTransaction();
            Query q = session.createQuery("select u.login from User u");
            list = (List<User>) q.list();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return list;
    }
    
    public User getByLogin(String login){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        User user = null;
        try {
            Transaction tx = session.beginTransaction();
            Query q = session.createQuery("from User u where u.login='" + login + "'");
            user = (User) q.uniqueResult();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return user;
    }

    public List<User> getNormal() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        List<User> list = new ArrayList<User>();
        try {
            Transaction tx = session.beginTransaction();
            Query q = session.createQuery("from User u where u.type ='normal' and u.confirmed='yes'");
            list = (List<User>) q.list();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return list;
    }

    public List<User> getEmployee() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        List<User> list = new ArrayList<User>();
        try {
            Transaction tx = session.beginTransaction();
            Query q = session.createQuery("from User u where u.type ='emp' and u.confirmed='yes'");
            list = (List<User>) q.list();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return list;
    }

    public List<User> getAdmin() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        List<User> list = new ArrayList<User>();
        try {
            Transaction tx = session.beginTransaction();
            Query q = session.createQuery("from User u where u.type ='admin' and u.confirmed='yes'");
            list = (List<User>) q.list();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return list;
    }

    public User getByLoginPassword(String login, String password) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        User user = null;
        try {
            Transaction tx = session.beginTransaction();
            Query q = session.createQuery("from User u where u.login='" + login + "' and password='" + password + "'");
            user = (User) q.uniqueResult();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return user;
    }
    
    public List<User> getNotConfirmed(){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        List<User> list = new ArrayList<User>();
        try {
            Transaction tx = session.beginTransaction();
            Query q = session.createQuery("from User u where u.confirmed='no'");
            list = (List<User>) q.list();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return list;
    }

    public void add(User user) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            Transaction tx = session.beginTransaction();
            Query q = session.createQuery("from User u where u.login='" + user.getLogin()
                    + "' and password='" + user.getPassword() + "' and address ='" + user.getAddress() + "'");
            if ((User) q.uniqueResult() == null) {
                session.save(user);
                tx.commit();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void confirm(int id){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            Query q = session.createQuery("from User u where u.id='" + id + "'");
            User user = (User) q.uniqueResult();
            user.setConfirmed("yes");
            session.update(user);
            tx.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void delete(int id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            Query q = session.createQuery("from User u where u.id='" + id + "'");
            User user = (User) q.uniqueResult();
            session.delete(user);
            tx.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
