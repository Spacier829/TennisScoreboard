<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tennis Scoreboard | Match Score</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;700&display=swap" rel="stylesheet">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Roboto+Mono:wght@300&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="css/style.css">

    <script src="js/app.js"></script>
</head>
<body>
<header class="header">
    <section class="nav-header">
        <div class="brand">
            <div class="nav-toggle">
                <img src="images/menu.png" alt="Logo" class="logo">
            </div>
            <span class="logo-text">TennisScoreboard</span>
        </div>
        <div>
            <nav class="nav-links">
                <a class="nav-link" href="${pageContext.request.contextPath}/">Home</a>
                <a class="nav-link" href="${pageContext.request.contextPath}/matches">Matches</a>
            </nav>
        </div>
    </section>
</header>
<main>
    <div class="container">
        <h1>Current match</h1>
        <div class="current-match-image"></div>
        <section class="score">
            <table class="table">
                <thead class="result">
                <tr>
                    <th class="table-text">Player</th>
                    <th class="table-text">Sets</th>
                    <th class="table-text">Games</th>
                    <th class="table-text">Points</th>
                </tr>
                </thead>
                <tbody>
                <tr class="player1">
                    <td class="table-text">${requestScope.ongoingMatch.firstPlayer.name}</td>
                    <td class="table-text">${requestScope.ongoingMatch.firstPlayer.sets}</td>
                    <td class="table-text">${requestScope.ongoingMatch.firstPlayer.games}</td>
                    <td class="table-text">
                        <c:choose>
                            <c:when test="${requestScope.ongoingMatch.firstPlayer.games == 6 &&
                            requestScope.ongoingMatch.secondPlayer.games == 6}">
                                ${requestScope.ongoingMatch.firstPlayer.points}
                            </c:when>
                            <c:otherwise>
                                <c:choose>
                                    <c:when test="${requestScope.ongoingMatch.firstPlayer.points == 0}">0</c:when>
                                    <c:when test="${requestScope.ongoingMatch.firstPlayer.points == 1}">15</c:when>
                                    <c:when test="${requestScope.ongoingMatch.firstPlayer.points == 2}">30</c:when>
                                    <c:when test="${requestScope.ongoingMatch.firstPlayer.points == 3}">40</c:when>
                                    <c:when test="${requestScope.ongoingMatch.firstPlayer.points == 4}">AD</c:when>
                                    <c:otherwise>${requestScope.ongoingMatch.firstPlayer.points}</c:otherwise>
                                </c:choose>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td class="table-text">
                        <form action="${pageContext.request.contextPath}/match-score" method="POST">
                            <input type="hidden" name="uuid" value="${requestScope.uuid}">
                            <input type="hidden" name="playerName"
                                   value="${requestScope.ongoingMatch.firstPlayer.name}">
                            <button type="submit" class="score-btn">Score</button>
                        </form>
                    </td>
                </tr>
                <tr class="player2">
                    <td class="table-text">${requestScope.ongoingMatch.secondPlayer.name}</td>
                    <td class="table-text">${requestScope.ongoingMatch.secondPlayer.sets}</td>
                    <td class="table-text">${requestScope.ongoingMatch.secondPlayer.games}</td>
                    <td class="table-text">
                        <c:choose>
                            <c:when test="${requestScope.ongoingMatch.firstPlayer.games == 6 &&
                            requestScope.ongoingMatch.secondPlayer.games == 6}">
                                ${requestScope.ongoingMatch.secondPlayer.points}
                            </c:when>
                            <c:otherwise>
                                <c:choose>
                                    <c:when test="${requestScope.ongoingMatch.secondPlayer.points == 0}">0</c:when>
                                    <c:when test="${requestScope.ongoingMatch.secondPlayer.points == 1}">15</c:when>
                                    <c:when test="${requestScope.ongoingMatch.secondPlayer.points == 2}">30</c:when>
                                    <c:when test="${requestScope.ongoingMatch.secondPlayer.points == 3}">40</c:when>
                                    <c:when test="${requestScope.ongoingMatch.secondPlayer.points == 4}">AD</c:when>
                                    <c:otherwise>${requestScope.ongoingMatch.secondPlayer.points}</c:otherwise>
                                </c:choose>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td class="table-text">
                        <form action="${pageContext.request.contextPath}/match-score" method="POST">
                            <input type="hidden" name="uuid" value="${requestScope.uuid}">
                            <input type="hidden" name="playerName"
                                   value="${requestScope.ongoingMatch.secondPlayer.name}">
                            <button type="submit" class="score-btn">Score</button>
                        </form>
                    </td>
                </tr>
                </tbody>
            </table>
        </section>
    </div>
</main>
<footer>
    <div class="footer">
        <p>&copy; Tennis Scoreboard, project from <a href="https://zhukovsd.github.io/java-backend-learning-course/">zhukovsd/java-backend-learning-course</a>
            roadmap.</p>
    </div>
</footer>
</body>
</html>
