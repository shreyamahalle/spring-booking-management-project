# spring-booking-management-project

spring-booking-management-project

## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Getting Started](#getting-started)
- [Installation](#installation)
- [Usage](#usage)
- [Contributing](#contributing)
- [Contact](#contact)

# **spring-booking-management-project**

Welcome to the **Spring Booking Management Project
**, a simple yet powerful Java application that simulates a booking management system. This project is built using the
Spring Framework (XML-based configuration only) and applies solid Object-Oriented Programming (OOP) principles.
---

## Introduction

## **🚀 Project Overview**

The Spring Booking Management System provides a modular approach to managing:

📋 Customers

🍽️ Restaurants

🚚 Delivery Agents

📦 Orders

It mimics real-world operations like customer registration, restaurant assignment, order placing, and delivery
tracking — all managed using a clean layered architecture (Controller → Service → Repository).


---

## Features

## **✨ Key Features**

- **📅 Customer and Restaurant Booking Management**: Effortlessly handle customer bookings and restaurant data.
- **🚚 Delivery Agent Simulation**: Assign delivery agents based on location and availability.
- **📦 Dynamic Order Handling**: Add, track, and manage orders through their entire lifecycle.
- **💻 Seamless Java Integration**: Clean integration of Java models for each entity (Customer, Delivery Agent, Order,
  Restaurant).
- **🔍 Easy-to-Follow Structure**: Well-documented, clean code for learning and demonstration.

---

# Technologies Used

## **🛠️ Technologies Used**

This project is built with the following technologies:

- **Java**: The core programming language used for developing the application.
- **JSP (JavaServer Pages)**: For creating dynamic web content.
- **Servlets**: For handling requests and responses in a web environment.
- **Spring Framework**: Used for creating a flexible and scalable application architecture, handling dependency
  injection, and simplifying configurations.
- **Maven**: A project management and build automation tool to handle dependencies, building, and packaging.
- **MySQL**: A reliable relational database system used to store customer, restaurant, delivery agent, and order data.
- **JDBC**: Database connectivity
- **HTML5/CSS3:** Frontend technologies.
- **Git**: For version control and collaboration.

---

## Getting Started

## **💡 Installation and Setup**

### **Prerequisites**

Before you begin, ensure you have the following:

- **Java Development Kit (JDK) 21**: Install the latest JDK for optimal performance.
- **Maven**: Make sure Maven is installed to handle the project build.
- **MySQL Database**: You'll need to have MySQL installed and set up to manage your data.
- **Apache Tomcat**: A servlet container for running your web application.
- **IDE**: Use IntelliJ IDEA, Eclipse, or any Java IDE you're comfortable with.
- **Git (optional)**: For version control and easier collaboration.

## **💡Steps to Get Started**

## 1. **Clone the Repository:**

   ```bash
   git clone git@github.com:shreyamahalle/spring-booking-management-project.git
```

## 2. **Navigate to the Project Directory:**

```bash
 cd maven-booking-management-project
```

## 3. **Open the Project in Your Preferred IDE:**

```bash

 Launch your IDE (IntelliJ, Eclipse, etc.), and open the cloned repository.
```

## 4. **Set up the MySQL Database:**

```bash
  CREATE DATABASE booking_management;
  USE booking_management;
```

## 5. **MySQL Database Setup**

- To set up the database and create the necessary tables, run the following SQL queries:
  ```bash
  -Insert sample data into the Customer table
   INSERT INTO Customer (name, username, mobileNo, city, area)
  
  -VALUES ('John Doe', 'johndoe123', '123-456-7890', 'New York', 'Manhattan');
  ```

## 6. **Configure Your MySQL Connection:**

- In your project configuration (application.properties), set the MySQL database connection details.

- Example application.properties:

- spring.datasource.url=jdbc:mysql://localhost:3306/booking_management
- spring.datasource.username=root
- spring.datasource.password=your_password
- spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

## 7. **Compile and Run the Application:**

- Locate Main.java in your IDE.

- Run the file to launch the application.

## 8. **Build the project:**

   ```bash
   mvn clean install
   ```

## Usage

- **User Registration:** Navigate to the registration page and create a new account.
- **User Login:** Log in with your credentials to access the application.
- **CRUD Operations:** Perform create, read, update, and delete operations on the data.
- **Session Management:** Manage user sessions effectively.

## Contact

For any questions or suggestions, feel free to open an issue or contact me directly:

- **GitHub:** [Shreya Mahalle](https://github.com/shreyamahalle)

---

*This README was generated by [Shreya Mahalle](https://github.com/shreyamahalle).*

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details.

## **Code Structure** ## 

## **Packages Structure**

- com.booking.using.dowhile: Contains the main application logic (BookingDemoDoWhile.java).
- com.booking.using.dowhile.model: Includes the model classes:
    - Customer.java
    - DeliveryAgent.java
    - Order.java
    - Restaurant.java
- package names: com.booking.Customer.model
- package names: com.booking.DeliveryAgent.model
- package names: com.booking.Order.model
- package names: com.booking.Restaurant.model
- package names: com.booking.BookingDemoDoWhile.service

## **Functionality**

1. **Customer**
2. **DeliveryAgent**
3. **Order**
4. **Restaurant**

## **🔧 Core Functionality**

1. Customer Management
   Allows you to create and manage customer information such as name, username, contact details, and address.

2. Delivery Agent Management
   Adds delivery agents, assigns them to orders, and tracks their delivery status.

3. Order Management
   Manages the creation, display, and tracking of customer orders from placement to delivery.

4. Restaurant Management
   Registers new restaurants, displays restaurant details, and associates them with orders.

## **📝 Database Schema (POJO Classes)**

| **Entity**        | **Attributes**                              |
|-------------------|---------------------------------------------|
| **Customer**      | user id, name, username,monbileNo,city,area |
| **DeliveryAgent** | id, name, city, mobileNo                    |
| **Order**         | id, name, city, mobileNo                    |
| **Restaurant**    | registerNo, name, City, Area                |

---

# Class Diagram

```mermaid
---
title: booking management project
---
classDiagram

 note " food order management "

 class Customer
 Customer : +int ID
 Customer : +String name
 Customer : +String city
 Customer : +int age
 Customer : +int contactNo

class Customer{
 +createCustomer()
 +displayCustomer()
}

class DeliveryAgent
DeliveryAgent : +int id
DeliveryAgent : +String name
DeliveryAgent : +String city
DeliveryAgent : +mobileNo

class DeliveryAgent{
+createDeliveryAgent()
+displayDeliveryAgent()
}

  Customer --|> Order  : Inheritance
class Order{
 +createOrder()
 +displayOrder()
}
 Order : +int Id
 Order : +String name
 Order : +String lastName
 Order : +int age
 Order : +int contactNo
 Order : +String address

class Order{
 +createOrder()
 +displayOrder()
}

Restaurant: +int registerNo
Restaurant: +String Name
Restaurant: +String city
Restaurant: +String Area
Restaurant: +int contactNo

class Restaurant{
 +createRestaurant()
 +displayRestaurant()
}

Customer <-- Order : Association
DeliveryAgent  <-- Order  : Assocciation


Restaurant --> Customer : Association
Restaurant --> Order : Association
Restaurant --> DeliveryAgent : Association

Customer --> DeliveryAgent : Association




```

---

## 👨‍💻 Author

Shreya Mahalle  
[GitHub](https://github.com/shreyamahalle) | [LinkedIn](https://linkedin.com/in/shreyamahalle)

---

