# WeatherAndClothes

Website allowing users to access and manage their
private virtual wardrobes. Additionally, it offers a way
to check weather in specific places by using an
external API. Includes testing and OAuth2.
Built in accordance to REST principles.

<b> Project involves the use of: </b><br>
<br>- Java 17
<br>- Spring, Spring Boot
<br>- HTML, Bootstrap, Thymeleaf
<br>- JPQL, Hibernate, MySQL
<br>- JUnit, Mockito, AssertJ


<b>How to run?</b> <br>
1. You will need an account on https://openweathermap.org/api (free version is enough) to get an API KEY for calls to properly configure the application in the fourth step.
2. You will also need a google account to create CLIENT ID and CLIENT SECRET for OAuth2 (if you don't know how, there are instructions on the internet, for example here: https://developers.google.com/workspace/guides/create-credentials).
3. Create an _"application.properties"_ under the path <i>"src/main/resources/application.properties"</i>.
4. In the _"application.properties"_ file, write the following to set up your database, external API and OAuth2 Security:
<br>
<br>
<br>spring.jpa.hibernate.ddl-auto=update
<br>spring.datasource.url=jdbc:mysql://${MYSQL_HOST:localhost}:3306/**_DATABASE_NAME_**
<br>spring.datasource.username=**_USERNAME_** 
<br>spring.datasource.password=**_PASSWORD_** 
<br>spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver 
<br>
<br>#WeatherAPI
<br>weather-api-token.token=**_YOUR_WEATHER_API_KEY_**
<br>
<br>#Security
<br>spring.security.oauth2.client.registration.google.client-id=**_YOUR_GOOGLE_APP_CLIENT_ID_**
<br>spring.security.oauth2.client.registration.google.client-secret=**_YOUR_GOOGLE_APP_CLIENT_SECRET_**

<br>Example of proper configuration:





