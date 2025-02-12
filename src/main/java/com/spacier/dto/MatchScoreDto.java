package com.spacier.dto;

import com.spacier.exception.NotFoundException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

  public PlayerScoreDto getPlayerByName(String playerName) {
    if (firstPlayer.getName().equals(playerName)) {
      return firstPlayer;
    } else if (secondPlayer.getName().equals(playerName)) {
      return secondPlayer;
    }
    throw new NotFoundException("Player not found");
  }

  public PlayerScoreDto getOpponentByName(String playerName) {
    if (firstPlayer.getName().equals(playerName)) {
      return secondPlayer;
    } else if (secondPlayer.getName().equals(playerName)) {
      return firstPlayer;
    }
    throw new NotFoundException("Player not found");
  }
}
