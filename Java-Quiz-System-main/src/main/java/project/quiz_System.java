package project;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class quiz_System {
    public static void main(String[] args) throws IOException, ParseException {
        //-1nich a input nilam  user nam r pass
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your username: ");
        String inputUsername = sc.nextLine();
        System.out.print("Enter your password: ");
        String inputPassword = sc.nextLine();

//------json parser diye users.jso file thake value read korlam
        JSONParser parser = new JSONParser();
        JSONArray users = (JSONArray) parser.parse(new FileReader("./src/main/resources/users.json"));
        boolean found = false;

        for (Object obj : users)
        {
            JSONObject user = (JSONObject) obj;
            String username = (String) user.get("username");
            String password = (String) user.get("password");
            String role = (String) user.get("role");

            if (username.equals(inputUsername) && password.equals(inputPassword))
            {
                found = true;
                if (role.equalsIgnoreCase("admin"))
                {
                    System.out.println("Welcome admin! Please create new questions in the question bank.");
                    addQuestions();
                }
                else if (role.equalsIgnoreCase("student"))
                {
                    System.out.println("Welcome " + username + " to the quiz! We will throw you 10 questions. Each MCQ mark is 1 and no negative marking. Are you ready? Press 's' to start.");

                    String start = sc.nextLine();
                    if (start.equalsIgnoreCase("s"))
                    {
                        runQuiz();
                    } else
                    {
                        System.out.println(" Quiz cancelled.");
                    }
                }
break;
            }
        }

        if (!found) {
            System.out.println("Invalid username or password!");
        }
    }

    public static void addQuestions() throws IOException, ParseException {
        Scanner sc = new Scanner(System.in);
        JSONParser parser = new JSONParser();

        File quizFile = new File("./src/main/resources/quiz.json");
        JSONArray quizArray;

        if (quizFile.exists() && quizFile.length() > 0) {
            quizArray = (JSONArray) parser.parse(new FileReader(quizFile));
        } else {
            quizArray = new JSONArray();
        }

        while (true) {
            System.out.print("Press 's' to start adding a question or 'q' to quit: ");
            String choice = sc.nextLine();

            if (choice.equalsIgnoreCase("q")) {
                break;
            }

            System.out.print("Input your question: ");
            String question = sc.nextLine();

            System.out.print("Input option 1: ");
            String option1 = sc.nextLine();

            System.out.print("Input option 2: ");
            String option2 = sc.nextLine();

            System.out.print("Input option 3: ");
            String option3 = sc.nextLine();

            System.out.print("Input option 4: ");
            String option4 = sc.nextLine();

            System.out.print("What is the answer key? (1-4): ");
            int answerKey = Integer.parseInt(sc.nextLine());

            JSONObject newQuestion = new JSONObject();
            newQuestion.put("question", question);
            newQuestion.put("option 1", option1);
            newQuestion.put("option 2", option2);
            newQuestion.put("option 3", option3);
            newQuestion.put("option 4", option4);
            newQuestion.put("answerkey", answerKey);

            quizArray.add(newQuestion);

            System.out.println(" Saved successfully!");
        }

        // quiz.json এ save করা
        try (FileWriter fileWriter = new FileWriter(quizFile)) {
            fileWriter.write(quizArray.toJSONString());
            fileWriter.flush();
        }

        System.out.println(" All questions saved to quiz bank!");
    }
    public static void runQuiz() throws IOException, ParseException {
        Scanner sc = new Scanner(System.in);
        JSONParser parser = new JSONParser();

        File quizFile = new File("./src/main/resources/quiz.json");
        if (!quizFile.exists()) {
            System.out.println("Quiz bank not found!");
            return;
        }

        JSONArray quizArray = (JSONArray) parser.parse(new FileReader(quizFile));
        if (quizArray.size() < 1) {
            System.out.println("Quiz bank is empty!");
            return;
        }

        // Shuffle and pick 10 random questions
        List<Object> questionList = new ArrayList<>(quizArray);
        Collections.shuffle(questionList);
        int totalQuestions = Math.min(10, questionList.size());
        int score = 0;

        for (int i = 0; i < totalQuestions; i++) {
            JSONObject question = (JSONObject) questionList.get(i);
            System.out.println("[Question " + (i + 1) + "] " + question.get("question"));
            System.out.println("1. " + question.get("option 1"));
            System.out.println("2. " + question.get("option 2"));
            System.out.println("3. " + question.get("option 3"));
            System.out.println("4. " + question.get("option 4"));

            System.out.print("Answer: ");
            String userInput = sc.nextLine();

            int userAnswer;
            try {
                userAnswer = Integer.parseInt(userInput);
            } catch (NumberFormatException e) {
                userAnswer = -1;
            }

            long correctAnswer = (long) question.get("answerkey");

            if (userAnswer == correctAnswer) {
                score++;
            }
        }

        // Show result
        System.out.println("\n Your score: " + score + " out of 10");

        if (score >= 8) {
            System.out.println(" Excellent! You have got " + score + " out of 10");
        } else if (score >= 5) {
            System.out.println(" Good. You have got " + score + " out of 10");
        } else if (score >= 2) {
            System.out.println(" Very poor! You have got " + score + " out of 10");
        } else {
            System.out.println(" Very sorry you are failed. You have got " + score + " out of 10");
        }

        System.out.print("\nWould you like to start again? Press 's' for start or 'q' for quit: ");
        String choice = sc.nextLine();
        if (choice.equalsIgnoreCase("s")) {
            runQuiz();
        } else {
            System.out.println(" Thank you for participating!");
        }
    }
}
