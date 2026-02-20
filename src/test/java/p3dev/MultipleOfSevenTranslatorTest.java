package p3dev;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MultipleOfSevenTranslatorTest {

    static Stream<Integer> multipleOfSevenProvider() {
        return Stream.of(7, 14, 30474192, -163954);
    }

    static Stream<Integer> notMultipleOfSevenProvider() {
        return Stream.of(0, 1, 11, 7892331, -1234564);
    }

    @ParameterizedTest
    @MethodSource("multipleOfSevenProvider")
    void should_return_jazz_when_translate_is_called_with_multiple_of_seven(int number) {
        //Arrange
        MultipleOfSevenTranslator translator = new MultipleOfSevenTranslator();

        //Act
        String result = translator.translate(number);

        //Assert
        assertEquals("Jazz", result);
    }

    @ParameterizedTest
    @MethodSource("notMultipleOfSevenProvider")
    void should_throw_when_translate_is_called_with_not_multiple_of_seven(int number) {
        //Arrange
        MultipleOfSevenTranslator translator = new MultipleOfSevenTranslator();

        //Act - Assert
        assertThrows(IllegalArgumentException.class, () -> translator.translate(number));
    }
}
