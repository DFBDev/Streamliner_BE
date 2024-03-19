## What is Streamliner?

Streamliner is a web application initially designed to automate the manual writing and transportation of tutoring records in a college environment. Specifically, it provides a streamlined way for a tutor to record student information on a web form and send it as an email to a custom location. <br><br>The repo for the view/frontend can be found [here](https://github.com/DFBDev/Streamliner_FE). <br><br>Due to its flexible nature, all of the MVC source code for Streamliner can be adapted to other needs by applying slight alterations and following the proper setup (which is further elaborated upon in the "Changing/Modifying" section of this readme). **The current state of the application is intended to be hosted locally. ALL REQUESTS, ASIDE FROM SMTP, ARE NOT CURRENTLY SSL/TLS ENABLED.**

## Specifics.
* Developed on Intellij w/ Maven build.
* Uses HTTPServer API for handling requests/responses (path: com.sun.net.httpserver.HttpServer).
* Communicates with localhost PostgreSQL database using JDBC API (path: java.sql.*).
* Uses PostgreSQL driver for JDBC (version 42.7.2, found in pom.xml dependencies).
* Uses Jakarta Mail API for SMTP connection (version 2.0.1, found in pom.xml dependencies, path: jakarta.mail.*).
* SMTP connection is established with Google SMTP server @ host smtp.gmail.com.
* No frameworks used; developed purely in Java.

## Structure.
### dataHandlers package:
* `src/main/java/dataHandlers/dbInputHandler`: Contains inputData method which directly handles insertion query execution using arguments passed from dbSubPayloadProcessor.
* `src/main/java/dataHandlers/dbRetrieveHandler`: Contains retrieveData method which directly pulls data from database and returns it as ArrayList. Used in smtpConnect for email body.
* `src/main/java/dataHandlers/dbSubPayloadProcessor`: Contains processData method which is responsible for parsing & validating payload/form data from request; passes data to dbInputHandler's inputData method for database insertion.
### reqHandlers package:
* `src/main/java/reqHandlers/dbSubmissionHandler`: Contains handle method which is responsible for providing proper headers for requests and extracting data from payload to dbSubPayloadProcessor.
* `src/main/java/smtpHandlers/smtpSubmissionHandler`: Contains handle method which provides proper headers for requests and extracts data from payload to smtpSubPayloadProcessor.
* `src/main/java/reqHandlers/faviconHandler`: Handles default favicon request.
### smtpHandlers package: 
* `src/main/java/smtpHandlers/smtpConnect`: Contains sender method which is directly responsible for establishing SMTP connection with Google host smtp.gmail.com and creating email to be sent. 
* `src/main/java/smtpHandlers/smtpSubPayloadProcessor`: Contains processData method which parses form/payload data and passes to smtpConnect's sender method.

### Code Flow Visualizer:
* dbSubmissionHandler -> dbSubPayloadProcessor -> dbInputHandler
* smtpSubmissionHandler -> smtpSubPayloadProcessor -> smtpConnect -> dbRetrieveHandler (returns data to smtpConnect)

## Releases:

v1.0.0 - First release. Contains all necessary source code required for successful database storage and SMTP transaction.

## Changing/Modifying:
**COMING SOON**
