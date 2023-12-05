public class Player {
    private String name;
    private int score;

    public Player(String name) {
        this.name = name;
        this.score = 0;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int num) {
        score = num;
    }

    public void rollDice() {
        int roll = (int) (Math.random() * 21) + 1;
        System.out.println("Rolled: " + roll);
        addToScore(roll);
    }

    private void addToScore(int points) {
        this.score += points;
    }
}

