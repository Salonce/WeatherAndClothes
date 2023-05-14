# WeatherAndClothes
Application let's us store items in our wardrobe and check the weather under specific coordinates.


<b> About the project: </b>
- Spring Boot, Spring
- Local tomcat Server
- Thymeleaf
- HTML
- Bootstrap 
- MySQL
- JPA and HIBERNATE

<b>How to run?</b> <br>
To run the application we need to create an "application.properties" file under "src/main/resources/application.properties" with tokenConfig filled as followed (mySQL database can be changed to other): <br>
 <br> 
/////////////////// <br>
spring.jpa.hibernate.ddl-auto=update <br>
spring.datasource.url=jdbc:mysql://${MYSQL_HOST:localhost}:3306/springTwo     // localhost and port are modifiable <br>
spring.datasource.username=USERNAME    // eg. spring.datasource.username=root <br>
spring.datasource.password=PASSWORD    // eg. spring.datasource.password=password12345 <br>
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver <br>
#spring.jpa.show-sql: true <br>

#Simple properties <br>
weather-api-token.token=YOUR_WEATHER_API_TOKEN  (eg. weather-api-token.token=Aa1ea30a7s3sd5f54eg3hnbnn7u18mk9    -  this token will not work) <br>
 <br> #token should be taken from your own (free) account on: <a> https://openweathermap.org/ </a> <br>
//////////////////// <br>

