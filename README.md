# WeatherBot
Бот, возвращающий погодные условия для выбранного пользователем города.
Данные берутся из Openweather Api в формате JSON. Десериализуются в объект WeatherResponse производится с помощью Jackson Framework.
Выбранный пользователем город хранится в базе данных.
Работа с базой данных осуществляется с помощью Spring Data JPA и Hibernate.
Dependency injection происходит с помощью средств Spring Core.
