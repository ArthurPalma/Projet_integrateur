package main.java.org.projet.dao;

import main.java.org.projet.model.UtilisateurNonAbonne;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;

import java.util.List;

public class UtilisateurNonAbonneDAO implements CRUDRepository<UtilisateurNonAbonne> {

    @Override
    public void create(UtilisateurNonAbonne utilisateurNonAbonne) {
        Transaction tx = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.save(utilisateurNonAbonne);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public UtilisateurNonAbonne findById(Long id) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            return session.get(UtilisateurNonAbonne.class, id);
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<UtilisateurNonAbonne> findAll() {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<UtilisateurNonAbonne> criteria = cb.createQuery(UtilisateurNonAbonne.class);
            criteria.from(UtilisateurNonAbonne.class);
            return session.createQuery(criteria).getResultList();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(UtilisateurNonAbonne utilisateurNonAbonne) {
        Transaction tx = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.update(utilisateurNonAbonne);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void delete(UtilisateurNonAbonne utilisateurNonAbonne) {
        Transaction tx = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.delete(utilisateurNonAbonne);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }
}
