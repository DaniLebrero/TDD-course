package p3dev;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MultipleOfElevenTranslatorTest {

    static Stream<Integer> multipleOfElevenProvider() {
        return Stream.of(11, 22, 121, 257752, -10325183);
    }

    static Stream<Integer> notMultipleOfElevenProvider() {
        return Stream.of(0, 1, 7, 7892331, -1234564);
    }

    @ParameterizedTest
    @MethodSource("multipleOfElevenProvider")
    void should_return_mozz_when_translate_is_called_with_multiple_of_eleven(int number) {
        //Arrange
        MultipleOfElevenTranslator translator = new MultipleOfElevenTranslator();

        //Act
        String result = translator.translate(number);

        //Assert
        assertEquals("Mozz", result);
    }

    @ParameterizedTest
    @MethodSource("notMultipleOfElevenProvider")
    void should_throw_when_translate_is_called_with_not_multiple_of_eleven(int number) {
        //Arrange
        MultipleOfElevenTranslator translator = new MultipleOfElevenTranslator();

        //Act - Assert
        assertThrows(IllegalArgumentException.class, () -> translator.translate(number));
    }
}
