package p3dev;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MultipleOfFiveTranslatorTest {

    static Stream<Integer> multipleOfFiveProvider() {
        return Stream.of(5, 10, 7892330, -1234565);
    }

    @ParameterizedTest
    @MethodSource("multipleOfFiveProvider")
    void should_return_fizz_when_multiple_of_five(int number) {
        //Arrange
        NumberMultipleTranslator translator = new NumberMultipleTranslator();

        //Act
        String result = translator.translate(number);

        //Assert
        assertEquals("Buzz", result);
    }
}
