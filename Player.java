public class Player {
    private String username;
    private int totalGames;
    private int wins;

    public Player(String username) {
        this.username = username;
        this.totalGames = 0;
        this.wins = 0;
    }

    public Player(Player player) {
        this.username = player.username;
        this.totalGames = player.totalGames;
        this.wins = player.wins;
    }

    public String getUsername() {
        return username;
    }

    public void incrementWins() {
        wins++;
    }

    public void incrementTotalGames() {
        totalGames++;
    }

    public double getWinPercentage() {
        if (totalGames == 0) {
            return 0;
        }
        return ((double) wins / totalGames) * 100;
    }

    public int getWins() {
        return wins;
    }
}