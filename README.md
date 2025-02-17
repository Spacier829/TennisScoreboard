# TennisScoreboard

Веб-приложение, реализующее табло счёта теннисного матча.

Front-end by Sergey Zhukov.

## Содержание

- [Стек](#стек)
- [Функционал](#функционал)
- [Интерфейс](#интерфейс)

## Стек

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Apache Maven](https://img.shields.io/badge/Apache%20Maven-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white)
![Hibernate](https://img.shields.io/badge/Hibernate-59666C?style=for-the-badge&logo=Hibernate&logoColor=white)
![H2](https://img.shields.io/badge/H2-59666C?style=for-the-badge)
![Apache Tomcat](https://img.shields.io/badge/apache%20tomcat-%23F8DC75.svg?style=for-the-badge&logo=apache-tomcat&logoColor=black)
![JUnit](https://img.shields.io/badge/-Junit-252529?style=for-the-badge&logoColor=FCC72B)
![HTML5](https://img.shields.io/badge/html5-%23E34F26.svg?style=for-the-badge&logo=html5&logoColor=white)
![CSS3](https://img.shields.io/badge/css3-%231572B6.svg?style=for-the-badge&logo=css3&logoColor=white)

## Функционал

<h3>Работа с матчами</h3>
<li>Создание нового матча </li>
<li>Просмотр законченных матчей, поиск матчей по именам игроков</li>
<li>Подсчёт очков в текущем матче</li>

## Интерфейс

<h3>Главная страница</h3>
<li>Ссылки, ведущие на страницы нового матча и списка завершенных матчей</li>

<h3>Страница нового матча</h3>
Адрес - `/new-match`
<li>Поля ввода имен игроков</li>

<h3>Страница счета матча</h3>
Адрес - `match-score?uuid=$match_id`    
GET параметр `uuid` содержит UUID матча
<li>Таблица с именами игроков, текущим счетом</li>
<li>Кнопки для присвоения очков игрокам</li>

<h3>Страница сыгранных матчей</h3>
Адрес - `/matches?page=$page_number&filter_by_player_name=$player_name`    
GET параметр `page` - номер страницы, по умолчанию - 1.    
GET параметр `filter_by_player_name` имя игрока, матчи которого в поиске, по умолчанию - все игроки.
<li>Постранично отображает список сыгранных матчей. Позволяет искать матчи игрока по его имени.</li>
<li>Таблица с игроками и результатом матча</li>
