# SQL Deployment Manager (Console Version)

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

```sql
CREATE TABLE users (
    id INT PRIMARY KEY,
    username VARCHAR(50),
    email VARCHAR(100),
    created_at DATETIME,
    is_active BOOLEAN
);
```

---

## 🧪 Sample Target Schemas (For Testing)

### 🟢 Sample Target Schema 1 (Added Column)

```sql
CREATE TABLE users (
    id INT PRIMARY KEY,
    username VARCHAR(50),
    email VARCHAR(100),
    created_at DATETIME,
    is_active BOOLEAN,
    phone VARCHAR(20) -- new column added
);
```

### 🟡 Sample Target Schema 2 (Modified Column)

```sql
CREATE TABLE users (
    id INT PRIMARY KEY,
    username VARCHAR(50),
    email VARCHAR(150), -- modified from VARCHAR(100)
    created_at DATETIME,
    is_active BOOLEAN
);
```

### 🔴 Sample Target Schema 3 (Dropped + Added Column)

```sql
CREATE TABLE users (
    id INT PRIMARY KEY,
    username VARCHAR(50),
    email VARCHAR(100),
    last_login DATETIME, -- added
    is_active BOOLEAN
    -- created_at column removed
);
```

---

## 🧾 Sample SQL Scripts

### SQL Script 1

```sql
ALTER TABLE users
ADD COLUMN phone VARCHAR(20),
MODIFY COLUMN email VARCHAR(150);
```

### SQL Script 2

```sql
ALTER TABLE users
DROP COLUMN created_at,
ADD COLUMN last_login DATETIME;
```

### SQL Script 3

```sql
ALTER TABLE users
ADD COLUMN password VARCHAR(255) NOT NULL,
MODIFY COLUMN id BIGINT;
```

---

## ♻ Sample Rollback Script Output

```sql
ALTER TABLE users
DROP COLUMN phone,
MODIFY COLUMN email VARCHAR(100);
```

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

## ✅ Conclusion

The **SQL Deployment Manager** demonstrates a structured approach to managing database schema changes using Java. It applies OOP principles, file handling, role-based workflows, and SQL analysis, closely reflecting real-world database deployment practices.


