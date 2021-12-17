package subway.code;

import static subway.ErrorMessage.*;

import java.util.Arrays;

public enum MainCode {
    FIND_PATH("1"), QUIT("Q");

    private String code;

    MainCode(String code) {
        this.code = code;
    }

    public static MainCode find(String input) {
        return Arrays.stream(MainCode.values())
            .filter(code -> input.equals(code.getCode()))
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException(NO_CODE_ERROR));
    }

    private String getCode() {
        return code;
    }
}
