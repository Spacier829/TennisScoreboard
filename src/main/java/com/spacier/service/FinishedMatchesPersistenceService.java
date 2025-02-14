package com.spacier.service;

import com.spacier.dao.MatchDaoImpl;
import com.spacier.dto.MatchScoreDto;
import com.spacier.entity.Match;
import com.spacier.entity.Player;

public class FinishedMatchesPersistenceService {
  private static final FinishedMatchesPersistenceService INSTANCE = new FinishedMatchesPersistenceService();
  private static final MatchDaoImpl matchDao = MatchDaoImpl.getInstance();

  public static FinishedMatchesPersistenceService getInstance() {
    return INSTANCE;
  }

  public void save(MatchScoreDto match, String winnerName) {
    Player firstPlayer = Player
        .builder()
        .name(match.getFirstPlayer().getName())
        .build();
    Player secondPlayer = Player.builder()
        .name(match.getSecondPlayer().getName())
        .build();
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
}
