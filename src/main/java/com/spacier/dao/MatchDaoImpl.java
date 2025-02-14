package com.spacier.dao;

import com.spacier.entity.Match;
import com.spacier.exception.DataBaseException;
import com.spacier.util.HibernateUtil;
import org.hibernate.Session;

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
}
