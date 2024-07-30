# PlaywrightAPIWithJava

PlaywrightAPIWithJava is an API testing framework built with Playwright Java, integrated with Allure reporting, and using TestNG for test execution. The framework is designed to facilitate robust API testing, leveraging Lombok for reducing boilerplate code and DataFaker for generating dynamic test data.

# Table of Contents
Introduction
Features
Getting Started
Prerequisites
Installation
Running the Tests
Viewing the Reports
Project Structure
Dependencies
Contributing
License

# Introduction
PlaywrightAPIWithJava provides a powerful testing framework for API interactions, combining modern technologies and best practices to ensure high-quality software delivery.

# Features
Playwright Java: Provides a robust API for automating web interactions and testing.
Allure Reporting: Integrates detailed test reports with rich visuals.
TestNG: Manages test execution with advanced features.
Lombok: Reduces boilerplate code, making the codebase cleaner.
DataFaker: Generates dynamic and realistic test data.

# Getting Started
These instructions will help you set up and run the PlaywrightAPIWithJava framework on your local machine.

# Prerequisites
Java Development Kit (JDK): Version 11 or higher.
Maven: For managing project dependencies and building the project.
Allure Commandline: For generating and viewing test reports.

# Installation
1. Clone the repository:
git clone https://github.com/yourusername/PlaywrightAPIWithJava.git
cd PlaywrightAPIWithJava


2. Install the project dependencies using Maven:
mvn clean install

Running the Tests
To run the tests, execute:
mvn test

Alternatively, you can run the tests using the TestNG suite file located in the test-suite directory:
mvn test -DsuiteXmlFile=test-suite/testng.xml

Viewing the Reports
Generate and view detailed test reports using Allure:

1. Run the tests and generate Allure results:
   mvn test
   
2. Serve the Allure report:
   allure serve target/allure-results

The command will start a local server and open the Allure report in your default web browser.

Project Structure
The project is organized as follows:

PlaywrightAPIWithJava

PlaywrightAPIWithJava/
├── src/
│   └── test/
│       └── java/
│           └── com/
│               └── yourusername/
│                   ├── manager/
│                   │   ├── BaseTest.java
│                   │   └── RequestManager.java
│                   ├── reqres/
│                   │   ├── BookingData.java
│                   │   ├── BookingDataBuilder.java
│                   │   ├── BookingDates.java
│                   │   ├── CreateToken.java
│                   │   ├── PartialBookingData.java
│                   │   └── TokenBuilder.java
│                   └── restfulbooker/
│                       ├── RestfulBookingEnd2EndTest.java
│                       └── RestfulBookingOneTest.java
├── test-suite/
│   └── testng.xml
├── pom.xml
└── README.md


src/test/java: Contains all the test-related code.

   manager: Contains classes for managing tests.
   
      BaseTest.java: Base class for all tests.
      
      RequestManager.java: Class for managing API requests.
      
   reqres: Contains classes related to the ReqRes API.
   
      BookingData.java: Data model for booking.
      
      BookingDataBuilder.java: Builder for BookingData.
      
      BookingDates.java: Data model for booking dates.
      
      CreateToken.java: Class for creating tokens.
      
      PartialBookingData.java: Data model for partial booking.
      
      TokenBuilder.java: Builder for tokens.
      
restfulbooker: Contains test classes for the Restful Booker API.

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

Contributions are welcome! Please follow these steps:

Fork the repository.

1.Create a new branch (git checkout -b feature/your-feature).

2.Commit your changes (git commit -m 'Add some feature').

3.Push to the branch (git push origin feature/your-feature).

4.Open a pull request.

License

This project is licensed under the MIT License - see the LICENSE file for details.
