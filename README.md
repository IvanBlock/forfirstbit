# forfirstbit
Разработан веб-сервис, который раз в день(в 5.00 утра по МСК(в 2.00 ночи на разворачиваем хосте)) заходит на "https://habrahabr.ru/search/?target_type=posts&q=java&order_by=date",
забирает 10 новых новостей, заносит их в БД, и формирует веб страницу с новостями(http://forfirstbit.jelastic.regruhosting.ru/).
Если в заголовке новости есть слово "java", формирует email с с данной новостью и отсылает на почту testmywebservice888@gmail.com.(данный функционал работает локально, но не работает на сервере,
т.к. необходим платный вариант резервирования хоста)
Использованы следующие технологии:
Back end: Spring Boot, JDBC, Mail API
Front end: Thymeleaf, Twitter Bootstrap.
