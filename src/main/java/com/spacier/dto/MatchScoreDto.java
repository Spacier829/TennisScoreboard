package com.spacier.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class MatchScoreDto {
  private final UUID uuid = UUID.randomUUID();
  private PlayerDto firstPlayer;
  private PlayerDto secondPlayer;
  private int score;

  public MatchScoreDto(PlayerDto firstPlayer, PlayerDto secondPlayer) {
    this.firstPlayer = firstPlayer;
    this.secondPlayer = secondPlayer;
  }
}
