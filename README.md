PlaywrightAPIWithJava
This repository contains an API testing framework built using Playwright Java dependency, integrated with Allure reporting for comprehensive test results and analysis. The framework utilizes TestNG for test execution, Lombok for boilerplate code reduction, and DataFaker for generating test data.

Table of Contents
Getting Started
Prerequisites
Installation
Running the Tests
Viewing the Reports
Project Structure
Dependencies
Contributing
License
Getting Started
These instructions will help you set up and run the API testing framework on your local machine for development and testing purposes.

Prerequisites
Java Development Kit (JDK) 11 or higher
Maven
Allure Commandline
Installation
Clone the repository:

sh
Copy code
git clone https://github.com/yourusername/PlaywrightAPIWithJava.git
cd PlaywrightAPIWithJava
Install the project dependencies using Maven:

sh
Copy code
mvn clean install
Running the Tests
To run the tests, use the following command:

sh
Copy code
mvn test
Alternatively, you can run the tests using the TestNG suite file:

sh
Copy code
mvn test -DsuiteXmlFile=test-suite/testng.xml
Viewing the Reports
Allure reporting is integrated to provide detailed test reports. To generate and view the reports, follow these steps:

Run the tests and generate Allure results:

sh
Copy code
mvn test
Serve the Allure report:

sh
Copy code
allure serve target/allure-results
This command will start a local server and open the Allure report in your default web browser.

Project Structure
Here's an overview of the project's structure:

bash
Copy code
PlaywrightAPIWithJava
├── src
│   └── test
│       └── java
│           └── com
│               └── yourusername
│                   ├── manager
│                   │   ├── BaseTest.java
│                   │   └── RequestManager.java
│                   ├── reqres
│                   │   ├── BookingData.java
│                   │   ├── BookingDataBuilder.java
│                   │   ├── BookingDates.java
│                   │   ├── CreateToken.java
│                   │   ├── PartialBookingData.java
│                   │   └── TokenBuilder.java
│                   └── restfulbooker
│                       ├── RestfulBookingEnd2EndTest.java
│                       └── RestfulBookingOneTest.java
├── test-suite
│   └── testng.xml
├── pom.xml
└── README.md
src/test/java: Contains all the test-related code.
manager: Contains classes for managing tests.
BaseTest.java: Base class for all tests.
RequestManager.java: Class for managing API requests.
reqres: Contains classes related to ReqRes API.
BookingData.java: Data model for booking.
BookingDataBuilder.java: Builder for BookingData.
BookingDates.java: Data model for booking dates.
CreateToken.java: Class for creating tokens.
PartialBookingData.java: Data model for partial booking.
TokenBuilder.java: Builder for tokens.
restfulbooker: Contains test classes for Restful Booker API.
RestfulBookingEnd2EndTest.java: End-to-end test class for Restful Booker.
RestfulBookingOneTest.java: Single test class for Restful Booker.
test-suite: Contains the TestNG suite file.
testng.xml: Configuration file for running the TestNG tests.
Dependencies
Playwright Java: For API testing.
TestNG: For test execution.
Lombok: For reducing boilerplate code.
DataFaker: For generating random test data.
Allure: For test reporting.
Contributing
We welcome contributions to improve this project. Please fork the repository and create a pull request for any changes you propose.

Fork the repository.
Create a new branch (git checkout -b feature/your-feature).
Commit your changes (git commit -m 'Add some feature').
Push to the branch (git push origin feature/your-feature).
Open a pull request.
License
This project is licensed under the MIT License - see the LICENSE file for details.

