package p3dev;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NumberTranslatorTests {

    static Stream<TestCase> testCasesProvider() {
        return Stream.of(
                new TestCase(0, "0"),
                new TestCase(1, "1"),
                new TestCase(2, "2"),
                new TestCase(3, "Fizz"),
                new TestCase(4, "4"),
                new TestCase(5, "Buzz"),
                new TestCase(7, "Jazz"),
                new TestCase(11, "Mozz"),
                new TestCase(8, "8"),
                new TestCase(13, "13"),
                new TestCase(15, "FizzBuzz"),
                new TestCase(16, "16"),
                new TestCase(17, "17"),
                new TestCase(19, "19"),
                new TestCase(21, "FizzJazz"),
                new TestCase(33, "FizzMozz"),
                new TestCase(35, "BuzzJazz"),
                new TestCase(55, "BuzzMozz"),
                new TestCase(77, "JazzMozz"),
                new TestCase(105, "FizzBuzzJazz"),
                new TestCase(165, "FizzBuzzMozz"),
                new TestCase(231, "FizzJazzMozz"),
                new TestCase(385, "BuzzJazzMozz"),
                new TestCase(1155, "FizzBuzzJazzMozz"),
                new TestCase(-1155, "FizzBuzzJazzMozz"),
                new TestCase(4376, "4376"),
                new TestCase(-4376, "-4376")
        );
    }

    @ParameterizedTest
    @MethodSource("testCasesProvider")
    void should_return_expected_when_test_is_called(TestCase testCase) {
        //Arrange
        NumberTranslator translator = new NumberTranslator();

        //Act
        String result = translator.translate(testCase.number);

        //Assert
        assertEquals(testCase.expectedResult, result);
    }

    static class TestCase {
        int number;
        String expectedResult;

        TestCase(int number, String expectedResult) {
            this.number = number;
            this.expectedResult = expectedResult;
        }

        @Override
        public String toString() {
            return String.format("%d should return \"%s\"", number, expectedResult);
        }
    }
}
