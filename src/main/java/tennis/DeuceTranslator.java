package tennis;

public class DeuceTranslator implements IGameScoreTranslator {
    @Override
    public boolean applies(TennisGame game) {
        return game.isDeuce();
    }

    @Override
    public String translate(TennisGame game) {
        if (!applies(game)) throw new IllegalArgumentException("Game is not in deuce");
        return "deuce";
    }
}
