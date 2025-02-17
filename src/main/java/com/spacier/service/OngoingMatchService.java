package com.spacier.service;

import com.spacier.dto.MatchScoreDto;
import com.spacier.exception.NotFoundException;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@NoArgsConstructor
public class OngoingMatchService {
  private static final OngoingMatchService INSTANCE = new OngoingMatchService();
  private static final Map<UUID, MatchScoreDto> ongoingMatches = new HashMap<>();

  public static OngoingMatchService getInstance() {
    return INSTANCE;
  }

  public MatchScoreDto getMatch(UUID uuid) {
    return Optional.ofNullable(ongoingMatches.get(uuid)).orElseThrow(() -> new NotFoundException("UUID not found"));
  }

  public UUID addMatch(MatchScoreDto matchScoreDto) {
    UUID uuid = UUID.randomUUID();
    ongoingMatches.put(uuid, matchScoreDto);
    return uuid;
  }

  public void removeMatch(UUID uuid) {
    ongoingMatches.remove(uuid);
  }
}
