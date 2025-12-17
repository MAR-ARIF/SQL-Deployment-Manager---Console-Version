# SQL Deployment Manager (Console Version)

SID - 2427580
Team name- Team Quint

---

## 📌 Project Overview

The **SQL Deployment Manager** is a **console-based Java application** designed to simulate real-world database schema deployment workflows.
It helps **developers and DBAs** safely analyze, compare, deploy, and roll back SQL schema changes before applying them to production environments.

The user can see predefined schema comparison and script OR he can provide custom schema and script in this program

This project focuses on **Object-Oriented Programming (OOP)** concepts.

---

## 🎯 Key Features

* **Schema Comparison**

    * Compare predefined and custom target schemas
    * Detect added, dropped, and modified columns

* **SQL Script Analysis**

    * Performance analysis
    * Security risk detection
    * SQL style and best-practice validation

* **Rollback Script Generator**

    * Automatically generates rollback SQL for safety

* **Deployment Workflow Simulation**

    * Developer submits deployment request
    * DBA approves or declines deployment
    * Deployment history stored in a log file

* **Role-Based Access**

    * Developer and DBA have different permissions

---

## 👥 User Roles

### 👨‍💻 Developer

* View schema comparisons
* Analyze SQL scripts
* Generate rollback scripts
* Submit deployment requests
* View deployment history

### 🛡 Database Administrator (DBA)

* View pending deployment requests
* Approve or decline deployments
* View deployment history

---
#   Project Structure

Main.java – Application entry point

SchemaDiff.java – Predefined schema comparison

CustomSchemaDiff.java – Custom schema parsing and comparison

AiAnalysis.java – Analysis interface

PerformanceAnalysis.java – Performance checks

SecurityAnalysis.java – Security checks

StyleAnalysis.java – Style and best practice checks

RollbackScript.java – Rollback script generator

DeploymentWorkflow.java – Deployment request handling

FileHandler.java – File operations (logs & scripts)

User.java – User data and input handling
## 🧱 Main (Current) Schema


CREATE TABLE users (
    id INT PRIMARY KEY,
    username VARCHAR(50),
    email VARCHAR(100),
    created_at DATETIME,
    is_active BOOLEAN
);

---

## 🧪 Sample Target Schemas (For Testing)

### 🟢 Sample Target Schema 1 (Added Column)


CREATE TABLE users (
    id INT PRIMARY KEY,
    username VARCHAR(50),
    email VARCHAR(100),
    created_at DATETIME,
    is_active BOOLEAN,
    phone VARCHAR(20) -- new column added
);


### 🟡 Sample Target Schema 2 (Modified Column)


CREATE TABLE users (
    id INT PRIMARY KEY,
    username VARCHAR(50),
    email VARCHAR(150), -- modified from VARCHAR(100)
    created_at DATETIME,
    is_active BOOLEAN
);


### 🔴 Sample Target Schema 3 (Dropped + Added Column)


CREATE TABLE users (
    id INT PRIMARY KEY,
    username VARCHAR(50),
    email VARCHAR(100),
    last_login DATETIME, -- added
    is_active BOOLEAN
    -- created_at column removed
);


---

## 🧾 Sample SQL Scripts

### SQL Script 1


ALTER TABLE users
ADD COLUMN phone VARCHAR(20),
MODIFY COLUMN email VARCHAR(150);
```

### SQL Script 2


ALTER TABLE users
DROP COLUMN created_at,
ADD COLUMN last_login DATETIME;
```

### SQL Script 3


ALTER TABLE users
ADD COLUMN password VARCHAR(255) NOT NULL,
MODIFY COLUMN id BIGINT;


---

## ♻ Sample Rollback Script Output


ALTER TABLE users
DROP COLUMN phone,
MODIFY COLUMN email VARCHAR(100);


---

## 🗃 Deployment Log Example

```
Development | Predefined Script | ⏳AWAITING APPROVAL | 21/05/2025--14:30:10 | BY Arif | DEV
Staging | Custom Script | ✅APPROVED BY DBA JHON | 21/05/2025--15:10:45 | BY Arif | DEV
```

---

## 🧠 Object-Oriented Programming (OOP) Concepts Used

* **Encapsulation** – Private variables with getters and controlled access
* **Inheritance** – `CustomSchemaDiff` extends `SchemaDiff`
* **Abstraction** – `AiAnalysis` interface
* **Polymorphism** – Multiple analysis classes implementing `AiAnalysis`
* **Separation of Concerns** – Each class handles a specific responsibility

---

## ▶ How to Run the Application

1. Open the project in **IntelliJ IDEA** or any Java IDE
2. Run `Main.java`
3. Follow the console menu options
4. The user can see predefined schema comparison and script OR he can provide custom schema and script in this program.
4. Copy-paste sample schemas or SQL scripts when asked for custom schema or script or You can use your own custom script but make sure the syntax is same.

---
###Explanation of Changes from Component 010/1 Design

During the implementation of this assignment, I made a small number of changes to the original class names and method structures that were proposed in the design document for component 010/1. These changes were necessary to ensure that the final Java application followed best coding practices, improved clarity, and worked effectively in a console-based environment.
The reasons for these deviations are listed below:

1. 	Improved Naming Consistency:
Some class and method names from the original diagrams were adjusted to follow standard Java naming conventions. This includes using CamelCase for class names and lowerCamelCase for method names. These changes improve readability and align the project with common OOP standards.

2. 	Simplification for Console-Based Implementation:
The original design included elements that were more suitable for a GUI or a more complex system. Since this assignment requires a console-only application, certain names and functions were simplified to better match the required interaction style.

3. 	Avoiding Redundancy and Improving Structure:
A few methods and attributes were renamed or reorganised to avoid duplication and to make the code more modular. These adjustments help maintain clean structure and make the program easier to test and understand.

4. 	Practical Adjustments During Development:
While implementing the system, some class responsibilities became clearer. Minor changes were made to ensure each class had a single, well-defined purpose. These changes improve overall cohesion and maintainability

## ✅ Conclusion

The **SQL Deployment Manager** demonstrates a structured approach to managing database schema changes using Java. It applies OOP principles, file handling, role-based workflows, and SQL analysis, closely reflecting real-world database deployment practices.


