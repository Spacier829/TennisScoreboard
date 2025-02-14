package com.spacier.servlet;

import com.spacier.dto.MatchScoreDto;
import com.spacier.service.FinishedMatchesPersistenceService;
import com.spacier.service.MatchScoreCalculationService;
import com.spacier.service.OngoingMatchService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/match-score")
public class MatchScoreServlet extends HttpServlet {
  private final OngoingMatchService ongoingMatchService = OngoingMatchService.getInstance();
  private final FinishedMatchesPersistenceService finishedMatchesPersistenceService =
      FinishedMatchesPersistenceService.getInstance();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    UUID uuid = UUID.fromString(req.getParameter("uuid"));
    MatchScoreDto ongoingMatch = ongoingMatchService.getMatch(uuid);

    req.setAttribute("ongoingMatch", ongoingMatch);
    req.setAttribute("uuid", uuid);
    req.getRequestDispatcher("match-score.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    UUID uuid = UUID.fromString(req.getParameter("uuid"));
    String playerName = req.getParameter("playerName");
    MatchScoreDto ongoingMatch = ongoingMatchService.getMatch(uuid);
    MatchScoreCalculationService matchScore = new MatchScoreCalculationService(ongoingMatch);
    matchScore.calculatePlayerScore(playerName);
    if (matchScore.isMatchOver()) {
      finishedMatchesPersistenceService.save(ongoingMatch, playerName);
      ongoingMatchService.removeMatch(uuid);
      resp.sendRedirect("/matches");
    } else {
      req.setAttribute("uuid", uuid);
      resp.sendRedirect("/match-score?uuid=" + uuid);
    }
  }
}
