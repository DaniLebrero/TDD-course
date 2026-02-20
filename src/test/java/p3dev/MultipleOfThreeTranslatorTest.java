package p3dev;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MultipleOfThreeTranslatorTest {

    static Stream<Integer> multipleOfThreeProvider() {
        return Stream.of(3, 6, 789234, -123456);
    }

    static Stream<Integer> notMultipleOfThreeProvider() {
        return Stream.of(0, 1, 2, 11, 789235, -123457);
    }

    @ParameterizedTest
    @MethodSource("multipleOfThreeProvider")
    void should_return_fizz_when_translate_is_called_with_multiple_of_three(int number) {
        //Arrange
        MultipleOfThreeTranslator translator = new MultipleOfThreeTranslator();

        //Act
        String result = translator.translate(number);

        //Assert
        assertEquals("Fizz", result);
    }

    @ParameterizedTest
    @MethodSource("notMultipleOfThreeProvider")
    void should_throw_when_translate_is_called_with_not_multiple_of_three(int number) {
        //Arrange
        MultipleOfThreeTranslator translator = new MultipleOfThreeTranslator();

        //Act - Assert
        assertThrows(IllegalArgumentException.class, () -> translator.translate(number));
    }
}
