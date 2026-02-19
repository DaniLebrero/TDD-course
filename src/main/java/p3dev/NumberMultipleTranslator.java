package p3dev;

import java.util.List;

public class NumberMultipleTranslator {

    private final List<INumberMultipleTranslator> translators;
    private final INumberMultipleTranslator defaultTranslator;

    public NumberMultipleTranslator() {
        this.translators = List.of(
            new MultipleOfThreeTranslator(),
            new MultipleOfFiveTranslator()
        );

        this.defaultTranslator = new OtherCasesTranslator();
    }

    public String translate(int number) {
        StringBuilder sb = new StringBuilder();

        for (INumberMultipleTranslator translator : this.translators) {
            if (translator.applies(number)) sb.append(translator.translate(number));
        }

        return sb.isEmpty() ? this.defaultTranslator.translate(number) : sb.toString();
    }
}
