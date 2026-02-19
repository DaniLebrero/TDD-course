package tennis;

import org.junit.jupiter.api.Test;
import tennis.TennisGame;

import static org.junit.jupiter.api.Assertions.*;

class TennisGameTest {

    @Test
    void newGame_startsLoveLove() {
        // Arrange
        TennisGame game = new TennisGame("Player1", "Player2", new StandardScoreFormatter());

        // Act
        String score = game.score();

        // Assert
        assertEquals("love-love", score);
    }

    @Test
    void player1_scoresOnce_fifteenLove() {
        // Arrange
        TennisGame game = new TennisGame("Player1", "Player2", new StandardScoreFormatter());

        // Act
        game.pointWonBy("Player1");

        // Assert
        assertEquals("fifteen-love", game.score());
    }

    @Test
    void player1_scoresTwice_thirtyLove() {
        // Arrange
        TennisGame game = new TennisGame("Player1", "Player2", new StandardScoreFormatter());

        // Act
        game.pointWonBy("Player1");
        game.pointWonBy("Player1");

        // Assert
        assertEquals("thirty-love", game.score());
    }

    @Test
    void player2_scoresThreeTimes_loveForty() {
        // Arrange
        TennisGame game = new TennisGame("Player1", "Player2", new StandardScoreFormatter());

        // Act
        game.pointWonBy("Player2");
        game.pointWonBy("Player2");
        game.pointWonBy("Player2");

        // Assert
        assertEquals("love-forty", game.score());
    }

    @Test
    void fortyForty_isDeuce() {
        // Arrange
        TennisGame game = new TennisGame("Player1", "Player2", new StandardScoreFormatter());
        for (int i = 0; i < 3; i++) {
            game.pointWonBy("Player1");
            game.pointWonBy("Player2");
        }

        // Act
        game.pointWonBy("Player1");
        game.pointWonBy("Player2");

        // Assert
        assertEquals("deuce", game.score());
    }

    @Test
    void fromDeuce_player1_scores_advantagePlayer1() {
        // Arrange
        TennisGame game = new TennisGame("Player1", "Player2", new StandardScoreFormatter());
        reachDeuce(game);

        // Act
        game.pointWonBy("Player1");

        // Assert
        assertEquals("advantage Player1", game.score());
    }

    @Test
    void fromAdvantage_ifOpponentScores_backToDeuce() {
        // Arrange
        TennisGame game = new TennisGame("Player1", "Player2", new StandardScoreFormatter());
        reachDeuce(game);
        game.pointWonBy("Player1"); // advantage Player1

        // Act
        game.pointWonBy("Player2");

        // Assert
        assertEquals("deuce", game.score());
    }

    @Test
    void fromAdvantage_ifSamePlayerScores_gameIsWon() {
        // Arrange
        TennisGame game = new TennisGame("Player1", "Player2", new StandardScoreFormatter());
        reachDeuce(game);
        game.pointWonBy("Player1"); // advantage Player1

        // Act
        game.pointWonBy("Player1");

        // Assert
        assertTrue(game.isFinished());
        assertEquals("game Player1", game.score());
    }

    @Test
    void beforeDeuce_ifPlayerReachesFourPointsWithTwoDifference_winsGame() {
        // Arrange
        TennisGame game = new TennisGame("Player1", "Player2", new StandardScoreFormatter());
        game.pointWonBy("Player1");
        game.pointWonBy("Player1");
        game.pointWonBy("Player1"); // 40-love

        // Act
        game.pointWonBy("Player1");

        // Assert
        assertTrue(game.isFinished());
        assertEquals("game Player1", game.score());
    }

    private static void reachDeuce(TennisGame game) {
        for (int i = 0; i < 3; i++) {
            game.pointWonBy("Player1");
            game.pointWonBy("Player2");
        }
        game.pointWonBy("Player1"); // 40
        game.pointWonBy("Player2"); // 40 -> deuce
    }
}
