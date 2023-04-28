# WeatherAndClothes
Spring web application for storing clothes and checking the weather.

Application let's us store items in our wardrobe and check the weather under specific coordinates.


This application uses the following:
- Spring Boot, Spring
- Local tomcat Server
- Thymeleaf
- HTML
- Bootstrap 
- MySQL
- JPA and HIBERNATE


How to run:
///////////////////
To run the application we need to create an "application.properties" file under "src/main/resources/application.properties" with configuration filled as followed (mySQL database can be changed to other):
///////////////////
spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=jdbc:mysql://${MYSQL_HOST:localhost}:3306/springTwo     // localhost and port are modifiable
spring.datasource.username=USERNAME    // eg. spring.datasource.username=root
spring.datasource.password=PASSWORD    // eg. spring.datasource.password=password12345
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
#spring.jpa.show-sql: true

#Simple properties
weather-api-token.token=YOUR_WEATHER_API_TOKEN  (eg. weather-api-token.token=Aa1ea30a7s3sd5f54eg3hnbnn7u18mk9    -  this token will not work)
////////////////////
