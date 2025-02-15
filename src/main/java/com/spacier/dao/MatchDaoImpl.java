package com.spacier.dao;

import com.spacier.entity.Match;
import com.spacier.exception.DataBaseException;
import com.spacier.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public class MatchDaoImpl implements Dao<Match> {
  private static final MatchDaoImpl INSTANCE = new MatchDaoImpl();

  public static MatchDaoImpl getInstance() {
    return INSTANCE;
  }

  @Override
  public Match add(Match match) {
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      session.beginTransaction();
      session.persist(match);
      session.getTransaction().commit();
      return match;
    } catch (RuntimeException e) {
      throw new DataBaseException("Failed to add match to the database");
    }
  }

  @Override
  public Optional<Match> findByName(String name) {
    return Optional.empty();
  }

  public List<Match> findAll() {
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      return session.createQuery("from Match", Match.class).getResultList();
    } catch (DataBaseException e) {
      throw new DataBaseException("Failed to find matches from the database");
    }
  }

  public List<Match> findAllWithPagination(int page, int size) {
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      return session.createQuery("from Match", Match.class).setFirstResult((page - 1) * size).setMaxResults(
          size).list();
    } catch (DataBaseException e) {
      throw new DataBaseException("Failed to find matches from the database");
    }
  }

  public long getCountMatches(){
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      return session.createQuery("SELECT COUNT(m) from Match m", Long.class).uniqueResult();
    } catch (DataBaseException e) {
      throw new DataBaseException("Failed to find matches from the database");
    }
  }
}
