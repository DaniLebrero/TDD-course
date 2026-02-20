package p3dev;

public class MultipleOfSevenTranslator implements INumberMultipleTranslator {
    @Override
    public boolean applies(int number) {
        return number != 0 && number % 7 == 0;
    }

    @Override
    public String translate(int number) {
        if (!applies(number)) throw new IllegalArgumentException("Number must be a multiple of 7");
        return "Jazz";
    }
}
