package com.spacier.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PlayerScoreDto {
  private String name;
  private int points = 3;
  private int games = 5;
  private int sets = 1;

  public PlayerScoreDto(String name) {
    this.name = name;
  }
}
