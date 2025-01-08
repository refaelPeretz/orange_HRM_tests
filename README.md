# OrangeHRM-Testing Project

This repository contains automation tests for the OrangeHRM system using **Java Selenium**. The project is organized following the **Page Object Model** (POM) structure and generates a detailed report using **Extent Reports**. The repository aims to validate core functionalities of the OrangeHRM system through comprehensive test scenarios.

## Project Features
- **Page Object Model (POM)**: Ensures better maintainability and reusability of the code.
- **Automated Tests**:
  - Login and logout functionality tests.
  - Validation of user permissions and dashboard sections.
  - Employee creation and information updates.
  - Error handling for invalid inputs.
- **Reporting**: Detailed test reports are generated using **Extent Reports**, including logs, steps, and screenshots of failed tests.
- **Error Handling**: Automatic screenshot capture for failed test cases.
- **Cross-Browser Testing**: Supports Chrome and Firefox browsers.

## Project Structure
- **Pages**: Contains page classes (e.g., `LoginPage`, `AdminPage`, `PIMPage`, `UserPage`) that encapsulate the elements and methods for interacting with the OrangeHRM system.
- **Tests**: Contains test classes implementing various test scenarios:
  - `LoginTest`: Verifies login functionality for Admin and regular users.
  - `InvalidLoginTest`: Tests the system's response to invalid credentials.
  - `addUserWithoutPasswordTest`: Checks validation for creating users without passwords.
  - `AdminDashboardFieldValidation`: Validates the tabs and sections visible to Admin users.
  - `EditEmployeeInfoTest`: Verifies employee information updates.
  - `LogoutAndReLoginTest`: Ensures proper logout and re-login functionality.
- **Resources**: Test scenarios are documented as Word or Excel files for easy reference.
- **Base Classes**:
  - `BaseDriver`: Manages WebDriver initialization, browser configuration, and screenshot functionality.
  - `ExtentReportManager`: Handles report generation and test logging.

## Prerequisites
To set up and run the project, ensure the following are installed:
- Java JDK 8 or higher
- Maven
- Selenium WebDriver
- JUnit 5
- Extent Reports library
- Browser drivers: ChromeDriver or GeckoDriver (Firefox)

## Running the Tests
1. Clone the repository:
   ```bash
   git clone https://github.com/refaelPeretz/orange_HRM_tests.git

