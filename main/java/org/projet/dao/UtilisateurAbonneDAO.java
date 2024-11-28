package main.java.org.projet.dao;

import main.java.org.projet.model.UtilsateurAbonne;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import java.util.List;

public class UtilisateurAbonneDAO implements CRUDRepository<UtilsateurAbonne> {

    @Override
    public void create(UtilsateurAbonne utilisateurAbonne) {
        Session session = HibernateConfig.getSessionFactory().getCurrentSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.save(utilisateurAbonne);
            tx.commit();
            ErrorUtil.getInstance().setErrorCode(0);
            ErrorUtil.getInstance().setMessage("UtilisateurAbonne created successfully");
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            ErrorUtil.getInstance().setErrorCode(1);
            ErrorUtil.getInstance().setMessage("Failed to create UtilisateurAbonne");
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public UtilsateurAbonne findById(Long id) {
        UtilsateurAbonne utilisateurAbonne = null;
        Session session = HibernateConfig.getSessionFactory().openSession();

        try {
            utilisateurAbonne = session.get(UtilsateurAbonne.class, id);
            ErrorUtil.getInstance().setErrorCode(0);
        } catch (HibernateException e) {
            ErrorUtil.getInstance().setErrorCode(1);
            ErrorUtil.getInstance().setMessage("Failed to find UtilisateurAbonne");
            e.printStackTrace();
        } finally {
            session.close();
        }

        return utilisateurAbonne;
    }

    @Override
    public List<UtilsateurAbonne> findAll() {
        List<UtilsateurAbonne> utilisateurs = null;
        Session session = HibernateConfig.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<UtilsateurAbonne> criteria = cb.createQuery(UtilsateurAbonne.class);
            criteria.from(UtilsateurAbonne.class);
            utilisateurs = session.createQuery(criteria).getResultList();
            tx.commit();
            ErrorUtil.getInstance().setErrorCode(0);
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            ErrorUtil.getInstance().setErrorCode(1);
            ErrorUtil.getInstance().setMessage("Failed to fetch Utilisateurs");
            e.printStackTrace();
        } finally {
            session.close();
        }

        return utilisateurs;
    }

    @Override
    public void update(UtilsateurAbonne utilisateurAbonne) {
        Session session = HibernateConfig.getSessionFactory().getCurrentSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.update(utilisateurAbonne);
            tx.commit();
            ErrorUtil.getInstance().setErrorCode(0);
            ErrorUtil.getInstance().setMessage("UtilisateurAbonne updated successfully");
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            ErrorUtil.getInstance().setErrorCode(1);
            ErrorUtil.getInstance().setMessage("Failed to update UtilisateurAbonne");
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void delete(UtilsateurAbonne utilisateurAbonne) {
        Session session = HibernateConfig.getSessionFactory().getCurrentSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.delete(utilisateurAbonne);
            tx.commit();
            ErrorUtil.getInstance().setErrorCode(0);
            ErrorUtil.getInstance().setMessage("UtilisateurAbonne deleted successfully");
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            ErrorUtil.getInstance().setErrorCode(1);
            ErrorUtil.getInstance().setMessage("Failed to delete UtilisateurAbonne");
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public UtilsateurAbonne findByUsername(String username) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            return session.createQuery("FROM UtilsateurAbonne WHERE username = :username", UtilsateurAbonne.class)
                          .setParameter("username", username)
                          .uniqueResult();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }
}