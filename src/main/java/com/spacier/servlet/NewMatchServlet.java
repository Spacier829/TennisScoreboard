package com.spacier.servlet;

import com.spacier.dto.MatchScoreDto;
import com.spacier.dto.PlayerScoreDto;
import com.spacier.service.OngoingMatchService;
import com.spacier.util.ValidationUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/new-match")
public class NewMatchServlet extends HttpServlet {
  private final OngoingMatchService ongoingMatchService = OngoingMatchService.getInstance();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.getRequestDispatcher("new-match.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String firstPlayerName = req.getParameter("firstPlayer");
    String secondPlayerName = req.getParameter("secondPlayer");
    ValidationUtil.validatePlayerNames(firstPlayerName, secondPlayerName);

    PlayerScoreDto firstPlayer = new PlayerScoreDto(firstPlayerName);
    PlayerScoreDto secondPlayer = new PlayerScoreDto(secondPlayerName);

    MatchScoreDto matchScoreDto = new MatchScoreDto(firstPlayer, secondPlayer);
    UUID uuid = ongoingMatchService.addMatch(matchScoreDto);
    resp.sendRedirect(req.getContextPath() + "/match-score?uuid=" + uuid);
  }
}
