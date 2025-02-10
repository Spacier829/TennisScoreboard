package com.spacier.dao;

import java.util.Optional;

public interface Dao<T> {
  T add(T t);

  Optional<T> findByName(String name);
}
