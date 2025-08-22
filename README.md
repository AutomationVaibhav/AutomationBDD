# 🚀 Enterprise Automation Framework

![Build](https://img.shields.io/badge/build-passing-brightgreen)
![License](https://img.shields.io/badge/license-MIT-blue.svg)
![Java](https://img.shields.io/badge/java-11%2B-blue.svg)

A layered **Selenium + Cucumber BDD** automation framework designed for **scalability, reusability, and CI/CD integration**.  
It separates concerns into **Core**, **Plugin**, and **Test** layers, while persisting execution results into **MongoDB**.  

---

## 📦 Project Structure
automation-framework/

├─ core-layer/ # Framework engine (drivers, hooks, MongoDB, logging, reporting)

├─ plugin-layer/ # Application logic (Page Objects, reusable Step Definitions)

└─ test-layer/ # Feature files, Runners, Reports (with submodules for different suites)

   ├─ Flipkart/

   ├─ Amazon/

   └─ etc
---



---

## 🧩 Layer Responsibilities

### **1. Core Layer**
- Manages WebDriver lifecycle (`DriverFactory`)
- Global **Hooks** (`@Before`, `@After`) for setup & teardown
- Logging with **Log4j2**
- **Extent Reports** manager
- **MongoDB singleton (`MongoDBManager`)** → saves scenario results:
  ```json
  {
    "scenarioName": "Login with valid credentials",
    "status": "PASSED",
    "startTime": "2025-08-22 10:15:30.123",
    "endTime":   "2025-08-22 10:15:33.456",
    "durationMillis": 3333
  }

## **2. Plugin Layer**

- Contains Page Objects extending BasePage

- Reflections API auto-registers all Page classes in PageManager → no hardcoding

- Common reusable Step Definitions (src/main/java)

- Packaged as a JAR and consumed by test-layer


## **3. Test Layer**

Contains submodules (e.g., Flipkart, Amazon)

Each submodule has:

features/ → Gherkin feature files

runner/ → Cucumber runners

resources/ → application.properties, reportportal.properties

Depends on plugin-layer (→ automatically pulls core-layer too)


✅ Prerequisites

JDK: 17+

Maven: 3.8+

MongoDB: Local or remote instance

Browser: Chrome (drivers auto-managed by WebDriverManager)


---

## 🏗️ Quick Start

# Core layer
git clone 
(https://github.com/AutomationVaibhav/CoreLayer.git)

cd core-layer

mvn clean install

# Plugin layer
git clone
(https://github.com/AutomationVaibhav/PluginLayer.git)

cd plugin-layer
  
mvn clean install

# Test layer
git clone 
(https://github.com/AutomationVaibhav/TestLayer.git)

cd test-layer

mvn clean install



---

## ✨ Run Test

cd test-layer/Flipkart

mvn clean test -Dcucumber.tags="@Login"


## 📝 Reporting

- **Cucumber JSON & HTML** reports are generated on every run.

---

---

## ✨ Features of the Framework

1. **Layered Architecture**
   - `core-layer` → Manages WebDriver, Hooks, Logging, Reporting, DB
   - `plugin-layer` → Page Objects & Step Definitions
   - `test-layer` → Business features & runners
   - Clean separation of concerns → scalable and reusable.

2. **Dynamic Page Management (Reflections API)**
   - Auto-discovers all Page classes at runtime.
   - No hardcoding of Page objects → easy maintainability.

3. **Centralized Driver Factory**
   - Single point to initialize browsers (Chrome, Firefox, etc.).
   - Uses **WebDriverManager** → no need to download drivers manually.

4. **Reusable Hooks**
   - Handles setup/teardown of browser.
   - Takes **screenshots on failures**.
   - Pushes results to **Extent Reports** and **MongoDB**.

5. **MongoDB Integration**
   - Saves **scenario name, status, start time, end time, execution duration**.
   - Implemented with a **Singleton MongoDB client** → ensures efficient connection handling.

6. **Rich Reporting**
   - **Extent Reports** → HTML execution reports with screenshots.
   - **Cucumber JSON/HTML reports** → for integrations with Jenkins / ReportPortal.

7. **Logging with Log4j2**
   - Configurable log levels (INFO, DEBUG, ERROR).
   - Centralized logging across all layers.


8. **Scalability with Submodules**
   - Each application/test suite (e.g., Flipkart, Amazon) is a separate **submodule**.
   - Easy to add/remove new test suites without affecting the core.



---



