

# 🟦 Clase: `TennisGame`

---

* **TennisGame(String player1, String player2, ScoreFormatter formatter)**:
  Constructor de la clase. Inicializa los nombres de los jugadores y el formateador del marcador. Aplica el principio **Dependency Inversion (SOLID)** al recibir el `ScoreFormatter` por parámetro en vez de crearlo internamente.

---

* **void pointWonBy(String playerName)**:
  Suma un punto al jugador indicado.
  Primero verifica que el juego no haya terminado (`ensureNotFinished()`), y luego incrementa los puntos del jugador correspondiente.
  Si el nombre no coincide con ninguno de los jugadores, lanza una excepción.

---

* **String score()**:
  Devuelve el marcador actual del juego en formato texto.
  No genera el texto directamente, sino que delega esa responsabilidad al `ScoreFormatter`, respetando el principio de **Single Responsibility**.

---

* **boolean isFinished()**:
  Indica si el juego ha terminado.
  Internamente delega la lógica a `hasWinner()`.

---

* **String winner()**:
  Devuelve el nombre del jugador que ha ganado el juego.
  Solo puede llamarse si el juego ha terminado; si no, lanza una excepción.

---

* **int player1Points()**:
  Devuelve los puntos actuales del jugador 1.
  Es un método de consulta (getter) usado por el formateador.

---

* **int player2Points()**:
  Devuelve los puntos actuales del jugador 2.
  También es un método de consulta usado por el formateador.

---

* **boolean isDeuce()**:
  Devuelve `true` si ambos jugadores tienen al menos 3 puntos (40) y están empatados.
  Representa el estado especial de **deuce**.

---

* **boolean hasAdvantage()**:
  Devuelve `true` si ambos jugadores tienen al menos 3 puntos y uno tiene exactamente un punto más que el otro.
  Representa el estado de **ventaja**.

---

* **String advantagePlayer()**:
  Devuelve el nombre del jugador que tiene ventaja.
  Solo puede llamarse si realmente hay ventaja; si no, lanza excepción.

---

* **boolean hasWinner()** *(privado)*:
  Determina si hay un ganador del juego.
  Un jugador gana si tiene al menos 4 puntos y una diferencia mínima de 2 puntos.

---

* **void ensureNotFinished()** *(privado)*:
  Lanza una excepción si el juego ya ha terminado.
  Evita que se sigan sumando puntos después de finalizar el juego.

---

# 🟦 Clase: `StandardScoreFormatter`

---

* **String format(TennisGame game)**:
  Genera el texto que representa el marcador del juego.
  Evalúa en orden:

    1. Si el juego terminó → `"game PlayerX"`
    2. Si está en deuce → `"deuce"`
    3. Si hay ventaja → `"advantage PlayerX"`
    4. En caso normal → `"fifteen-thirty"`, etc.

  Este método encapsula la lógica de representación textual.

---

* **String pointName(int points)** *(privado)*:
  Convierte el valor numérico interno del punto (0,1,2,3) en su nombre textual:

    * 0 → love
    * 1 → fifteen
    * 2 → thirty
    * 3 → forty

---

# 🟦 Clase: `ScoreFormatter` (interfaz)

---

* **String format(TennisGame game)**:
  Define el contrato que cualquier formateador debe implementar.
  Permite cambiar la forma en que se muestra el marcador sin modificar `TennisGame` (**Open/Closed Principle**).

---

# 🟦 Clase: `TennisMatch`

---

* **TennisMatch(String player1, String player2)**:
  Constructor.
  Inicializa los jugadores, pone el contador de juegos a 0 y crea el primer `TennisGame`.

---

* **void pointWonBy(String playerName)**:
  Añade un punto al juego actual.
  Si el juego termina:

    * Suma un juego al ganador.
    * Crea un nuevo `TennisGame` si el partido no ha terminado.

---

* **boolean isFinished()**:
  Devuelve `true` si el partido ha terminado.
  Internamente usa `hasMatchWinner()`.

---

* **String matchWinner()**:
  Devuelve el nombre del ganador del partido.
  Solo puede llamarse si el partido ha terminado.

---

* **String matchScore()**:
  Devuelve el estado actual del partido:

    * Si terminó → `"match Player1 (sets: X-Y)"`
    * Si no terminó → `"sets: X-Y (current game: ...)"`

---

* **boolean hasMatchWinner()** *(privado)*:
  Determina si hay ganador del partido.
  Según tu regla:

    * Debe tener al menos 4 juegos
    * Y una diferencia mínima de 2 juegos

---

* **void ensureNotFinished()** *(privado)*:
  Evita que se sigan sumando puntos si el partido ya terminó.

---

# 🔎 Resumen Arquitectónico (importante para entrevista)

* `TennisGame` → lógica de puntos
* `TennisMatch` → lógica de juegos
* `ScoreFormatter` → presentación del marcador
