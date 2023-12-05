import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Game {
    private Player[] players;
    private int currentPlayerIndex;
    private int targetScore = 369;
    private Scanner myScanner;

    public Game(int numPlayers) {
        players = new Player[numPlayers];
        currentPlayerIndex = 0;
        myScanner = new Scanner(System.in);
    }

    public Game(int numPlayers, int targetScore) {
        players = new Player[numPlayers];
        currentPlayerIndex = 0;
        myScanner = new Scanner(System.in);
        this.targetScore = targetScore;
    }

    public void start() {
        getData();
    }

    public int getTargetScore() {
        return targetScore;
    }

    private void getData() {
        System.out.println("Enter player names:");
        for (int i = 0; i < players.length; i++) {
            System.out.print("Player " + (i + 1) + ": ");
            String playerName = myScanner.next();
            Player player = new Player(playerName);
            addPlayer(player);
        }

        play();
        myScanner.close();
    }

    public void addPlayer(Player player) {
        players[currentPlayerIndex++] = player;
    }

    public void play() {
        boolean endGame = false;

        while (!endGame) {
            boolean gameWon = false;
            while (!gameWon) {
                Player currentPlayer = players[currentPlayerIndex % players.length];
                System.out.println("\n" + currentPlayer.getName() + "'s turn:");

                int currentRoundScore = currentPlayer.getScore();
                currentPlayer.rollDice();

                if (currentRoundScore >= targetScore) {
                    System.out.println("Total Score: " + currentRoundScore);
                    System.out.println(currentPlayer.getName() + " wins!");
                    gameWon = true;
                } else {
                    System.out.println("Total Score: " + currentRoundScore);
                }

                currentPlayerIndex++;
            }

            int correctRiddles = 0;

            List<RiddleAnswerPair> riddleAnswerPairs = new ArrayList<>();
            riddleAnswerPairs.add(new RiddleAnswerPair("I speak without a mouth and hear without ears. I have no body, but I come alive with the wind. What am I? (7 letters)", "whisper"));
            riddleAnswerPairs.add(new RiddleAnswerPair("I have keys but no locks. I have space but no room. You can enter, but you can't go inside. What am I? (8 letters)", "keyboard"));
            riddleAnswerPairs.add(new RiddleAnswerPair("The more you take, the more you leave behind. What am I? (9 letters)", "footsteps"));
            riddleAnswerPairs.add(new RiddleAnswerPair("I have cities but no houses. I have mountains but no trees. I have water but no fish. What am I? (3 letters)", "map"));

            // Shuffle the ridles and answers together
            Collections.shuffle(riddleAnswerPairs);

            for (RiddleAnswerPair pair : riddleAnswerPairs) {//iterate over elements of a collection
                System.out.println(pair.getRiddle());
                String answer = myScanner.nextLine();

                if (answer.length() == pair.getAnswer().length() && answer.equalsIgnoreCase(pair.getAnswer())) {
                    System.out.println("Congratulations! You guessed the riddle correctly.");
                    correctRiddles++;
                } else {
                    resetAllPlayerScores();
                    System.out.println("Incorrect answer. All scores reset to zero.");
                    gameWon = false;
                    break;
                }
            }

            if (correctRiddles == riddleAnswerPairs.size()) {
                System.out.println("Game over. All riddles solved!");
                endGame = true;
            }
        }
    }
    private static class RiddleAnswerPair {
        private final String riddle;
        private final String answer;

        public RiddleAnswerPair(String riddle, String answer) {
            this.riddle = riddle;
            this.answer = answer;
        }

        public String getRiddle() {
            return riddle;
        }

        public String getAnswer() {
            return answer;
        }
    }
    private void resetAllPlayerScores() {
        for (Player player : players) {
            player.setScore(0);
        }
    }
}
