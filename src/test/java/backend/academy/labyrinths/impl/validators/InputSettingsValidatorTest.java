package backend.academy.labyrinths.impl.validators;

import backend.academy.labyrinths.interfaces.validators.InputDataValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

public class InputSettingsValidatorTest {

    private final InputDataValidator validator = new InputSettingsValidator();

    static Stream<Arguments> validNumbersArguments() {
        return Stream.of(
            Arguments.of("12", 15),
            Arguments.of("1", 2),
            Arguments.of("12345", 1000000),
            Arguments.of(String.valueOf(Integer.MAX_VALUE), Integer.MAX_VALUE),
            Arguments.of(String.valueOf(Integer.MIN_VALUE), Integer.MIN_VALUE)
            );
    }

    static Stream<Arguments> invalidNumbersArguments() {
        return Stream.of(
            Arguments.of("12", 1),
            Arguments.of("1 ", 2),
            Arguments.of("12345few", 1000000),
            Arguments.of("", Integer.MAX_VALUE),
            Arguments.of("Hello, world!", 1)
        );
    }

    @ParameterizedTest
    @MethodSource("validNumbersArguments")
    public void parametrizedCheckValidNumbersSuccess(String request, int maxValue) {
        Assertions.assertTrue(validator.isValidNumber(request, maxValue));
    }

    @ParameterizedTest
    @MethodSource("invalidNumbersArguments")
    public void parametrizedCheckInvalidNumbersDenied(String request, int maxValue) {
        Assertions.assertFalse(validator.isValidNumber(request, maxValue));
    }
}
