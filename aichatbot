import java.util.Scanner;

public class Chatbot {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        String[] questions = new String[50];
        String[] answers = new String[50];

        int size = 6;

        questions[0] = "admission";
        answers[0] = "Admissions open in August.";

        questions[1] = "fee";
        answers[1] = "Fee details are available in the accounts office.";

        questions[2] = "hostel";
        answers[2] = "Hostel facility is available for students.";

        questions[3] = "scholarship";
        answers[3] = "Scholarships are available for high achievers.";

        questions[4] = "course";
        answers[4] = "We offer BSCS, BSSE and BSAI.";

        questions[5] = "contact";
        answers[5] = "Contact us at 123456789.";

        String[] history = new String[100];
        int historycount = 0;

        System.out.println("=================================");
        System.out.println("      AI UNIVERSITY CHATBOT");
        System.out.println("=================================");
        System.out.println("Type 'bye' to exit.");
        System.out.println();

        while (true) {

            System.out.print("You: ");
            String message = input.nextLine();

            history[historycount] = message;
            historycount++;

            if (message.equalsIgnoreCase("bye")) {
                System.out.println("Bot: Goodbye!");
                break;
            }

            // NLP Processing
            String processed = message.toLowerCase();
            processed = processed.replace("?", "");
            processed = processed.replace(".", "");
            processed = processed.replace(",", "");

            boolean found = false;

            if (processed.contains("hello") ||
                processed.contains("hi")) {

                System.out.println("Bot: Hello! How can I help you?");
                found = true;
            }

            else if (processed.contains("thanks")) {

                System.out.println("Bot: You're welcome.");
                found = true;
            }

            else {

                for (int i = 0; i < size; i++) {

                    if (processed.contains(questions[i])) {

                        System.out.println("Bot: " + answers[i]);
                        found = true;
                        break;
                    }
                }
            }

            if (!found) {

                System.out.println("Bot: I don't know the answer.");
                System.out.print("Would you like to teach me? (yes/no): ");

                String choice = input.nextLine();

                if (choice.equalsIgnoreCase("yes")) {

                    System.out.print("Enter keyword: ");
                    String newquestion = input.nextLine().toLowerCase();

                    System.out.print("Enter answer: ");
                    String newanswer = input.nextLine();

                    questions[size] = newquestion;
                    answers[size] = newanswer;

                    size++;

                    System.out.println("Bot: Thank you! I learned something new.");
                }
            }

            System.out.println();
        }

        System.out.println("\n========== CHAT HISTORY ==========");

        for (int i = 0; i < historycount; i++) {
            System.out.println((i + 1) + ". " + history[i]);
        }

        input.close();
    }
}
