package main.java.org.projet.dao;

import main.java.org.projet.data.Film;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.projet.model.Film;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;

public class FilmDAO implements CRUDRepository<Film> {
    private Connection connection;

    public FilmDAO(Connection connection) {
        this.connection = connection;
    }

    // @Override
    // public void create(Film film) {
    // String sql = "INSERT INTO Film (titre, realisateur, genre, dispoPhysique,
    // dateSortie) VALUES (?, ?, ?, ?, ?)";
    // try (PreparedStatement stmt = connection.prepareStatement(sql)) {
    // stmt.setString(1, film.getTitre());
    // stmt.setString(2, film.getRealisateur());
    // stmt.setString(3, film.getGenre());
    // stmt.setBoolean(4, film.isDispoPhysique());
    // stmt.setDate(5, film.getDateSortie());
    // stmt.executeUpdate();
    // } catch (SQLException e) {
    // e.printStackTrace();
    // }
    // }

    @Override
    public Film findById(Long id) {

        Film film = null;
        Session session = HibernateConfig.getSessionFactory();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            film = session.get(Film.class, id);
            tx.commit();
            ErrorUtil.getInstance().setErrorCode(0);
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void update(Film film) {
        Session session = HibernateConfig.getSessionFactory().getCurrentSession();
        Transaction tx = null;
    
        try {
            tx = session.beginTransaction();
            session.update(film); 
            tx.commit();
            ErrorUtil.getInstance().setErrorCode(0);
            ErrorUtil.getInstance().setMessage("Update successful");
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            ErrorUtil.getInstance().setErrorCode(1);
            ErrorUtil.getInstance().setMessage("Something went wrong during update");
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void update(Film film) {
        String sql = "UPDATE Film SET titre = ?, realisateur = ?, genre = ?, dispoPhysique = ?, dateSortie = ? WHERE idFilm = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, film.getTitre());
            stmt.setString(2, film.getRealisateur());
            stmt.setString(3, film.getGenre());
            stmt.setBoolean(4, film.isDispoPhysique());
            stmt.setDate(5, film.getDateSortie());
            stmt.setLong(6, film.getIdFilm());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Film film) {
        Session session = HibernateConfig.getSessionFactory().getCurrentSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.delete(t);
            tx.commit();

            ErrorUtil.getInstance().setErrorCode(0);
            ErrorUtil.getInstance().setMessage("Delete successfully");

        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }

            ErrorUtil.getInstance().setErrorCode(1);
            ErrorUtil.getInstance().setMessage("Something went wrong");

            e.printStackTrace();
        } finally {
            // session.close();
        }

    }
}
