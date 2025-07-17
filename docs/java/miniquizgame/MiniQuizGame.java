import java.util.Scanner;

public class MiniQuizGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Questions
        String[] questions = {
            "What is the output of 5 + 3?",
            "Which loop is used when the number of iterations is known?",
            "Which data structure stores multiple values of the same type?",
            "What does Scanner do in Java?",
            "Which keyword is used to exit a loop?"
        };

        // Options
        String[][] options = {
            {"A. 53", "B. 8", "C. Error", "D. None"},
            {"A. while", "B. do-while", "C. for", "D. if"},
            {"A. Scanner", "B. int", "C. Array", "D. String"},
            {"A. Outputs data", "B. Takes input", "C. Loops the program", "D. None"},
            {"A. return", "B. exit", "C. break", "D. continue"}
        };

        // Correct answers (use B, C, etc.)
        char[] answers = {'B', 'C', 'C', 'B', 'C'};

        int score = 0;

        // Loop through each question
        for (int i = 0; i < questions.length; i++) {
            System.out.println("\nQuestion " + (i + 1) + ": " + questions[i]);
            for (String option : options[i]) {
                System.out.println(option);
            }

            System.out.print("Your answer (A/B/C/D): ");
            char userAnswer = Character.toUpperCase(scanner.next().charAt(0));

            // Check the answer
            if (userAnswer == answers[i]) {
                System.out.println("✅ Correct!");
                score++;
            } else {
                System.out.println("❌ Wrong! Correct answer: " + answers[i]);
            }

            // Show current score
            System.out.println("Current Score: " + score + "/" + (i + 1));
        }

        // Final Score
        System.out.println("\n🎉 Quiz Over! Your Final Score: " + score + "/" + questions.length);

        scanner.close();
    }
}
