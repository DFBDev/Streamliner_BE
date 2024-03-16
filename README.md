## What is Streamliner?

Streamliner is a web application initially designed to automate the manual writing and transportation of tutoring records in a college environment. Specifically, it provides a streamlined way for a tutor to record student information on a web form and send it as an email to a custom location. The repo for the view/frontend can be found [here](https://github.com/DFBDev/Streamliner_FE). Due to its flexible nature, all of the MVC source code for Streamliner can be adapted to other needs by applying slight alterations and following the proper setup (which is further elaborated upon in the "Changing/Modifying" section of this readme). **The current state of the application is intended to be hosted locally. ALL REQUESTS, ASIDE FROM SMTP, ARE NOT CURRENTLY SSL/TLS ENABLED.**

## Specifics.
* Developed on Intellij w/ Maven build.
* Uses HTTPServer API for handling requests/responses (com.sun.net.httpserver.HttpServer).
* Communicates with localhost PostgreSQL database using JDBC API (java.sql.*).
* Uses PostgreSQL driver (version 42.7.2, found in pom.xml dependencies).
* Uses Jakarta Mail API for SMTP connection (version 2.0.1, found in pom.xml dependencies, jakarta.mail.*).
* SMTP connection is established with Google SMTP server @ host smtp.gmail.com.
* No frameworks used; developed purely in Java.

## Structure.
### dataHandlers package:
* **src/main/java/dataHandlers/dbInputHandler:** Directly handles query execution using parameters passed in dbSubPayloadProcessor.
* **src/main/java/dataHandlers/dbRetrieveHandler:** Directly pulls data from database and returns it in ArrayList. Used in smtpConnect for email body.
* **src/main/java/dataHandlers/dbSubPayloadProcessor:** Responsible for parsing & validating payload/form data from request; passes it to dbInputHandler for database insertion.
### reqHandlers package:
* **src/main/java/reqHandlers/dbSubmissionHandler:** Responsible for providing proper headers for requests and extracting data from payload to dbSubPayloadProcessor.
* **src/main/java/reqHandlers/faviconHandler:** Handles default favicon request.
### smtpHandlers package: 
* **src/main/java/smtpHandlers/smtpConnect:** Directly responsible for establishing SMTP connection with Google host smtp.gmail.com and creating email to be sent.  
* **src/main/java/smtpHandlers/smtpSubmissionHandler:** Provides proper headers for requests and extracts data from payload to smtpSubPayloadProcessor.
* **src/main/java/smtpHandlers/smtpSubPayloadProcessor:** Parses form/payload data and passes to smtpConnect.

## Releases:

v1.0.0 - First release. Contains all necessary source code required for successful database storage and SMTP transaction.

## Changing/Modifying:
**COMING SOON**
