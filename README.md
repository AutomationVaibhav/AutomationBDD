# 🚀 AutomationBDD Framework

![Build](https://img.shields.io/badge/build-passing-brightgreen)
![License](https://img.shields.io/badge/license-MIT-blue.svg)
![Java](https://img.shields.io/badge/java-11%2B-blue.svg)
![Jenkins](https://img.shields.io/badge/ci-jenkins-blue)

> **A Selenium + Cucumber BDD framework with distributed Jenkins support and rich reporting.**

---

## ✨ Features

- 🌱 Modular Page Object Model
- 🧪 BDD-style readable test scenarios
- ⚡ Distributed test execution (Jenkins Master/Agent)
- 📊 Beautiful Cucumber Reports in Jenkins
- 🧩 Easy to extend and customize

---


---

## 🚦 Tech Stack

| Tool        | Purpose                          |
|-------------|----------------------------------|
| Selenium    | Browser automation               |
| Cucumber    | BDD framework                    |
| JUnit       | Test runner                      |
| Jenkins     | CI/CD and distributed execution  |
| Maven       | Build automation                 |
| Java 11+    | Programming language             |

---

## 🏗️ Quick Start

1. **Clone the repo:**
    ```sh
    git clone https://github.com/yourusername/AutomationBDD.git
    cd AutomationBDD
    ```
2. **Install dependencies:**
    - Java 11+
    - Maven
    - Chrome/Firefox

3. **Run tests locally:**
    ```sh
    mvn clean test
    ```

4. **Review HTML & JSON reports:**
    - `target/Cucumber-reports.html`
    - `target/CucumberTestReport.json`

---

## ⚙️ Jenkins Integration

- **Distributed Execution:**
    - Jenkins master on host machine
    - Jenkins agent/VM for real browser UI testing

- **Setup:**
    1. Add your Jenkins agent node (remote VM) using JNLP or SSH.
    2. Restrict job to run on the agent (label-based).
    3. Configure build steps:
        - **SCM:** Pull from GitHub repo
        - **Build:** `mvn clean test`
    4. **Reporting:**
        - **Post-build action:** Add "Publish Cucumber test result report"
        - **JSON Reports Path:** `target`

- **After each build:**
    - View detailed Cucumber reports in the Jenkins UI.

---

## 📝 Reporting

- **Cucumber JSON & HTML** reports are generated on every run.
- **Jenkins Cucumber Reports plugin** parses the JSON and shows interactive, filterable results in the UI.

---

## 🧑‍💻 Example Test Runner Configuration

```java
@CucumberOptions(
    features = "src/test/resources/features",
    glue = {"stepdefinitions", "Hooks"},
    plugin = {
        "pretty",
        "html:target/CucumberTestReport.html",
        "json:target/CucumberTestReport.json"
    },
    monochrome = true
)

