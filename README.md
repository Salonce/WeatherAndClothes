# WeatherAndClothes
Application let's us check the weather in specific city/under specific coordinates and allows us store items in a wardrobe.

![2Ltadu7](https://github.com/Salonce/WeatherAndClothes/assets/27849647/b6cec21b-384e-4040-a15a-d71dcf811bd2)
![adJInF9](https://github.com/Salonce/WeatherAndClothes/assets/27849647/b05f0507-b446-4035-ad84-f5f3e2aab81a)

<b> About the project: </b>
- Uses Java with Spring Framework, Tomcat Server
- Frontend: HTML, Thymeleaf, Bootstrap 
- MySQL database (modifiable) with JPA and HIBERNATE


<b>How to run?</b> <br>
1. Create an account on https://openweathermap.org/api (free version is enough) to get an API KEY for calls.
2. Create an "application.properties" file under "src/main/resources/application.properties" where you configure the database connection and add API KEY from the website: <br>

<br>
spring.jpa.hibernate.ddl-auto=update <br>
spring.datasource.url=jdbc:mysql://${MYSQL_HOST:localhost}:3306/DATABASENAME  <br>
spring.datasource.username=USERNAME <br>
spring.datasource.password=PASSWORD <br>
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver <br>
#spring.jpa.show-sql: true <br>
<br>
#Simple properties <br>
weather-api-token.token=YOUR_WEATHER_API_KEY

<br>Example of proper configuration:
![UqJZ7xf](https://github.com/Salonce/WeatherAndClothes/assets/27849647/eb7f8780-b428-42cd-935a-ee69e50ec934)




