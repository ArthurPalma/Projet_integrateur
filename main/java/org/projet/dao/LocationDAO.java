package main.java.org.projet.dao;
import main.java.org.projet.model.Location;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import java.util.List;

public class LocationDAO implements CRUDRepository<Location> {

    @Override
    public void create(Location location) {
        Session session = HibernateConfig.getSessionFactory().getCurrentSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.save(location);
            tx.commit();
            ErrorUtil.getInstance().setErrorCode(0);
            ErrorUtil.getInstance().setMessage("Location created successfully");
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            ErrorUtil.getInstance().setErrorCode(1);
            ErrorUtil.getInstance().setMessage("Failed to create Location");
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public Location findById(Long id) {
        Location location = null;
        Session session = HibernateConfig.getSessionFactory().openSession();

        try {
            location = session.get(Location.class, id);
            ErrorUtil.getInstance().setErrorCode(0);
        } catch (HibernateException e) {
            ErrorUtil.getInstance().setErrorCode(1);
            ErrorUtil.getInstance().setMessage("Failed to find Location");
            e.printStackTrace();
        } finally {
            session.close();
        }

        return location;
    }

    @Override
    public List<Location> findAll() {
        List<Location> locations = null;
        Session session = HibernateConfig.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Location> criteria = cb.createQuery(Location.class);
            criteria.from(Location.class);
            locations = session.createQuery(criteria).getResultList();
            tx.commit();
            ErrorUtil.getInstance().setErrorCode(0);
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            ErrorUtil.getInstance().setErrorCode(1);
            ErrorUtil.getInstance().setMessage("Failed to fetch locations");
            e.printStackTrace();
        } finally {
            session.close();
        }

        return locations;
    }

    @Override
    public void update(Location location) {
        Session session = HibernateConfig.getSessionFactory().getCurrentSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.update(location);
            tx.commit();
            ErrorUtil.getInstance().setErrorCode(0);
            ErrorUtil.getInstance().setMessage("Location updated successfully");
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            ErrorUtil.getInstance().setErrorCode(1);
            ErrorUtil.getInstance().setMessage("Failed to update Location");
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void delete(Location location) {
        Session session = HibernateConfig.getSessionFactory().getCurrentSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.delete(location);
            tx.commit();
            ErrorUtil.getInstance().setErrorCode(0);
            ErrorUtil.getInstance().setMessage("Location deleted successfully");
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            ErrorUtil.getInstance().setErrorCode(1);
            ErrorUtil.getInstance().setMessage("Failed to delete Location");
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}