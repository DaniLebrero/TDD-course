package p3dev;

public class OtherCasesTranslator implements INumberMultipleTranslator {
    @Override
    public boolean applies(int number) {
        return true;
    }

    @Override
    public String translate(int number) {
        return String.valueOf(number);
    }
}
