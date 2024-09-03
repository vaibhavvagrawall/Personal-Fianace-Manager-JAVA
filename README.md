# **Personal Finance Management System**

The **Personal Finance Management System** is a simple, console-based Java application designed to help users manage their personal finances. The application allows users to register, log in, add income and expense transactions, and generate a financial report that summarizes their transactions and calculates their current balance. The data is securely stored in serialized files, making it easy to save and load user information and financial records.

## **Project Structure**

- **`User.java`:** Handles user information, including username, password, and authentication logic.
- **`Transaction.java`:** Represents a financial transaction with attributes like amount, category, date, and type (income/expense).
- **`FinanceManager.java`:** Manages a list of transactions, calculates the balance, and generates financial reports.
- **`FileManager.java`:** Provides functionality to save and load user and transaction data from files using serialization.
- **`Main.java`:** The entry point of the application, handling user interaction and integrating all components.
