package com.spacier.dao;

import com.spacier.entity.Player;
import com.spacier.util.HibernateUtil;
import lombok.Getter;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.Optional;

public class PlayerDaoImpl implements Dao<Player> {
  @Getter
  private static final PlayerDaoImpl INSTANCE = new PlayerDaoImpl();

  private static final String FIND_BY_NAME_HQL = "FROM Player WHERE name = :name";

  @Override
  public Player add(Player player) {
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      session.beginTransaction();
      session.persist(player);
      session.getTransaction().commit();
      return player;
    } catch (RuntimeException e) {
      throw new RuntimeException("Failed to add player to the database");
    }
  }

  @Override
  public Optional<Player> findByName(String name) {
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      Query<Player> query = session.createQuery(FIND_BY_NAME_HQL, Player.class);
      query.setParameter("name", name);
      return Optional.ofNullable(query.uniqueResult());
    } catch (RuntimeException e) {
      throw new RuntimeException("Failed to find player with name " + name);
    }
  }
}
