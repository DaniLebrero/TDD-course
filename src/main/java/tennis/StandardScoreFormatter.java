package tennis;

public class StandardScoreFormatter implements ScoreFormatter {

    @Override
    public String format(TennisGame game) {
        if (game.isFinished()) {
            return "game " + game.winner();
        }

        if (game.isDeuce()) {
            return "deuce";
        }

        if (game.hasAdvantage()) {
            return "advantage " + game.advantagePlayer();
        }

        return pointName(game.player1Points()) + "-" + pointName(game.player2Points());
    }

    private String pointName(int points) {
        return switch (points) {
            case 0 -> "love";
            case 1 -> "fifteen";
            case 2 -> "thirty";
            case 3 -> "forty";
            default -> throw new IllegalArgumentException("Point out of range for naming: " + points);
        };
    }
}
