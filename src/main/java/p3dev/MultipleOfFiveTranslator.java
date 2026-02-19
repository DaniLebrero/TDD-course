package p3dev;

public class MultipleOfFiveTranslator implements INumberMultipleTranslator {
    @Override
    public boolean applies(int number) {
        return number != 0 && number % 5 == 0;
    }

    @Override
    public String translate(int number) {
        if (!applies(number)) throw new IllegalArgumentException("Number must be a multiple of 5");
        return "Buzz";
    }
}
