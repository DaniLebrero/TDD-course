package tennis;

public class GameFinishedTranslator implements IGameScoreTranslator {
    @Override
    public boolean applies(TennisGame game) {
        return game.isFinished();
    }

    @Override
    public String translate(TennisGame game) {
        if (!applies(game)) throw new IllegalArgumentException("Game is not finished");
        return "game " + game.winner();
    }
}
