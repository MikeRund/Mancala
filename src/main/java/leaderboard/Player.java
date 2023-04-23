package leaderboard;

public class Player {
    private String username;
    private int totalGames;
    private int wins;
    private int losses;
    private int rank;

    public Player(String username) {
        this.username = username;
        this.totalGames = 0;
        this.wins = 0;
        this.losses = 0;
    }

    public Player(Player player) {
        this.username = player.username;
        this.totalGames = player.totalGames;
        this.wins = player.wins;
        this.losses = player.losses;
    }

    public String getUsername() {
        return username;
    }

    public double getWinPercentage() {
        if (totalGames == 0) {
            return 0;
        }
        return ((double) wins / totalGames) * 100;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getWins() {
        return wins;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public int getLosses(){
        return losses;
    }

    public void setTotalGames(int totalGames) {
        this.totalGames = totalGames;
    }

    public int getTotalGames() {
        return totalGames;
    }

    public int getRank() {
        return rank;
    }
    
    public void setRank(int rank) {
        this.rank = rank;
    }
}