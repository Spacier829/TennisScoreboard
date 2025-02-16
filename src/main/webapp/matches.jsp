<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tennis Scoreboard | Finished Matches</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;700&display=swap" rel="stylesheet">
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
        <c:if test="${not empty requestScope.errorMessage}">
            <p style="color: red;">${requestScope.errorMessage}</p>
        </c:if>
        <h1>Matches</h1>
        <div class="input-container">
            <form action="${pageContext.request.contextPath}/matches" method="GET">
                <input class="input-filter" placeholder="Filter by name" type="text" name="filter_by_player_name"/>
                <button class="btn-filter">Reset Filter</button>
            </form>
        </div>


        <table class="table-matches">
            <tr>
                <th>Player One</th>
                <th>Player Two</th>
                <th>Winner</th>
            </tr>
            <c:forEach var="match" items="${requestScope.matches}">
                <tr>
                    <td>${match.firstPlayer.name}</td>
                    <td>${match.secondPlayer.name}</td>
                    <td><span class="winner-name-td">${match.winner.name}</span></td>
                </tr>
            </c:forEach>
        </table>

        <c:if test="${requestScope.totalPages > 0}">
            <div class="pagination">
                <c:if test="${requestScope.currentPage > 1}">
                    <a class="prev" href="?page=${requestScope.currentPage - 1}"> < </a>
                </c:if>

                <c:set var="startPage" value="${requestScope.currentPage - 1}"/>
                <c:set var="endPage" value="${requestScope.currentPage + 1}"/>

                <c:if test="${startPage < 1}">
                    <c:set var="startPage" value="1"/>
                    <c:set var="endPage" value="3"/>
                </c:if>

                <c:if test="${endPage > requestScope.totalPages}">
                    <c:set var="endPage" value="${requestScope.totalPages}"/>
                    <c:set var="startPage" value="${endPage - 2}"/>
                </c:if>

                <c:if test="${startPage < 1}">
                    <c:set var="startPage" value="1"/>
                </c:if>

                <c:forEach var="i" begin="${startPage}" end="${endPage}">
                    <c:choose>
                        <c:when test="${i == requestScope.currentPage}">
                            <a class="num-page current" href="#">${i}</a>
                        </c:when>
                        <c:otherwise>
                            <a class="num-page" href="?page=${i}">${i}</a>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>

                <c:if test="${requestScope.currentPage < requestScope.totalPages}">
                    <a class="next" href="?page=${requestScope.currentPage + 1}"> > </a>
                </c:if>
            </div>
        </c:if>
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
