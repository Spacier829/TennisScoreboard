package com.spacier.util;

import com.spacier.exception.InvalidParameterException;

public class ValidationUtil {
  public static void validatePlayerNames(String firstPlayer, String secondPlayer) {
    validateName(firstPlayer);
    validateName(secondPlayer);
    if (firstPlayer.equals(secondPlayer)) {
      throw new InvalidParameterException("Names must be different");
    }
  }

  private static void validateName(String name) {
    if (name.isEmpty() || name.isBlank()) {
      throw new InvalidParameterException("Name cannot be empty");
    }
    if (!name.matches("^[A-Z]\\. [A-Za-z]{1,98}$")) {
      throw new InvalidParameterException("Name must match the example: J. Doe");
    }
  }
}
