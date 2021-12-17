package subway.view;

import java.util.List;

public class OutputView {
    public static void showResult(int distanceSum, int timeSum, List<String> stationsName) {
        System.out.println("[INFO] ---");
        System.out.println("[INFO] 총 거리: " + distanceSum);
        System.out.println("[INFO] 총 소요 시간: " + timeSum);
        System.out.println("[INFO] ---");
        showStationsName(stationsName);
    }

    private static void showStationsName(List<String> stationsName) {
        for (String stationName : stationsName) {
            System.out.println("[INFO] " + stationName);
        }
    }
}
