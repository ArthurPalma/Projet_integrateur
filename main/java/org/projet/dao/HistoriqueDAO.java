package main.java.org.projet.dao;

import main.java.org.projet.model.Historique;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.List;

public class HistoriqueDAO implements CRUDRepository<Historique> {
    @Override
    public void create(Historique historique) {
        Session session = HibernateConfig.getSessionFactory().getCurrentSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.save(historique);
            tx.commit();
            ErrorUtil.getInstance().setErrorCode(0);
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            ErrorUtil.getInstance().setErrorCode(1);
            ErrorUtil.getInstance().setMessage("Failed to create Historique");
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public Historique findById(Long id) {
        Historique historique = null;
        Session session = HibernateConfig.getSessionFactory().openSession();

        try {
            historique = session.get(Historique.class, id);
            ErrorUtil.getInstance().setErrorCode(0);
        } catch (HibernateException e) {
            ErrorUtil.getInstance().setErrorCode(1);
            ErrorUtil.getInstance().setMessage("Failed to find Historique");
            e.printStackTrace();
        } finally {
            session.close();
        }

        return historique;
    }

    @Override
    public List<Historique> findAll() {
        List<Historique> historiques = new ArrayList<>();
        Session session = HibernateConfig.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Historique> criteria = cb.createQuery(Historique.class);
            criteria.select(criteria.from(Historique.class));
            historiques = session.createQuery(criteria).getResultList();
            tx.commit();
            ErrorUtil.getInstance().setErrorCode(0);
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            ErrorUtil.getInstance().setErrorCode(1);
            ErrorUtil.getInstance().setMessage("Failed to fetch all Historique records");
            e.printStackTrace();
        } finally {
            session.close();
        }

        return historiques;
    }

    @Override
    public void update(Historique historique) {
        Session session = HibernateConfig.getSessionFactory().getCurrentSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.update(historique);
            tx.commit();
            ErrorUtil.getInstance().setErrorCode(0);
            ErrorUtil.getInstance().setMessage("Update successful");
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            ErrorUtil.getInstance().setErrorCode(1);
            ErrorUtil.getInstance().setMessage("Failed to update Historique");
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void delete(Historique historique) {
        Session session = HibernateConfig.getSessionFactory().getCurrentSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.delete(historique);
            tx.commit();
            ErrorUtil.getInstance().setErrorCode(0);
            ErrorUtil.getInstance().setMessage("Delete successful");
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            ErrorUtil.getInstance().setErrorCode(1);
            ErrorUtil.getInstance().setMessage("Failed to delete Historique");
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
