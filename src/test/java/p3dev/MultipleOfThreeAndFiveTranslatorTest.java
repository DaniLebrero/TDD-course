package p3dev;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MultipleOfThreeAndFiveTranslatorTest {

    static Stream<Integer> multipleOfThreeAndFiveProvider() {
        return Stream.of(30, 615, 7892340, -12345615);
    }

    @ParameterizedTest
    @MethodSource("multipleOfThreeAndFiveProvider")
    void should_return_fizz_when_multiple_of_three_and_five(int number) {
        //Arrange
        NumberMultipleTranslator translator = new NumberMultipleTranslator();

        //Act
        String result = translator.translate(number);

        //Assert
        assertEquals("FizzBuzz", result);
    }
}
