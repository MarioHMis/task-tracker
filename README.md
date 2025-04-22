# TaskTrackCLI

TaskTrackCLI is a lightweight command-line application built with Java as part of my personal portfolio. It allows users to manage their tasks by adding, listing, updating, filtering, and deleting them through a clean and intuitive text-based interface. All tasks are stored persistently using JSON, with support for status tracking and timestamps.

## Features

- 📋 Task creation with automatic timestamps
- ✅ Status management: TODO, IN_PROGRESS, DONE
- 🔢 Task selection by number (no need to enter long IDs)
- 🧹 Delete tasks easily
- 🔎 Filter tasks by status
- 💾 Persistent storage using JSON with automatic backup
- 🧪 Includes basic unit tests with JUnit 5
- 🚀 Packaged as an executable JAR

## Requirements

- Java 17 or higher
- Maven 3.8 or higher

## Installation and Usage

1. Clone the repository:

   ```bash
   git clone https://github.com/MarioHMis/TaskTrackCLI.git
   cd TaskTrackCLI
   ```

2. Build the project with Maven:

   ```bash
   mvn clean package
   ```

3. Run the application:

   ```bash
   java -jar target/tasktrackcli-1.0-SNAPSHOT-jar-with-dependencies.jar
   ```

All task data will be saved in a `tasks.json` file in the root of the project directory.

## Screenshots

### 🧭 Main Menu

![Main Menu] ![menu](https://github.com/user-attachments/assets/91c34451-0463-4557-82b1-cb2b465a94c6)


### 🧹 Task Management Flow

![Task Flow] ![task-flow](https://github.com/user-attachments/assets/6ac5f1be-3fcf-480b-a02e-8280ccbc5ac5)


## Technologies Used

- Java 17
- Maven
- Gson (for JSON handling)
- JUnit 5 (for unit testing)
- Git (version control)
- IntelliJ IDEA (development environment)
