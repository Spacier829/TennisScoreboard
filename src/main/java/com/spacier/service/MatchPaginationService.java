package com.spacier.service;

import com.spacier.dao.MatchDaoImpl;
import com.spacier.entity.Match;
import com.spacier.exception.NotFoundException;

import java.util.List;

public class MatchPaginationService {
  private static final MatchPaginationService INSTANCE = new MatchPaginationService();
  private static final MatchDaoImpl matchDao = MatchDaoImpl.getInstance();
  private static final int MAX_PAGE_SIZE = 5;

  public static MatchPaginationService getInstance() {
    return INSTANCE;
  }

  public long getTotalPages() {
    return (matchDao.getCountMatches() + MAX_PAGE_SIZE - 1) / MAX_PAGE_SIZE;
  }

  public List<Match> getMatchesByPage(int page) {
    return matchDao.findAllWithPagination(page, MAX_PAGE_SIZE);
  }

  public List<Match> getMatchesByName(String name, int page) {
    return matchDao.findByNameWithPagination(name, page, MAX_PAGE_SIZE);
  }

  public long getPagesByName(String name) {
    long count = (matchDao.getCountMatchesByName(name) + MAX_PAGE_SIZE - 1) / MAX_PAGE_SIZE;
    if (count == 0) {
      throw new NotFoundException("No matches found with this player ");
    }
    return count;
  }
}
