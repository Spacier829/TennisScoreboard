package com.spacier.filter;

import com.spacier.exception.InvalidParameterException;
import com.spacier.exception.NotFoundException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter("/*")
public class ExceptionHandlerFilter extends HttpFilter {
  @Override
  protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException,
      ServletException {
    try {
      chain.doFilter(req, res);
    } catch (InvalidParameterException e) {
      req.setAttribute("errorMessage", e.getMessage());
      String currentPage = req.getServletPath();
      if (currentPage.equals("/new-match")) {
        req.getRequestDispatcher("new-match.jsp").forward(req, res);
      } else if (currentPage.equals("/matches")) {
        req.getRequestDispatcher("matches.jsp").forward(req, res);
      } else {
        req.getRequestDispatcher("error.jsp").forward(req, res);
      }
    } catch (NotFoundException e) {
      req.setAttribute("errorMessage", e.getMessage());
      String currentPage = req.getServletPath();
      if (currentPage.equals("/matches")) {
        req.getRequestDispatcher("matches.jsp").forward(req, res);
      } else {
        req.getRequestDispatcher("error.jsp").forward(req, res);
      }
    } catch (Exception e) {
      req.setAttribute("errorMessage", e.getMessage());
      req.getRequestDispatcher("error.jsp").forward(req, res);
    }
  }
}
