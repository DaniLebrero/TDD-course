package tennis;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GameFinishedTranslatorTest {

    @Test
    void should_apply_when_game_is_finished() {
        TennisGame game = new TennisGame("Player1", "Player2", new StandardScoreFormatter());
        winQuickGame(game, "Player1");

        GameFinishedTranslator translator = new GameFinishedTranslator();

        assertTrue(translator.applies(game));
        assertEquals("game Player1", translator.translate(game));
    }

    @Test
    void should_throw_when_game_is_not_finished() {
        TennisGame game = new TennisGame("Player1", "Player2", new StandardScoreFormatter());

        GameFinishedTranslator translator = new GameFinishedTranslator();

        assertThrows(IllegalArgumentException.class, () -> translator.translate(game));
    }

    private static void winQuickGame(TennisGame game, String player) {
        game.pointWonBy(player);
        game.pointWonBy(player);
        game.pointWonBy(player);
        game.pointWonBy(player);
    }
}
