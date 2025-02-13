package com.spacier.service;


import com.spacier.dto.MatchScoreDto;
import com.spacier.dto.PlayerScoreDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MatchScoreCalculationServiceTest {
  private static final int LOVE = 0;
  private static final int POINT_40 = 3;
  MatchScoreDto match;
  MatchScoreCalculationService calculationService;

  @BeforeEach
  public void init() {
    match = new MatchScoreDto();
    match.setFirstPlayer(new PlayerScoreDto("Test Bob"));
    match.setSecondPlayer(new PlayerScoreDto("Test Alice"));
    calculationService = new MatchScoreCalculationService(match);
  }

  @Test
  public void shouldNotEndGameWhenPlayerScoresAtDeuce() {
    match.getFirstPlayer().setPoints(POINT_40);
    match.getSecondPlayer().setPoints(POINT_40);
    calculationService.calculatePlayerScore(match.getFirstPlayer().getName());

    assertEquals(0, match.getFirstPlayer().getGames());
    assertEquals(0, match.getSecondPlayer().getGames());
  }

  @Test
  public void shouldEndGameWhenPlayerScoresAtDeuce() {
    match.getFirstPlayer().setPoints(POINT_40);
    match.getSecondPlayer().setPoints(LOVE);
    calculationService.calculatePlayerScore(match.getFirstPlayer().getName());

    assertEquals(1, match.getFirstPlayer().getGames());
    assertEquals(0, match.getSecondPlayer().getGames());
  }

  @Test
  public void shouldStartTiebreakWhenScoreIsSixSix() {
    match.getFirstPlayer().setGames(6);
    match.getSecondPlayer().setGames(6);
    calculationService.calculatePlayerScore(match.getFirstPlayer().getName());

    assertEquals(1, match.getFirstPlayer().getPoints());
    assertEquals(0, match.getFirstPlayer().getSets());
  }

  @Test
  public void shouldWinGameIfLeadIsTwoGames() {
    match.getFirstPlayer().setGames(6);
    match.getFirstPlayer().setPoints(POINT_40);
    match.getSecondPlayer().setGames(LOVE);
    calculationService.calculatePlayerScore(match.getFirstPlayer().getName());

    assertEquals(1, match.getFirstPlayer().getSets());
  }

  @Test
  public void shouldEndMatchIfPlayerWinsTwoSets() {
    match.getFirstPlayer().setSets(1);
    match.getFirstPlayer().setGames(5);
    match.getFirstPlayer().setPoints(POINT_40);
    calculationService.calculatePlayerScore(match.getFirstPlayer().getName());

    assertTrue(calculationService.isMatchOver());
  }

  @Test
  public void shouldWinTiebreakIfPlayerLeadsByTwoPoints() {
    match.getFirstPlayer().setGames(6);
    match.getFirstPlayer().setPoints(11);
    match.getSecondPlayer().setGames(6);
    match.getSecondPlayer().setPoints(10);
    calculationService.calculatePlayerScore(match.getFirstPlayer().getName());

    assertAll(() -> assertEquals(1, match.getFirstPlayer().getSets()),
        () -> assertEquals(0, match.getSecondPlayer().getSets()));
  }

  @Test
  public void shouldResetScoreOnGameWin() {
    match.getFirstPlayer().setPoints(POINT_40);
    match.getFirstPlayer().setGames(6);
    match.getSecondPlayer().setGames(2);
    calculationService.calculatePlayerScore(match.getFirstPlayer().getName());

    assertAll(() -> assertEquals(1, match.getFirstPlayer().getSets()),
        () -> assertEquals(0, match.getFirstPlayer().getPoints()),
        () -> assertEquals(0, match.getFirstPlayer().getGames()),
        () -> assertEquals(0, match.getSecondPlayer().getPoints()),
        () -> assertEquals(0, match.getSecondPlayer().getGames()),
        () -> assertEquals(0, match.getSecondPlayer().getSets()));
  }
}
