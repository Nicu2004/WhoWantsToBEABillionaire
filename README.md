# 💰 Who Wants to Be a Billionaire?

A desktop quiz game inspired by *Who Wants to Be a Millionaire*, built with **Java 21**, **JavaFX 21**, and **PostgreSQL**. Players answer progressively harder multiple-choice questions to climb the prize ladder, with scores saved to a database.

---

## 📋 Table of Contents

- [About the Project](#about-the-project)
- [Tech Stack](#tech-stack)
- [Prerequisites](#prerequisites)
- [Setup & Installation](#setup--installation)
- [Running the App](#running-the-app)
- [How to Play](#how-to-play)
- [Adding Questions](#adding-questions)
- [Project Structure](#project-structure)
- [Contributing](#contributing)

---

## About the Project

This is a personal Java desktop application that replicates the classic TV quiz show format. Features include:

- 25 multiple-choice questions with 4 answer options each (A, B, C, D)
- **Two modes:** online (PostgreSQL database) and offline (`questions.txt` file)
- Score tracking saved persistently via PostgreSQL in online mode
- JavaFX-based graphical interface with CSS styling
- Docker Compose setup to spin up the PostgreSQL database with a single command

---

## Tech Stack

| Technology | Version | Purpose |
|---|---|---|
| Java | 21 | Core language |
| JavaFX | 21.0.6 | GUI framework |
| PostgreSQL | 14 | Score persistence |
| Maven | (via wrapper) | Build tool |
| Docker Compose | - | Database setup |

---

## Prerequisites

- **Java 21+** — [Download here](https://adoptium.net/)
- **Maven** — included via `mvnw` wrapper, no installation needed
- **Docker Desktop** *(online mode only)* — [Download here](https://www.docker.com/products/docker-desktop/)

---

## Setup & Installation

### 1. Clone the repository

```bash
git clone https://github.com/Nicu2004/WhoWantsToBEABillionaire.git
cd WhoWantsToBEABillionaire
```

### 2. Choose your mode

The app supports two modes:

| Mode | Questions source | Scores saved to |
|---|---|---|
| 🌐 **Online** | PostgreSQL database | PostgreSQL database |
| 📴 **Offline** | `questions.txt` file | `scores.txt` file |

#### Online mode — start the PostgreSQL database

Make sure Docker Desktop is running, then:

```bash
docker-compose up -d
```

This starts a PostgreSQL container with the following defaults:

| Setting | Value |
|---|---|
| Host | `localhost` |
| Port | `5432` |
| Database | `test` |
| Username | `postgres` |
| Password | `admin` |

#### Offline mode

No database setup needed. Questions are read directly from `questions.txt` in the project root. Scores are saved locally to `scores.txt`.

### 3. Install dependencies

```bash
./mvnw install        # Linux / macOS
mvnw.cmd install      # Windows
```

---

## Running the App

```bash
./mvnw exec:java      # Linux / macOS
mvnw.cmd exec:java    # Windows
```

Or run the main class directly from your IDE:

```
org.example.wowantstobeamillionare.game.starter.ApplicationStart
```

> **Note:** For online mode, make sure the Docker database container is running before launching the app. For offline mode, just ensure `questions.txt` is present in the project root.

---

## How to Play

1. Launch the application — the JavaFX window will open
2. Answer each multiple-choice question by clicking one of the four options
3. Correct answers advance you to the next question and increase your score
4. A wrong answer ends the game and your score is saved
5. Try to answer all 25 questions to reach the top prize!

**Example questions from the game:**
- *What is the capital of France?* → **(Paris)**
- *Who developed the theory of relativity?* → **(Albert Einstein)**
- *In computing, what does "HTTP" stand for?* → **(HyperText Transfer Protocol)**

---

## Adding Questions

### Offline mode

In offline mode, questions are read from `questions.txt` in the project root. The format for each entry is:

```
Question text here?
(Option A) (Option B) (Option C) (Option D)
<index of correct answer, 0-based>
```

**Example:**
```
What is 2 + 3?
(6) (4) (5) (3)
2
```

Here, index `2` means the third option — `(5)` — is correct. Leave a blank line between each question block.

### Online mode

In online mode, questions are fetched directly from the PostgreSQL database. To add or edit questions, insert them into the relevant table in your database instead of editing `questions.txt`.

---

## Project Structure

```
WhoWantsToBEABillionaire/
├── src/main/                   # Java source code (JavaFX controllers, game logic, DB)
├── .idea/                      # IntelliJ IDEA project files
├── .mvn/wrapper/               # Maven wrapper files
├── docker-compose.yml          # PostgreSQL container config
├── pom.xml                     # Maven dependencies & build config
├── questions.txt               # Quiz questions — used in offline mode
├── scores.txt                  # Local score log — used in offline mode
├── mvnw                        # Maven wrapper (Linux/macOS)
├── mvnw.cmd                    # Maven wrapper (Windows)
└── README.md
```

---

## Contributing

Suggestions and improvements are welcome!

1. Fork the repository
2. Create a new branch: `git checkout -b feature/your-feature`
3. Commit your changes: `git commit -m "Add your feature"`
4. Push to the branch: `git push origin feature/your-feature`
5. Open a Pull Request

---

> Built with ☕ Java and a love for quiz shows
