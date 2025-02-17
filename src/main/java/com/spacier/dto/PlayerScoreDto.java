package com.spacier.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PlayerScoreDto {
  private String name;
  private int points = 0;
  private int games = 0;
  private int sets = 0;

  public PlayerScoreDto(String name) {
    this.name = name;
  }
}
