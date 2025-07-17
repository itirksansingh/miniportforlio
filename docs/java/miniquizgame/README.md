# 🎮 Mini Quiz Game in Java

A simple console-based **Quiz Game** built in Java using **loops**, **arrays**, and the **Scanner** class.  
This game asks multiple-choice questions, checks your answers, and updates your score in real-time!

---

## 🚀 Features

- 🧠 5 preset quiz questions with 4 options each  
- 🎯 Real-time score display after each answer  
- 📝 Final score display at the end  
- 👶 Beginner-friendly code structure using basic Java concepts  

---

## 🛠️ Technologies & Concepts Used

| Concept / Method            | Purpose                                                                 |
|-----------------------------|-------------------------------------------------------------------------|
| `Scanner`                   | Read input from the user                                                |
| `for` loop                  | Iterate through the questions                                           |
| `String[]` array            | Store quiz questions                                                    |
| `String[][]` 2D array       | Store multiple options per question                                     |
| `char[]` array              | Store correct answers (A/B/C/D)                                         |
| `charAt(0)` method          | Get the first character from user input                                |
| `toUpperCase()` method      | Standardize user input for comparison                                   |
| `System.out.println()`      | Display output on console                                               |
| `if-else` statement         | Compare user input with correct answer and update score                 |

---

## 📄 Code Structure Overview

### ✅ `main` Method

```java
public static void main(String[] args)
```
- Entry point of the program

### 📦 Arrays Used

```java
String[] questions;
String[][] options;
char[] answers;
```

- `questions[]`: Stores all quiz questions  
- `options[][]`: Stores 4 options per question  
- `answers[]`: Stores correct answers in `char` format  

### 🔁 Loop to Display Questions

```java
for (int i = 0; i < questions.length; i++)
```
- Loops through each question, displays it along with options, and checks the user's answer.

### 🎮 Taking User Input

```java
Scanner scanner = new Scanner(System.in);
char userAnswer = Character.toUpperCase(scanner.next().charAt(0));
```
- Gets the user's answer and converts it to uppercase to ensure case-insensitive checking.

### 🧠 Answer Checking

```java
if (userAnswer == answers[i]) {
    // Correct!
} else {
    // Incorrect
}
```

---

## 📷 Sample Output

```
Question 1: What is the output of 5 + 3?
A. 53
B. 8
C. Error
D. None
Your answer (A/B/C/D): B
✅ Correct!
Current Score: 1/1
...
🎉 Quiz Over! Your Final Score: 5/5
```

---

## 📦 How to Run the Program

1. Save the code in a file named `MiniQuizGame.java`
2. Open terminal/command prompt
3. Compile the Java program:
   ```
   javac MiniQuizGame.java
   ```
4. Run the compiled class:
   ```
   java MiniQuizGame
   ```

---

## 💡 Future Enhancements (Optional)

- Add timer per question
- Randomize question order using `Collections.shuffle()`
- Read questions from external `.txt` or `.csv` file
- Add difficulty levels
- Support multiplayer or scoring leaderboard

---

## 👨‍💻 Author

**Sanskriti Singh** (Replace with your own name if needed)

---

## 📁 License

This project is open for educational purposes. Feel free to fork and improve!
