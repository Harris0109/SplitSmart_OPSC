SplitSmart - Group Expense Management App

🎯 Project Overview

SplitSmart is a robust, collaborative expense tracking application built for Android. It allows groups of friends, family, or colleagues to easily record shared expenses, manage group balances, and settle debts using an optimized payment strategy (minimum number of transactions).

Developed as the final project for OPSC6312 - Open Source Coding (Intermediate), this application demonstrates proficiency in modern Android development (Kotlin), persistent data storage (Firebase), automated testing, and CI/CD practices.

Key Features

Optimized Debt Settlement: Uses the DebtOptimizer class to calculate the minimum number of transactions required to clear all group balances.

Multilingual Support: Supports English, Afrikaans, and isiXhosa via the Settings Fragment and externalized string resources.

Offline Synchronization: Leverages Firebase Firestore's persistence layer to allow users to record expenses without an active internet connection.

Real-Time Data: All group data is synchronized instantly across all members using Firebase Firestore.

Modular Architecture: Implements a clear 3-tier structure (Presentation, Business Logic, Data) for maintainability and scalability.

🛠️ Technology Stack

Component

Technology

Role

Platform

Android Studio

Integrated Development Environment

Language

Kotlin

Primary development language

Database

Firebase Firestore

Real-time, NoSQL cloud database for data persistence

CI/CD

GitHub Actions

Automated build and testing workflow (ci.yml)

Architecture

Fragments / Repository Pattern

UI management and data abstraction

🚀 Getting Started

To run the SplitSmart application locally in Android Studio, follow these steps:

Prerequisites

Android Studio installed (latest stable version recommended).

Java Development Kit (JDK) installed.

Setup Instructions

Clone the Repository:

git clone [Your Actual GitHub Repository URL Here]
cd splitsmart_poe


Configure Firebase:

Create a new project in the Firebase Console.

Enable Cloud Firestore.

Register an Android app within your Firebase project.

Download the google-services.json file and place it in the app/ directory of the cloned project. (Note: This step is typically done by the local developer and not committed to the public repository).

Build and Run:

Open the project in Android Studio.

Wait for Gradle to sync dependencies.

Select an emulator or physical device and click the Run button (▶).

✅ Project Quality & Compliance

This project adheres to professional open-source standards and meets the academic requirements for the OPSC6312 POE.

Automated Testing

Type: Unit Tests for critical business logic.

Focus: The primary unit testing focus is on the DebtOptimizer class to ensure the minimum-transaction settlement algorithm is mathematically correct under various debt scenarios.

Location: Tests are located in the standard src/test directory.

Continuous Integration (CI/CD)

A CI/CD pipeline is configured using GitHub Actions to enforce code quality and stability.

Workflow File: .github/workflows/ci.yml

Trigger: Automatically runs on every push to the main branch and on pull requests.

Action: Executes the build process (./gradlew assembleDebug) and runs all available unit tests.

Version Control

The entire project history is managed using Git and hosted on GitHub.

The commit history follows a clear convention (e.g., feat:, fix:, refactor:) to document every stage of development.

👥 Group Members
