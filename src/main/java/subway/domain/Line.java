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

    public void showAllSection() {
        System.out.println(stations.get(0).getName());
        for (Section section : sections) {
            System.out.println(section);
            System.out.println(stations.get(sections.indexOf(section)+1).getName());
        }
    }

    // 추가 기능 구현
}
