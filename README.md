# 🔐 JavaShield Security Toolkit

JavaShield is a Java-based console application that simulates core cybersecurity practices such as password strength evaluation, suspicious URL detection, and brute-force attack monitoring using log analysis.

---

## 🚀 Features

### 🔑 Password Strength Checker
- Evaluates passwords based on:
  - Length
  - Uppercase & lowercase letters
  - Numbers
  - Special characters  
- Provides actionable suggestions for improvement

### 🌐 Suspicious URL Detector
- Detects risky URLs using heuristic rules:
  - HTTP vs HTTPS
  - Presence of symbols like '@' or '-'
  - Phishing keywords (login, verify, free)
  - Unusual URL length  
- Classifies links as **Safe**, **Suspicious**, or **High Risk**

### 📊 Access Log Monitor
- Reads log files and tracks failed login attempts  
- Uses **HashMap** to count user-based failures  
- Detects potential **brute-force attacks**  
- Flags suspicious activity when thresholds are exceeded  

---

## 🛠️ Tech Stack

- **Language:** Java  
- **Core Concepts:**
  - Object-Oriented Programming (OOP)  
  - File Handling (BufferedReader, FileReader)  
  - Regular Expressions (Regex)  
  - Collections Framework (HashMap)  
  - Exception Handling  

---

## ▶️ How to Run

```bash
javac --release 8 JavaShield.java
java JavaShield
