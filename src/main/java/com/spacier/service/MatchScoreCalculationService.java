package com.spacier.service;

import com.spacier.dto.MatchScoreDto;
import com.spacier.dto.PlayerScoreDto;

public class MatchScoreCalculationService {
  private static final int WINNING_DIFFERENCE = 2;
  private static final int POINT = 1;
  private static final int AD = 4;
  private final MatchScoreDto match;
  private PlayerScoreDto player;
  private PlayerScoreDto opponent;

  public MatchScoreCalculationService(MatchScoreDto match) {
    this.match = match;
  }

  public void calculatePlayerScore(String playerName) {
    player = match.getPlayerByName(playerName);
    opponent = match.getOpponentByName(playerName);
    calculatePlayerPoints();
  }

  private void calculatePlayerPoints() {
    player.setPoints(player.getPoints() + POINT);
    int playerPoints = player.getPoints();
    int opponentPoints = opponent.getPoints();

    if (playerPoints == 4) {
      if (playerPoints - opponentPoints >= WINNING_DIFFERENCE) {
        winSet();
      } else if (opponentPoints == 3) {
        player.setPoints(5);
      } else if (opponentPoints == 5) {
        player.setPoints(3);
        opponent.setPoints(3);
      }
    } else if (playerPoints == 6) {
      winSet();
    }
  }

  public void winSet() {
    player.setPoints(0);
    match.getOpponentByName(player.getName()).setPoints(0);
    player.setSets(player.getSets() + 1);
  }
}
