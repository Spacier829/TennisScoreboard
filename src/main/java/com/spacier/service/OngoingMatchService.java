package com.spacier.service;

import com.spacier.dto.MatchScoreDto;
import com.spacier.exception.NotFoundException;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@NoArgsConstructor
public class OngoingMatchService {
  @Getter
  private static final OngoingMatchService INSTANCE = new OngoingMatchService();

  private static final Map<UUID, MatchScoreDto> ongoingMatches = new HashMap<>();

  public MatchScoreDto getMatch(UUID uuid) {
    return Optional.ofNullable(ongoingMatches.get(uuid)).orElseThrow(() -> new NotFoundException("UUID not found"));
  }

  public UUID addMatch(MatchScoreDto matchScoreDto) {
    ongoingMatches.put(matchScoreDto.getUuid(), matchScoreDto);
    return matchScoreDto.getUuid();
  }

  public void removeMatch(UUID uuid) {
    ongoingMatches.remove(uuid);
  }
}
