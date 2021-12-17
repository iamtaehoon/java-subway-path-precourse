package subway.code;

import static subway.ErrorMessage.*;

import java.util.Arrays;

public enum RouteCode {
    MIN_DISTANCE("1"), MIN_TIME("2"), BACK("B");

    private String code;

    RouteCode(String code) {
        this.code = code;
    }

    private String getCode() {
        return code;
    }

    public static RouteCode find(String input) {
        return Arrays.stream(RouteCode.values())
            .filter(code -> input.equals(code.getCode()))
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException(NO_CODE_ERROR));

    }
}
