package com.spacier.dao;

import com.spacier.entity.Match;
import com.spacier.util.HibernateUtil;
import lombok.Getter;
import org.hibernate.Session;

import java.util.Optional;

public class MatchDaoImpl implements Dao<Match> {
  @Getter
  private static final MatchDaoImpl INSTANCE = new MatchDaoImpl();

  @Override
  public Match add(Match match) {
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      session.beginTransaction();
      session.persist(match);
      session.getTransaction().commit();
      return match;
    } catch (RuntimeException e) {
      throw new RuntimeException("Failed to add match to the database");
    }
  }

  @Override
  public Optional<Match> findByName(String name) {
    return Optional.empty();
  }
}
