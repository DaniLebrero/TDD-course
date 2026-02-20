package tennis;

public interface IGameScoreTranslator {
    boolean applies(TennisGame game);

    String translate(TennisGame game);
}
