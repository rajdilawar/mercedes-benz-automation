
# Mercedes-Benz Automated Test Framework

## Overview

This is an automated test framework developed using Selenium WebDriver for testing the Mercedes-Benz website. The framework is designed to perform end-to-end tests on various functionalities of the website, including navigation, car configuration, and price validation.

Following steps has been automated: 

- Open Mercedes-benz United Kingdom market
- Under “Our Models” - Select “Model: Hatchbacks”;
- Mouse over the “A Class” model available and proceed to “Build your car”
- Filter by Fuel type “Diesel”
- Take and save a screenshot of the results
- Save the value “£” of the highest and lowest price results in a text file
- Validating A Class models price are between £15,000 and £60,000"


## Prerequisites

Before running the tests, make sure you have the following installed:

- Java Development Kit (JDK) 11 or above
- Maven
- Chrome or Firefox browser (depending on the browser you want to use for testing)

## Getting Started

1. Clone the repository:

```
git clone https://github.com/rajdilawar/mercedes-benz-automation.git
cd mercedes-benz-automation
```

2. Update `config.properties` file:

    - Open `src/main/resources/config.properties` and set the `browserName` property to `chrome` or `firefox` depending on the browser you want to use for testing.

3. Build the project:

```
mvn clean install
```

## Running the Tests

To run the tests, use the following command:

```
mvn test
```

The tests will be executed using the specified browser (Chrome or Firefox) based on the configuration in `config.properties`.


## Framework Architecture

The framework follows a Page Object Model (POM) design pattern to maintain a clear separation between test code and page-specific code. The framework includes the following packages:

- `com.mercedes.base`: Contains the `InitClass` that initializes the WebDriver and reads configuration from `config.properties`.
- `com.mercedes.pages`: Contains the page classes representing different pages of the website.
- `com.mercedes.properties`: Contains the `Configuration` class to read data from `config.properties`.
- `com.mercedes.tests`: Contains the test classes that implement different test scenarios.
- `com.mercedes.util`: Contains utility classes such as `FileUtils` and `ScreenshotUtils`.

## Troubleshooting

If you encounter any issues while running the tests, make sure to check the following:

- Ensure that the correct version of the browser is installed on your system.
- Check the browser driver (chromedriver or geckodriver) version compatibility with your browser version.
- Verify that the `config.properties` file contains the correct browser name.
- Ensure you change the path to screenshot folder

## Contributors

- Rajdilawar Singh Chandehok (rajdilawar@gmail.com)
