package subway.domain;

import static subway.ErrorMessage.*;

import subway.code.RouteCode;

public class Section {
    private int distance;
    private int time;
    // 시작역, 끝역 안넣어도 될 거 같음. 어차피 Line에서 전부 조회할 수 있음.
    public Section(int distance, int time) {
        this.distance = distance;
        this.time = time;
    }

    public int getAccordingToStandard(RouteCode routeCode) {
        if (routeCode == RouteCode.MIN_DISTANCE) {
            return distance;
        }
        if (routeCode == RouteCode.MIN_TIME) {
            return time;
        }
        throw new IllegalArgumentException(NO_CODE_ERROR);
    }
}
