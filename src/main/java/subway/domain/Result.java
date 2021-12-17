package subway.domain;

import java.util.List;

public class Result {
    private int timeSum;
    private int distanceSum;
    private List<String> stationsNameInShortestPath;

    public Result(int timeSum, int distanceSum, List<String> stationsNameInShortestPath) {
        this.timeSum = timeSum;
        this.distanceSum = distanceSum;
        this.stationsNameInShortestPath = stationsNameInShortestPath;
    }

    public int getTimeSum() {
        return timeSum;
    }

    public int getDistanceSum() {
        return distanceSum;
    }

    public List<String> getStationsNameInShortestPath() {
        return stationsNameInShortestPath;
    }
}
