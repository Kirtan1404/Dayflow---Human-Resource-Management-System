
## Introduction

Dayflow HRMS provides a secure and efficient way to manage HR operations. It includes **role-based access control**, allowing employees and admins to access the system according to their privileges. Admins can manage employee profiles, approve leave requests, track attendance, and control payroll, while employees can view their profiles, attendance, leave status, and salary details.

---

## Features

**Authentication & Authorization**

* User registration with email verification
* Role-based login (Employee / Admin / HR)
* Secure password rules

**Dashboard**

* Employee dashboard: profile, attendance, leave requests
* Admin/HR dashboard: employee management, leave approvals, attendance overview

**Employee Profile Management**

* View and edit profile (limited fields for employees)
* Admins can edit all details

**Attendance Management**

* Daily and weekly attendance tracking
* Check-in/check-out option
* Attendance status: Present, Absent, Half-day, Leave

**Leave & Time-Off Management**

* Apply for leave with types (Paid, Sick, Unpaid)
* Admin approval/rejection workflow with comments
* Immediate record updates

**Payroll Management**

* Employees can view payroll (read-only)
* Admins can update payroll and salary structure

---

## Technologies

**Frontend**

* React.js + TypeScript
* Axios (for API calls)
* Material-UI / Tailwind CSS (optional for styling)
* React Router

**Backend**

* Spring Boot
* Spring Security (Authentication & Authorization)
* Spring Data JPA
* REST APIs

**Database**

* MySQL


---

## System Architecture

```
[Frontend - React/TS] <---> [Backend - Spring Boot REST API] <---> [MySQL Database]
```

* **Frontend**: Sends HTTP requests to backend API
* **Backend**: Handles business logic, authentication, and database operations
* **Database**: Stores employee, attendance, leave, and payroll data

---

## Setup & Installation

### Backend (Spring Boot)


. Update `application.properties` with your MySQL credentials:

   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/employee_managment
   spring.datasource.username=root
   spring.datasource.password=yourpassword
   spring.jpa.hibernate.ddl-auto=update
   ```

   ```
. Backend will run at `http://localhost:9999

### Frontend (React + TypeScript)

1. Navigate to frontend folder:

   ```bash
   cd ../frontend
   ```
2. Install dependencies:

   ```bash
   npm install
   ```
3. Start development server:

   ```bash
   npm run dev
   ```
4. Frontend will run at `http://localhost:8081`

### Database (MySQL)

1. Install MySQL and create a database:

   ```sql
   CREATE DATABASE Employee_managment;
   ```
2. Make sure credentials match `application.properties` in backend

---

## Usage

* Register as **Employee** or **Admin/HR**
* Login to view respective dashboards
* Employees: Apply for leave, check attendance, view salary
* Admins/HR: Manage employees, approve leave, view attendance and payroll

---
