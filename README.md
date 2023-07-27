# AutoSurvey Backend

### Sprint Log
https://docs.google.com/spreadsheets/d/131GQ0ECQZ2qMDJ8q3EOJtaPxaZ0fYK-Fzr3jG-eJOXo/edit#gid=0

### Pitch Deck
https://docs.google.com/document/d/1-v1rtQ55ZSGBe7O_RpJ1MYXa1TxsQAnLwjDPOG2lyxc/edit?pli=1#
###
Slide:
https://docs.google.com/presentation/d/1sFLdo-sCgNxrON7Qvv-SB8jJqen10MHNh7MddatoXtM/edit#slide=id.p

### Application Purpose
The project is a full-stack team management application. The backend connects to a remote database and provides an API that can be used to edit storage in the database.

### Run the application
- After cloning the project:
  - The application uses env.properties file. User must create the file and fill it with the information requested:
    - Mongo informations (user, passeod, db, cluster)
    - SIGNING_KEY: randon long alfanumeric string
    - JWT_256_BIT_SECRET: randon long alfanumeric string
  - Open it in the IDE and run the application
  - from terminal: mvn spring-boot:run 

### Json DATA

<div align=center>
 <img src="src/main/resources/json.png"/>
</div>

### Technology

The backend was built in Java using Maven and Spring Boot, then deployed through Azure, and the database was made with PostgreSQL.
