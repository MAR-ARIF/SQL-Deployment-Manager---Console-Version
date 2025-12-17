# SQL Deployment Manager (Console Version)

## 📌 Project Overview

The **SQL Deployment Manager** is a **console-based Java application** designed to simulate real-world database schema deployment workflows. It helps developers and DBAs safely analyze, compare, deploy, and roll back SQL schema changes before applying them to production databases.

This project focuses on **Object-Oriented Programming (OOP)** concepts. A full-stack version may be developed in the future.

---

## 🎯 Key Features

* **Schema Comparison** (Predefined & Custom)
* **SQL Script Analysis** (Syntax & risk detection)
* **AI-style Analysis Modules**

    * Performance Analysis
    * Security Analysis
    * Style / Best Practices Analysis
* **Rollback Script Generator**
* **Deployment Workflow Simulation**
* **Approval System (Developer → DBA)**
* **File-based Deployment History & Logging**

---

## 🧱 OOP Concepts Used

* **Encapsulation** – User, FileHandler
* **Inheritance** – CustomSchemaDiff extends SchemaDiff
* **Abstraction** – AiAnalysis interface
* **Polymorphism** – PerformanceAnalysis, SecurityAnalysis, StyleAnalysis, RollbackScript
* **Separation of Concerns** – Menu-based architecture

---

## 👥 User Roles

### 👨‍💻 Developer

* View schema comparisons
* Analyze SQL scripts
* Run AI analysis
* Request deployments
* View deployment history

### 🧑‍💼 DBA

* View pending approval requests
* Approve or decline deployments
* View deployment history

---

## 📂 Project Structure

```
src/
 ├── Main.java
 ├── User.java
 ├── FileHandler.java
 ├── SchemaDiff.java
 ├── CustomSchemaDiff.java
 ├── SqlScriptAnalysis.java
 ├── PerformanceAnalysis.java
 ├── SecurityAnalysis.java
 ├── StyleAnalysis.java
 ├── RollbackScript.java
 ├── DeploymentWorkflow.java
 ├── AIAnalysisMenu.java
 ├── SchemaComparisonMenu.java
```

---

## 📝 Deployment Log Example

```
Development | Custom Script | ⏳AWAITING APPROVAL | 14/12/2025--14:22:10 | BY Arif | DEV
```

After DBA approval:

```
Development | Custom Script | ✅APPROVED BY DBA JHON | 14/12/2025--14:22:10 | BY Arif | DEV
```

---

## 🚀 How to Run

1. Open the project in **IntelliJ IDEA**
2. Run `Main.java`
3. Use the console menus to navigate features

---

## 👤 Author

**Arif**

---

## 🔮 Future Improvements

* Password-based authentication
* Real database connection (JDBC)
* Web / Full-stack version
* Role-based access control

---

✅ **This project demonstrates safe SQL deployment practices, OOP principles, and real-world DevOps concepts in a simplified academic environment.**


