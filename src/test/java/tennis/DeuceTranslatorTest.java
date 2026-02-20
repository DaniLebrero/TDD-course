package tennis;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DeuceTranslatorTest {

    @Test
    void should_apply_when_game_is_deuce() {
        TennisGame game = new TennisGame("Player1", "Player2", new StandardScoreFormatter());
        reachDeuce(game);

        DeuceTranslator translator = new DeuceTranslator();

        assertTrue(translator.applies(game));
        assertEquals("deuce", translator.translate(game));
    }

    @Test
    void should_throw_when_game_is_not_deuce() {
        TennisGame game = new TennisGame("Player1", "Player2", new StandardScoreFormatter());

        DeuceTranslator translator = new DeuceTranslator();

        assertThrows(IllegalArgumentException.class, () -> translator.translate(game));
    }

    private static void reachDeuce(TennisGame game) {
        for (int i = 0; i < 3; i++) {
            game.pointWonBy("Player1");
            game.pointWonBy("Player2");
        }
        game.pointWonBy("Player1");
        game.pointWonBy("Player2");
    }
}
