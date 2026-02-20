package p3dev;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MultipleOfFiveTranslatorTest {

    static Stream<Integer> multipleOfFiveProvider() {
        return Stream.of(5, 10, 7892330, -1234565);
    }

    static Stream<Integer> notMultipleOfFiveProvider() {
        return Stream.of(0, 1, 11, 7892331, -1234564);
    }

    @ParameterizedTest
    @MethodSource("multipleOfFiveProvider")
    void should_return_buzz_when_translate_is_called_with_multiple_of_five(int number) {
        //Arrange
        MultipleOfFiveTranslator translator = new MultipleOfFiveTranslator();

        //Act
        String result = translator.translate(number);

        //Assert
        assertEquals("Buzz", result);
    }

    @ParameterizedTest
    @MethodSource("notMultipleOfFiveProvider")
    void should_throw_when_translate_is_called_with_not_multiple_of_five(int number) {
        //Arrange
        MultipleOfFiveTranslator translator = new MultipleOfFiveTranslator();

        //Act - Assert
        assertThrows(IllegalArgumentException.class, () -> translator.translate(number));
    }
}
