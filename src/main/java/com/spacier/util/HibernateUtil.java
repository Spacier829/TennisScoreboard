package com.spacier.util;

import com.spacier.entity.Match;
import com.spacier.entity.Player;
import lombok.Getter;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
  @Getter
  private static SessionFactory sessionFactory;

  private static SessionFactory buildSessionFactory() {
    try {
      return new Configuration()
          .configure()
          .addAnnotatedClass(Player.class)
          .addAnnotatedClass(Match.class)
          .buildSessionFactory();
    } catch (Exception exception) {
      System.err.println("SessionFactory initialization failed: " + exception);
      throw new ExceptionInInitializerError(exception);
    }
  }

  public static void closeSessionFactory() {
    if (sessionFactory != null) {
      sessionFactory.close();
    }
  }
}
