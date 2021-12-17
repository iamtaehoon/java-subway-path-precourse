package subway.domain;

public class ConnectInfo {
    private final Section section;
    private final String startStationName;
    private final String arriveStationName;

    public ConnectInfo(Section section, String startStationName, String arriveStationName) {
        this.section = section;
        this.startStationName = startStationName;
        this.arriveStationName = arriveStationName;
    }

    public Section getSection() {
        return section;
    }

    public String getStartStationName() {
        return startStationName;
    }

    public String getArriveStationName() {
        return arriveStationName;
    }
}
