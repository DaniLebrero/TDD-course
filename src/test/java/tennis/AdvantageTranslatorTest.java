package tennis;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AdvantageTranslatorTest {

    @Test
    void should_apply_when_game_has_advantage() {
        TennisGame game = new TennisGame("Player1", "Player2", new StandardScoreFormatter());
        reachDeuce(game);
        game.pointWonBy("Player1"); // advantage Player1

        AdvantageTranslator translator = new AdvantageTranslator();

        assertTrue(translator.applies(game));
        assertEquals("advantage Player1", translator.translate(game));
    }

    @Test
    void should_throw_when_game_has_no_advantage() {
        TennisGame game = new TennisGame("Player1", "Player2", new StandardScoreFormatter());

        AdvantageTranslator translator = new AdvantageTranslator();

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
