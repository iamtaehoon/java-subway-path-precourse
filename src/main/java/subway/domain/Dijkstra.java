package subway.domain;

import static subway.ErrorMessage.*;

import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.WeightedMultigraph;

import subway.code.RouteCode;
import subway.repository.LineRepository;
import subway.repository.StationRepository;

public class Dijkstra {
    public static WeightedMultigraph<String, DefaultWeightedEdge> timeGraph;
    public static WeightedMultigraph<String, DefaultWeightedEdge> distanceGraph;

    public static DijkstraShortestPath makeDijkstra(RouteCode routeCode) {
        WeightedMultigraph<String, DefaultWeightedEdge> graph;
        if (routeCode == RouteCode.MIN_TIME) {
            graph = makeUseThis(routeCode);
            return new DijkstraShortestPath(graph);
        }
        if (routeCode == RouteCode.MIN_DISTANCE) {
            graph = makeUseThis(routeCode);
            return new DijkstraShortestPath(graph);
        }
        throw new IllegalArgumentException(LOGIC_ERROR);
    }

    private static WeightedMultigraph<String, DefaultWeightedEdge> makeUseThis(RouteCode routeCode) {
        WeightedMultigraph<String, DefaultWeightedEdge> graph = new WeightedMultigraph(DefaultWeightedEdge.class);
        StationRepository.stations().stream().forEach(station -> graph.addVertex(station.getName()));
        LineRepository.lines();
        for (Line line : LineRepository.lines()) {
            for (ConnectInfo connectInfo : line.getConnectInfos()) {
                String startStationName = connectInfo.getStartStationName();
                String arriveStationName = connectInfo.getArriveStationName();
                Section section = connectInfo.getSection();
                graph.setEdgeWeight(graph.addEdge(startStationName,arriveStationName), section.getValue(routeCode));
            }
        }
        if (routeCode == RouteCode.MIN_DISTANCE) {
            distanceGraph = graph;
        }
        if (routeCode == RouteCode.MIN_TIME) {
            timeGraph = graph;
        }
        return graph;
    }
}
