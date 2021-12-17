package subway.domain;

import static subway.ErrorMessage.*;

import subway.code.RouteCode;

public class Section {
    private int distance;
    private int time;
    public Section(int distance, int time) {
        this.distance = distance;
        this.time = time;
    }

    public double getValue(RouteCode routeCode) {
        if (routeCode == RouteCode.MIN_DISTANCE) {
            return distance;
        }
        if (routeCode == RouteCode.MIN_TIME) {
            return time;
        }
        throw new IllegalArgumentException(LOGIC_ERROR);
    }
}
