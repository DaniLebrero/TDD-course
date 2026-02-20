package tennis;

public class StandardScoreFormatter implements ScoreFormatter {

    private final IGameScoreTranslator[] translators;

    public StandardScoreFormatter() {
        translators = new IGameScoreTranslator[]{
                new GameFinishedTranslator(),
                new DeuceTranslator(),
                new AdvantageTranslator(),
                new NormalScoreTranslator()
        };
    }

    @Override
    public String format(TennisGame game) {
        for (IGameScoreTranslator translator : translators) {
            if (translator.applies(game)) {
                return translator.translate(game);
            }
        }

        throw new IllegalStateException("No score translator applied");
    }
}
