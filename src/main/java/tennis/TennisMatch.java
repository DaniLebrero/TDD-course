package tennis;

public class TennisMatch {

    private final String player1;
    private final String player2;

    private int p1Games = 0;
    private int p2Games = 0;

    private TennisGame currentGame;

    public TennisMatch(String player1, String player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.currentGame = new TennisGame(player1, player2, new StandardScoreFormatter());
    }

    public void pointWonBy(String playerName) {
        ensureNotFinished();

        currentGame.pointWonBy(playerName);

        if (currentGame.isFinished()) {
            String winner = currentGame.winner();
            if (winner.equals(player1)) p1Games++;
            else p2Games++;

            // Nuevo juego si el partido no ha terminado
            if (!isFinished()) {
                currentGame = new TennisGame(player1, player2, new StandardScoreFormatter());
            }
        }
    }

    public boolean isFinished() {
        return hasMatchWinner();
    }

    public String matchWinner() {
        if (!hasMatchWinner()) throw new IllegalStateException("Match is not finished");
        return p1Games > p2Games ? player1 : player2;
    }

    public String matchScore() {
        if (isFinished()) {
            return "match " + matchWinner() + " (sets: " + p1Games + "-" + p2Games + ")";
        }
        return "sets: " + p1Games + "-" + p2Games + " (current game: " + currentGame.score() + ")";
    }

    private boolean hasMatchWinner() {
        // “El partido lo ganará el primero que gane 4 juegos y al menos 2 más que el oponente”
        if (p1Games >= 4 || p2Games >= 4) {
            return Math.abs(p1Games - p2Games) >= 2;
        }
        return false;
    }

    private void ensureNotFinished() {
        if (isFinished()) throw new IllegalStateException("Match already finished");
    }
}
