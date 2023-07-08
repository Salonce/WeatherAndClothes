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
1. Register on https://openweathermap.org/api (free version is enough) to get an API KEY for calls.
2. On https://console.cloud.google.com/apis/, create a new project and get API credentials (CLIENT ID and CLIENT SECRET) for OAuth2 (if you don't know how, there are instructions on the internet, for example here: https://developers.google.com/workspace/guides/create-credentials).
3. Provide all necessary configuration information in the <i>src/main/resources/application.properties</i> file either directly or place it in your system environment variables (by default).





