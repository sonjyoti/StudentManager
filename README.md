# Student Manager

A simple Android application built with **Kotlin** and **Jetpack Compose** to manage student records. This project is being developed as a hands-on learning project for Android development.

## Features

- Display a list of students.
- View student details, including name, age, course, and ID.
- Add new student records through a form.
- Validate user input before adding students.
- Automatically update the student list and student count using Compose state.
- Display validation messages for missing or invalid input.

## Tech Stack

- **Language:** Kotlin
- **UI Framework:** Jetpack Compose
- **UI Components:** Material 3
- **IDE:** Android Studio
- **Build System:** Gradle

## Screenshots

Screenshots will be added as the application UI develops.

## Getting Started

### Prerequisites

- Android Studio
- Android SDK
- An Android emulator or a physical Android device
- Git

### Installation

1. Clone the repository:

   ```bash
   git clone https://github.com/sonjyoti/StudentManager.git
   ```

2. Open Android Studio.

3. Select **Open** and choose the cloned `StudentManager` directory.

4. Wait for Gradle sync to complete.

5. Start an Android emulator or connect a physical Android device with USB debugging enabled.

6. Click **Run** in Android Studio to build and launch the application.

## Current Limitations

- Student records are stored in memory.
- Added students are lost when the application process is restarted.
- The application currently uses sample student records.
- A backend API and database have not yet been integrated.

## Planned Features

- Delete student records.
- Edit existing student details.
- Improve the user interface and input validation.
- Persist student records locally using Room Database.
- Search and filter students.
- Add automated tests.

## Contributing

This is a personal learning project. Suggestions and improvements are welcome.