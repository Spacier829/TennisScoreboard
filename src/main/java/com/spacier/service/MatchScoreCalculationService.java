package com.spacier.service;

import com.spacier.dto.MatchScoreDto;

public class MatchScoreCalculationService {
  private final MatchScoreDto match;

  public MatchScoreCalculationService(MatchScoreDto match) {
    this.match = match;
  }

  public void calculatePlayerScore(String playerName) {
    match.getPlayerByName(playerName).setPoints(match.getPlayerByName(playerName).getPoints() + 1);
  }
}
