package tennis;

import java.util.Objects;

public class TennisGame {

    private final String player1;
    private final String player2;
    private final ScoreFormatter formatter;

    private int p1Points = 0;
    private int p2Points = 0;

    public TennisGame(String player1, String player2, ScoreFormatter formatter) {
        this.player1 = Objects.requireNonNull(player1);
        this.player2 = Objects.requireNonNull(player2);
        this.formatter = Objects.requireNonNull(formatter);
    }

    public void pointWonBy(String playerName) {
        ensureNotFinished();

        if (player1.equals(playerName)) {
            p1Points++;
        } else if (player2.equals(playerName)) {
            p2Points++;
        } else {
            throw new IllegalArgumentException("Unknown player: " + playerName);
        }
    }

    public String score() {
        return formatter.format(this);
    }

    public boolean isFinished() {
        return hasWinner();
    }

    public String winner() {
        if (!hasWinner()) throw new IllegalStateException("Game is not finished");
        return p1Points > p2Points ? player1 : player2;
    }

    // --- Query methods used by ScoreFormatter (SRP-friendly) ---

    public int player1Points() { return p1Points; }
    public int player2Points() { return p2Points; }

    public boolean isDeuce() {
        return p1Points >= 3 && p2Points >= 3 && p1Points == p2Points;
    }

    public boolean hasAdvantage() {
        return p1Points >= 3 && p2Points >= 3 && Math.abs(p1Points - p2Points) == 1;
    }

    public String advantagePlayer() {
        if (!hasAdvantage()) throw new IllegalStateException("No advantage");
        return p1Points > p2Points ? player1 : player2;
    }

    // --- Internal rules ---

    private boolean hasWinner() {
        // gana si llega a 4+ puntos con diferencia de 2
        if (p1Points >= 4 || p2Points >= 4) {
            return Math.abs(p1Points - p2Points) >= 2;
        }
        return false;
    }

    private void ensureNotFinished() {
        if (isFinished()) {
            throw new IllegalStateException("Game already finished");
        }
    }
}
