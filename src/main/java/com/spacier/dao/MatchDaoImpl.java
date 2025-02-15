package com.spacier.dao;

import com.spacier.entity.Match;
import com.spacier.exception.DataBaseException;
import com.spacier.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public class MatchDaoImpl implements Dao<Match> {
  private static final MatchDaoImpl INSTANCE = new MatchDaoImpl();
  private static final String SELECT_ALL_HQL = "from Match";
  private static final String COUNT_HQL = "SELECT COUNT(m) from Match m";
  private static final String SELECT_BY_NAME_HQL = "FROM Match m WHERE m.firstPlayer.name = " +
                                                   ":name " +
                                                   "OR m" +
                                                   ".secondPlayer.name = :name";
  private static final String COUNT_BY_NAME_HQL = "SELECT COUNT(m)" + SELECT_BY_NAME_HQL;

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

  public List<Match> findByNameWithPagination(String name, int page, int size) {
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      return session.createQuery(SELECT_BY_NAME_HQL, Match.class)
          .setFirstResult((page - 1) * size)
          .setParameter("name", name)
          .setMaxResults(size)
          .list();
    } catch (DataBaseException e) {
      throw new DataBaseException("Failed to find matches from the database");
    }
  }

  public List<Match> findAllWithPagination(int page, int size) {
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      return session.createQuery(SELECT_ALL_HQL, Match.class).setFirstResult((page - 1) * size).setMaxResults(
          size).list();
    } catch (DataBaseException e) {
      throw new DataBaseException("Failed to find matches from the database");
    }
  }

  public long getCountMatches() {
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      return session.createQuery(COUNT_HQL, Long.class).uniqueResult();
    } catch (DataBaseException e) {
      throw new DataBaseException("Failed to find matches from the database");
    }
  }

  public long getCountMatchesByName(String name) {
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      return session.createQuery(
              COUNT_BY_NAME_HQL, Long.class)
          .setParameter("name", name).uniqueResult();
    } catch (DataBaseException e) {
      throw new DataBaseException("Failed to find matches from the database");
    }
  }
}
