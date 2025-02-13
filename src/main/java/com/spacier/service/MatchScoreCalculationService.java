package com.spacier.service;

import com.spacier.dto.MatchScoreDto;
import com.spacier.dto.PlayerScoreDto;

public class MatchScoreCalculationService {
  private static final int LOVE = 0;
  private static final int POINT = 1;
  private static final int WINNING_DIFFERENCE = 2;
  private static final int POINT_40 = 3;
  private static final int ADVANTAGE_POINTS = 4;
  private static final int ADVANTAGE_GAMES = 6;
  private static final int TIE_BREAK_WINNING_POINTS = 7;

  private final MatchScoreDto match;
  private PlayerScoreDto player;
  private PlayerScoreDto opponent;

  public MatchScoreCalculationService(MatchScoreDto match) {
    this.match = match;
  }

  public void calculatePlayerScore(String playerName) {
    player = match.getPlayerByName(playerName);
    opponent = match.getOpponentByName(playerName);
    if (isTieBreak()) {
      playTieBreak();
    } else {
      calculatePoints();
    }
  }

  private void calculatePoints() {
    winPoint();
    int playerPoints = player.getPoints();
    int opponentPoints = opponent.getPoints();

    if (playerPoints >= ADVANTAGE_POINTS) {
      if (playerPoints - opponentPoints >= WINNING_DIFFERENCE) {
        winGame();
        calculateGames();
      } else if (opponentPoints == POINT_40) {
        player.setPoints(ADVANTAGE_POINTS);
      } else if (opponentPoints == ADVANTAGE_POINTS) {
        player.setPoints(POINT_40);
        opponent.setPoints(POINT_40);
      }
    }
  }

  private void calculateGames() {
    int playerGames = player.getGames();
    int opponentGames = opponent.getGames();

    if (playerGames >= ADVANTAGE_GAMES) {
      if (playerGames - opponentGames >= WINNING_DIFFERENCE) {
        winSet();
      }
    }
  }

  private void playTieBreak() {
    winPoint();
    int playerPoints = player.getPoints();
    int opponentPoints = opponent.getPoints();

    if (playerPoints >= TIE_BREAK_WINNING_POINTS) {
      if (playerPoints - opponentPoints >= WINNING_DIFFERENCE) {
        winSet();
      }
    }
  }

  private boolean isTieBreak() {
    return player.getGames() == ADVANTAGE_GAMES && opponent.getGames() == ADVANTAGE_GAMES;
  }

  public boolean isMatchOver() {
    return player.getSets() == WINNING_DIFFERENCE;
  }

  public void winSet() {
    resetPoints();
    resetGames();
    player.setSets(player.getSets() + POINT);
  }

  public void winGame() {
    resetPoints();
    player.setGames(player.getGames() + POINT);
  }

  public void winPoint() {
    player.setPoints(player.getPoints() + POINT);
  }

  public void resetPoints() {
    player.setPoints(LOVE);
    opponent.setPoints(LOVE);
  }

  public void resetGames() {
    player.setGames(0);
    opponent.setGames(0);
  }
}
