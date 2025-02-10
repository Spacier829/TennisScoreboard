package com.spacier.filter;

import com.spacier.exception.InvalidParameterException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter("/")
public class ExceptionHandlerFilter extends HttpFilter {
  @Override
  protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException,
      ServletException {
    try {
      super.doFilter(req, res, chain);
    } catch (InvalidParameterException invalidParameterException) {
      req.setAttribute("error", invalidParameterException.getMessage());
      req.getRequestDispatcher("new-match.jsp").forward(req, res);
    }
  }
}
