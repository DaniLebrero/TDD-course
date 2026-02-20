package p3dev;

public class NumberTranslator {

    private final INumberMultipleTranslator[] translators;

    public NumberTranslator() {
        translators = new INumberMultipleTranslator[]{
                new MultipleOfThreeTranslator(),
                new MultipleOfFiveTranslator(),
                new MultipleOfSevenTranslator(),
                new MultipleOfElevenTranslator()
        };
    }

    public String translate(int number) {
        StringBuilder sb = new StringBuilder();

        for (INumberMultipleTranslator translator : translators) {
            if (translator.applies(number)) {
                sb.append(translator.translate(number));
            }
        }

        return sb.isEmpty() ? String.valueOf(number) : sb.toString();
    }
}
