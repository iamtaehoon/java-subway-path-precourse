package subway.view;

import java.util.List;

import subway.domain.Result;

public class OutputView {
    public static void showResult(Result result) {
        System.out.println("[INFO] ---");
        System.out.println("[INFO] 총 거리: " + result.getDistanceSum() +"km");
        System.out.println("[INFO] 총 소요 시간: " + result.getTimeSum() + "분");
        System.out.println("[INFO] ---");
        showStationsName(result.getStationsNameInShortestPath());
    }

    private static void showStationsName(List<String> stationsName) {
        for (String stationName : stationsName) {
            System.out.println("[INFO] " + stationName);
        }
        System.out.println();
    }
}
