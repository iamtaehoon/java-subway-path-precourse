package subway.service;

import static subway.ErrorMessage.*;

import java.util.LinkedList;
import java.util.List;

import org.jgrapht.alg.shortestpath.DijkstraShortestPath;

import subway.code.RouteCode;
import subway.domain.Dijkstra;
import subway.domain.Result;
import subway.domain.Station;
import subway.repository.StationRepository;

public class PathCheckService {
    DijkstraShortestPath distanceShortestPath;
    DijkstraShortestPath timeShortestPath;
    List<String> shortestPathStationNames = new LinkedList<>();

    public Station putStartStation(String startStationName) {
        return StationRepository.find(startStationName);
    }

    public Station putEndStation(Station startStation, String endStationName) {
        if (startStation.getName().equals(endStationName)) {
            throw new IllegalArgumentException(DUPLICATE_ERROR);
        }
        return StationRepository.find(endStationName);
    }

    public void makeDijkstra() {
        distanceShortestPath = Dijkstra.makeDijkstra(RouteCode.MIN_DISTANCE);
        timeShortestPath = Dijkstra.makeDijkstra(RouteCode.MIN_TIME);
    }
    public List<String> findShortestPath(Station startStation, Station endStation, RouteCode routeCode) {
        if (routeCode == RouteCode.MIN_DISTANCE) {
            shortestPathStationNames.addAll(
                distanceShortestPath.getPath(startStation.getName(), endStation.getName()).getVertexList());
        }
        if (routeCode == RouteCode.MIN_TIME) {
            shortestPathStationNames.addAll(
                timeShortestPath.getPath(startStation.getName(), endStation.getName()).getVertexList());
        }
        return shortestPathStationNames;
    }

    public Result calculate(List<String> stationsNameInShortestPath) { //calculate 이름 바꾸기.
        int timeSum = 0;
        int distanceSum = 0;
        String startStationName = shortestPathStationNames.get(0);
        for (String stationName : shortestPathStationNames) {
            if (stationName.equals(startStationName)) {
                continue;
            }
            timeSum += Dijkstra.timeGraph.getEdgeWeight(Dijkstra.timeGraph.getEdge(startStationName,stationName));
            distanceSum += Dijkstra.distanceGraph.getEdgeWeight(Dijkstra.distanceGraph.getEdge(startStationName,stationName));
            startStationName = stationName;
        }
        return new Result(timeSum, distanceSum, stationsNameInShortestPath);
    }
}
