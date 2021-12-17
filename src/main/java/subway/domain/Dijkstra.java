package subway.domain;

import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.WeightedMultigraph;

import subway.repository.LineRepository;
import subway.repository.StationRepository;

public class Dijkstra {
    public static WeightedMultigraph<String, DefaultWeightedEdge> timeGraph;
    public static WeightedMultigraph<String, DefaultWeightedEdge> distanceGraph;

    public static DijkstraShortestPath makeTimeDijkstra() { // 두 다익스트라 파라미터만 넘겨주면 합칠 수 있음
        timeGraph = new WeightedMultigraph(DefaultWeightedEdge.class);
        StationRepository.stations().stream().forEach(station -> timeGraph.addVertex(station.getName()));
        LineRepository.lines();
        for (Line line : LineRepository.lines()) {
            for (ConnectInfo connectInfo : line.getConnectInfos()) {
                String startStationName = connectInfo.getStartStationName();
                String arriveStationName = connectInfo.getArriveStationName();
                Section section = connectInfo.getSection();
                timeGraph.setEdgeWeight(timeGraph.addEdge(startStationName,arriveStationName), section.getTime());
            }
        }
        return new DijkstraShortestPath(timeGraph);
    }

    public static DijkstraShortestPath makeDistanceDijkstra() {
        distanceGraph = new WeightedMultigraph(DefaultWeightedEdge.class);
        StationRepository.stations().stream().forEach(station -> distanceGraph.addVertex(station.getName()));
        LineRepository.lines();
        for (Line line : LineRepository.lines()) {
            for (ConnectInfo connectInfo : line.getConnectInfos()) {
                String startStationName = connectInfo.getStartStationName();
                String arriveStationName = connectInfo.getArriveStationName();
                Section section = connectInfo.getSection();
                distanceGraph.setEdgeWeight(distanceGraph.addEdge(startStationName,arriveStationName), section.getDistance());
            }
        }
        return new DijkstraShortestPath(distanceGraph);
    }


}
