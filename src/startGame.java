import java.util.Scanner;
public class startGame {
    private int initialValue;

    public startGame(int initialValue) {
        this.initialValue = initialValue;
    }

    public void go(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the 369 Game!");
        System.out.println("Enter the number of players:");

        int numPlayers = scanner.nextInt();
        System.out.println("Enter the target score:");
        int targetScore = scanner.nextInt();
        if (targetScore ==0){
            Game game = new Game(numPlayers);
            game.start();
        }else if (targetScore <=0){
            Game game = new Game(numPlayers, initialValue);
            game.start();
        }else {
            Game game = new Game(numPlayers, targetScore);
            game.start();
        }

        scanner.close();
    }
}

