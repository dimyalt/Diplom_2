# Дипломная работа (задание 2: API)
В рамках дипломного проекта нужно протестировать ручки API для Stellar Burgers.
____
### Проект:
#### Цель:
Это дипломный проект, для проверки навыка API тестирования
#### Технологии:
В проекте используется: JAVA 11,  JUnit 4, REST Assured, Allure.
____
### Проект:
#### Задачи:
1. Создание пользователя:
- создать уникального пользователя;
- создать пользователя, который уже зарегистрирован;
- создать пользователя и не заполнить одно из обязательных полей.
2. Логин пользователя:
- логин под существующим пользователем,
- логин с неверным логином и паролем.
3. Изменение данных пользователя:
- с авторизацией,
- без авторизации,
4. Создание заказа:
- с авторизацией,
- без авторизации,
- с ингредиентами,
- без ингредиентов,
- с неверным хешем ингредиентов.
5. Получение заказов конкретного пользователя:
- авторизованный пользователь,
- неавторизованный пользователь.
____
### Документация:
https://code.s3.yandex.net/qa-automation-engineer/java/cheatsheets/paid-track/diplom/api-documentation.pdf - все ручки сервиса.
____
### Тесты:
- Создание пользователя
- Создание дубликата пользователя
- Создание пользователя без обязательного поля "пароль"
- Создание заказа авторизованным пользователем
- Создание заказа не авторизованным пользователем
- Создание заказа с не правильными типами ингредиентов
- Изменение email авторизованного пользователя 
- Изменение пароля авторизованного пользователя
- Изменение имени авторизованного пользователя
- Изменение email не авторизованного пользователя
- Изменение пароля не авторизованного пользователя
- Изменение имени не авторизованного пользователя
- Логин пользователя
- Логин пользователя с пустым полем "Email"
- Логин пользователя с пустым полем "Password"
- Все заказы не авторизованного пользователя
- Все заказы авторизованного пользователя
____
