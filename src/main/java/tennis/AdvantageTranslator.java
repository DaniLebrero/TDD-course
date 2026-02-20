package tennis;

public class AdvantageTranslator implements IGameScoreTranslator {
    @Override
    public boolean applies(TennisGame game) {
        return game.hasAdvantage();
    }

    @Override
    public String translate(TennisGame game) {
        if (!applies(game)) throw new IllegalArgumentException("Game has no advantage");
        return "advantage " + game.advantagePlayer();
    }
}
