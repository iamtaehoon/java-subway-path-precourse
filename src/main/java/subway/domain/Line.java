package subway.domain;

import java.util.ArrayList;

public class Line {
    private String name;
    private ArrayList<Station> stations = new ArrayList<>();
    private ArrayList<Section> sections = new ArrayList<>();

    public Line(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addFirstStation(String stationName) {
        stations.add(new Station(stationName));
    }

    public void addStation(Section section, String stationName) {
        stations.add(new Station(stationName));
        sections.add(section);
    }
}
