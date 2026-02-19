package tennis;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TennisMatchTest {

    @Test
    void match_startsWithZeroGames() {
        // Arrange
        TennisMatch match = new TennisMatch("Player1", "Player2");

        // Act
        String score = match.matchScore();

        // Assert
        assertEquals("sets: 0-0 (current game: love-love)", score);
    }

    @Test
    void player1_winsFourGamesButOnlyOneAhead_notWinnerYet() {
        // Arrange
        TennisMatch match = new TennisMatch("Player1", "Player2");

        // Act
        winGame(match, "Player1"); // 1-0
        winGame(match, "Player2"); // 1-1
        winGame(match, "Player1"); // 2-1
        winGame(match, "Player2"); // 2-2
        winGame(match, "Player1"); // 3-2
        winGame(match, "Player2"); // 3-3
        winGame(match, "Player1"); // 4-3

        // Assert
        assertFalse(match.isFinished());
        assertEquals("sets: 4-3 (current game: love-love)", match.matchScore());
    }

    @Test
    void player1_wins4GamesAndTwoAhead_winsMatch() {
        // Arrange
        TennisMatch match = new TennisMatch("Player1", "Player2");

        // Act
        winGame(match, "Player1");
        winGame(match, "Player1");
        winGame(match, "Player2");
        winGame(match, "Player1");
        winGame(match, "Player2");
        winGame(match, "Player1"); // 4-2

        // Assert
        assertTrue(match.isFinished());
        assertEquals("match Player1 (sets: 4-2)", match.matchScore());
    }

    @Test
    void longMatch_6_4_isValidWinCondition() {
        // Arrange
        TennisMatch match = new TennisMatch("Player1", "Player2");

        // Act
        // Alternamos para llegar a 3-3 (nadie puede ganar aún)
        for (int i = 0; i < 3; i++) {
            winGame(match, "Player1"); // 1-0, 2-1, 3-2
            winGame(match, "Player2"); // 1-1, 2-2, 3-3
        }

        // Ahora sí: Player1 gana 3 seguidos -> 6-3 (aquí ya ganaría)
        winGame(match, "Player1"); // 4-3 (no gana: solo +1)
        winGame(match, "Player2"); // 4-4 (empate)
        winGame(match, "Player1"); // 5-4
        winGame(match, "Player1"); // 6-4 -> gana

        // Assert
        assertTrue(match.isFinished());
        assertEquals("match Player1 (sets: 6-4)", match.matchScore());
    }


    private static void winGame(TennisMatch match, String player) {
        // Un juego “rápido”: 4 puntos seguidos antes de deuce
        match.pointWonBy(player);
        match.pointWonBy(player);
        match.pointWonBy(player);
        match.pointWonBy(player);
    }
}
