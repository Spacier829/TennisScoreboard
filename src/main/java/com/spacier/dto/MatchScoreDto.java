package com.spacier.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class MatchScoreDto {
  private PlayerScoreDto firstPlayer;
  private PlayerScoreDto secondPlayer;

  public MatchScoreDto(PlayerScoreDto firstPlayer, PlayerScoreDto secondPlayer) {
    this.firstPlayer = firstPlayer;
    this.secondPlayer = secondPlayer;
  }
}
