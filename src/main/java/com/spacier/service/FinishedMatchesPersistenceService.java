package com.spacier.service;

import com.spacier.dao.MatchDaoImpl;
import com.spacier.dao.PlayerDaoImpl;
import com.spacier.dto.MatchScoreDto;
import com.spacier.dto.PlayerScoreDto;
import com.spacier.entity.Match;
import com.spacier.entity.Player;

public class FinishedMatchesPersistenceService {
  private static final FinishedMatchesPersistenceService INSTANCE = new FinishedMatchesPersistenceService();

  private static final MatchDaoImpl matchDao = MatchDaoImpl.getInstance();
  private static final PlayerDaoImpl playerDao = PlayerDaoImpl.getInstance();

  public static FinishedMatchesPersistenceService getInstance() {
    return INSTANCE;
  }

  public void saveMatch(MatchScoreDto match, String winnerName) {
    Player firstPlayer = playerDao.findByName(match.getFirstPlayer().getName()).orElseGet(
        () -> savePlayer(match.getFirstPlayer()));
    Player secondPlayer = playerDao.findByName(match.getSecondPlayer().getName()).orElseGet(
        () -> savePlayer(match.getSecondPlayer()));

    Player winner;
    if (winnerName.equals(firstPlayer.getName())) {
      winner = firstPlayer;
    } else {
      winner = secondPlayer;
    }

    Match finishedMatch = Match
        .builder()
        .firstPlayer(firstPlayer)
        .secondPlayer(secondPlayer)
        .winner(winner).build();

    matchDao.add(finishedMatch);
  }

  public Player savePlayer(PlayerScoreDto playerDto) {
    Player player = Player
        .builder()
        .name(playerDto.getName())
        .build();
    return playerDao.add(player);
  }
}
