# HabitForge - A Full-Stack Productivity Dashboard ✨

HabitForge is a web-based productivity application designed to help you build better habits, manage your tasks, and stay focused. This project combines a robust Java backend powered by the Spark Framework with a dynamic and responsive frontend built with vanilla HTML, CSS, and JavaScript.



---
## ## Features

* ✅ **Habit Tracking:** Create, track, and delete daily habits. Your progress is measured with a streak counter to keep you motivated.
* 📝 **To-Do List:** A simple and effective list to manage your daily tasks. Add new items, mark them as complete, and clear your list.
* 🍅 **Pomodoro Timer:** A built-in timer to help you focus using the Pomodoro Technique. Cycle through work and break sessions to maximize productivity.
* ⏰ **Custom Reminders:** Set browser-based notifications for important events or tasks so you never miss a deadline.

---
## ## Tech Stack

* **Backend:**
    * **Java 17**
    * **Spark Framework** (for creating a lightweight REST API)
    * **Maven** (for dependency management and building the project)
    * **Gson** (for handling JSON data)
* **Frontend:**
    * HTML5
    * CSS3
    * Vanilla JavaScript (using Fetch API for backend communication)

---
## ## Getting Started

Follow these instructions to get a copy of the project up and running on your local machine.

### ### Prerequisites

Make sure you have the following software installed:
* **Git:** [Download Git](https://git-scm.com/downloads)
* **Java Development Kit (JDK):** Version 17 or higher.
* **Apache Maven:** [Maven Installation Guide](https://maven.apache.org/install.html)

### ### Installation & Setup

1.  **Clone the repository:**
    Replace `YourUsername` with your actual GitHub username.
    ```bash
    git clone [https://github.com/YourUsername/productivity-app.git](https://github.com/YourUsername/productivity-app.git)
    ```

2.  **Navigate to the project directory:**
    ```bash
    cd productivity-app
    ```

3.  **Run the Backend Server:**
    Execute the following Maven command in the root directory. This will compile the code and start the Java server.
    ```bash
    mvn compile exec:java "-Dexec.mainClass=com.example.habit_tracker.App"
    ```
    The server will be running on `http://localhost:4567`. Leave this terminal window open.

4.  **Launch the Frontend:**
    * Navigate to the `public` folder inside the project.
    * Open the **`index.html`** file in your favorite web browser.

---
## ## API Endpoints

The backend exposes the following RESTful API endpoints:

| Method | Endpoint                          | Description                               |
| :----- | :-------------------------------- | :---------------------------------------- |
| `GET`  | `/api/habits`                     | Get all habits.                           |
| `POST` | `/api/habits`                     | Create a new habit.                       |
| `DELETE`| `/api/habits/:name`               | Delete a habit by its name.               |
| `POST` | `/api/habits/:name/complete`      | Mark a habit as complete for the day.     |
| `GET`  | `/api/todos`                      | Get all to-do items.                      |
| `POST` | `/api/todos`                      | Create a new to-do item.                  |
| `DELETE`| `/api/todos/:id`                  | Delete a to-do item by its ID.            |
| `PUT`  | `/api/todos/:id/toggle`           | Toggle the completion status of a to-do.  |

---
## ## License

This project is licensed under the MIT License.
