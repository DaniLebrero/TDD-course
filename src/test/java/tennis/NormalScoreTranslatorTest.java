package tennis;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class NormalScoreTranslatorTest {

    static Stream<TestCase> scoreProvider() {
        return Stream.of(
                new TestCase(0, 0, "love-love"),
                new TestCase(1, 0, "fifteen-love"),
                new TestCase(2, 1, "thirty-fifteen"),
                new TestCase(3, 2, "forty-thirty")
        );
    }

    @ParameterizedTest
    @MethodSource("scoreProvider")
    void should_translate_standard_scores(TestCase testCase) {
        TennisGame game = new TennisGame("Player1", "Player2", new StandardScoreFormatter());
        scorePoints(game, "Player1", testCase.p1Points);
        scorePoints(game, "Player2", testCase.p2Points);

        NormalScoreTranslator translator = new NormalScoreTranslator();

        assertEquals(testCase.expected, translator.translate(game));
    }

    @ParameterizedTest
    @MethodSource("invalidScoreProvider")
    void should_throw_when_points_out_of_range(TestCase testCase) {
        TennisGame game = new TennisGame("Player1", "Player2", new StandardScoreFormatter());
        scorePoints(game, "Player1", testCase.p1Points);
        scorePoints(game, "Player2", testCase.p2Points);

        NormalScoreTranslator translator = new NormalScoreTranslator();

        assertThrows(IllegalArgumentException.class, () -> translator.translate(game));
    }

    static Stream<TestCase> invalidScoreProvider() {
        return Stream.of(
                new TestCase(4, 0, "game Player1"),
                new TestCase(0, 4, "game Player2")
        );
    }

    private static void scorePoints(TennisGame game, String player, int points) {
        for (int i = 0; i < points; i++) {
            game.pointWonBy(player);
        }
    }

    static class TestCase {
        int p1Points;
        int p2Points;
        String expected;

        TestCase(int p1Points, int p2Points, String expected) {
            this.p1Points = p1Points;
            this.p2Points = p2Points;
            this.expected = expected;
        }
    }
}
