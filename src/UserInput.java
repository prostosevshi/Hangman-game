import java.util.Scanner;

public class UserInput {
    Scanner sc = new Scanner(System.in);

    public char charInput() {
        return sc.next().charAt(0);
    }

    public String wordInput() {
        return sc.nextLine();
    }
}
