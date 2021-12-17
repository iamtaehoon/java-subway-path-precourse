package subway.code;

import static subway.ErrorMessage.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.WeightedMultigraph;

import subway.domain.ConnectInfo;
import subway.domain.Line;
import subway.domain.Section;
import subway.repository.LineRepository;
import subway.repository.StationRepository;

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

    public DijkstraShortestPath makeDijkstra() {
        WeightedMultigraph<String, DefaultWeightedEdge> graph
            = new WeightedMultigraph(DefaultWeightedEdge.class);
        StationRepository.stations().stream().forEach(station -> graph.addVertex(station.getName()));

        // graph.setEdgeWeight(graph.addEdge("이름1","이름2"),0); //Distance, Weight는 코드를 보고 결정할거임.
        LineRepository.lines();
        for (Line line : LineRepository.lines()) {
            for (ConnectInfo connectInfo : line.getConnectInfos()) {
                String startStationName = connectInfo.getStartStationName();
                String arriveStationName = connectInfo.getArriveStationName();
                Section section = connectInfo.getSection();
                graph.setEdgeWeight(graph.addEdge(startStationName,arriveStationName), section.getAccordingToStandard(this));
            }
        }
        return new DijkstraShortestPath(graph);
    }

}
