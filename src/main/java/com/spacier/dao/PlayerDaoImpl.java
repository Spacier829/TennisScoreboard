package com.spacier.dao;

import com.spacier.entity.Player;
import com.spacier.exception.DataBaseException;
import com.spacier.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.Optional;

public class PlayerDaoImpl implements Dao<Player> {
  private static final PlayerDaoImpl INSTANCE = new PlayerDaoImpl();

  private static final String FIND_BY_NAME_HQL = "FROM Player WHERE name = :name";

  public static PlayerDaoImpl getInstance() {
    return INSTANCE;
  }

  @Override
  public Player add(Player player) {
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      session.beginTransaction();
      session.persist(player);
      session.getTransaction().commit();
      return player;
    } catch (DataBaseException e) {
      throw new DataBaseException("Failed to add player to the database");
    }
  }

  @Override
  public Optional<Player> findByName(String name) {
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      Query<Player> query = session.createQuery(FIND_BY_NAME_HQL, Player.class);
      query.setParameter("name", name);
      return Optional.ofNullable(query.uniqueResult());
    } catch (DataBaseException e) {
      throw new DataBaseException("Failed to find player with name " + name);
    }
  }
}
