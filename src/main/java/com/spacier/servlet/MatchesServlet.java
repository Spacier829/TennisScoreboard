package com.spacier.servlet;

import com.spacier.dao.MatchDaoImpl;
import com.spacier.entity.Match;
import com.spacier.service.MatchPaginationService;
import com.spacier.util.ValidationUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet("/matches")
public class MatchesServlet extends HttpServlet {
  private static final MatchPaginationService matchPaginationService = MatchPaginationService.getInstance();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String pageParameter = Optional.ofNullable(req.getParameter("page")).orElse("1");
    Optional<String> filter_by_player_name = Optional.ofNullable(req.getParameter("filter_by_player_name"));

    long totalPages = 0;
    int page = 0;
    List<Match> matches;

    if (filter_by_player_name.isPresent()) {
      String name = filter_by_player_name.get();
      ValidationUtil.validateName(name);
      totalPages = matchPaginationService.getPagesByName(name);
      page = ValidationUtil.validatePage(pageParameter, totalPages);
      matches = matchPaginationService.getMatchesByName(name, page);
      req.setAttribute("filter_by_player_name", name);
    } else {
      totalPages = matchPaginationService.getTotalPages();
      page = ValidationUtil.validatePage(pageParameter, totalPages);
      matches = matchPaginationService.getMatchesByPage(page);
    }

    req.setAttribute("matches", matches);
    req.setAttribute("currentPage", page);
    req.setAttribute("totalPages", totalPages);
    req.getRequestDispatcher("matches.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
  }
}
