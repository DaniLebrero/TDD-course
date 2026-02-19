package p3dev;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OtherCasesTranslatorTests {

    static Stream<TestCase> otherCasesProvider() {
        return Stream.of(
            new TestCase(0, "0"),
            new TestCase(1, "1"),
            new TestCase(2, "2"),
            new TestCase(4, "4"),
            new TestCase(8, "8"),
            new TestCase(13, "13"),
            new TestCase(16, "16"),
            new TestCase(17, "17"),
            new TestCase(19, "19"),
            new TestCase(22, "22"),
            new TestCase(4376, "4376"),
            new TestCase(-4376, "-4376")
        );
    }

    @ParameterizedTest
    @MethodSource("otherCasesProvider")
    void should_return_number_as_string_when_other_case(TestCase testCase) {
        //Arrange
        NumberMultipleTranslator translator = new NumberMultipleTranslator();

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
