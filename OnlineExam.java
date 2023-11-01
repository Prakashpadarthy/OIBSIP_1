import java.util.Scanner;

public class OnlineExam {
    private final String username;
    private final String password;
    private boolean isLoggedIn;
    private final int timeLimitMinutes;
    private final int questionCount;
    private final int[] Response;
    private final int[] Correct;

    public OnlineExam(String username, String password) {
        this.username = username;
        this.password = password;
        System.out.println("Registration successful! :)");
        this.isLoggedIn = false;
        this.timeLimitMinutes = 10;
        this.questionCount = 5;
        this.Response = new int[questionCount];
        this.Correct = new int[questionCount];
        
        for (int i = 0; i < questionCount; i++) {
            Correct[i] = (int) Math.round(Math.random());
        }
    }

    public void Login() {
        System.out.println("Please log in to start the  Test.");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Username: ");
        String inputUsername = scanner.nextLine();
        System.out.print("Password: ");
        String inputPassword = scanner.nextLine();
        if (inputUsername.equals(username) && inputPassword.equals(password)) {
            isLoggedIn = true;
            System.out.println("Login successful. Good luck!");
        } else {
            System.out.println("Login failed. Please try again.");
        }
    }

    public void logout() {
        isLoggedIn = false;
        System.out.println("Logout successful.");
    }

    public void startExam() {
        if (!isLoggedIn) {
            System.out.println("Please log in first.");
            return;
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("Complete the test in " + timeLimitMinutes + " minutes.");
        System.out.println("\nElse,Your exam will be auto submitted after the time ");
        System.out.println("\nAll the best !");
        for (int i = 0; i < questionCount; i++) {
            System.out.println("Question " + (i + 1) + ":");
            System.out.println("1. Option 1");
            System.out.println("2. Option 2");
            System.out.print("Your answer (1 or 2): ");
            int response = scanner.nextInt();
            Response[i] = response;
        }

        System.out.println("Would you like to submit the test ? \n1: Yes \n2: No ");
        int choice = scanner.nextInt();
        if (choice == 1) {
            submitExam();
        } else {
            try {
                Thread.sleep(timeLimitMinutes * 10 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                submitExam();
            }

        }

    }

    public void submitExam() {
        if (!isLoggedIn) {
            System.out.println("Please log in first.");
            return;
        }
        int score = 0;
        for (int i = 0; i < questionCount; i++) {
            if (Response[i] == Correct[i]) {
                score++;
            }
        }
        System.out.println("Congratulations!:)");
        System.out.println("Your have Scored " + score + " out of " + questionCount + ".");
        System.out.println("Best of luck !:)");
        logout();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your username and password:");
        String username = scanner.nextLine();
        String password = scanner.nextLine();
        OnlineExam examSystem = new OnlineExam(username, password);
        examSystem.Login();
        examSystem.startExam();
    }
}
