package tennis;

public class NormalScoreTranslator implements IGameScoreTranslator {
    @Override
    public boolean applies(TennisGame game) {
        return true;
    }

    @Override
    public String translate(TennisGame game) {
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
