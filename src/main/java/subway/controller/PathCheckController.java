package subway.controller;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;

import subway.Initializer;
import subway.code.MainCode;
import subway.code.RouteCode;
import subway.domain.Dijkstra;
import subway.domain.Station;
import subway.repository.StationRepository;
import subway.service.PathCheckService;
import subway.view.InputView;
import subway.view.OutputView;

public class PathCheckController {
    MainCode mainCode;
    private PathCheckService pathCheckService = new PathCheckService();
    private InputView inputView;
    private int distanceSum = 0;
    private int timeSum = 0;
    List<String> shortestPathStationNames = new LinkedList<>();

    public PathCheckController(Scanner scanner) {
        Initializer.init();
        inputView = new InputView(scanner);
    }

    public void run() {
        enterMainFunction();
        if (mainCode == MainCode.QUIT) {
            System.out.println("종료 로직 만들어야 함.");
        }
        RouteCode routeCode = determineRouteCriteria();
        //routeCode가 back이면 다시 run 처음부터 실행.
        DijkstraShortestPath distanceShortestPath = Dijkstra.makeDistanceDijkstra();
        DijkstraShortestPath timeShortestPath = Dijkstra.makeTimeDijkstra();

        Station startStation = enterStartStation();
        Station endStation = enterEndStation(startStation);

        if (routeCode == RouteCode.MIN_DISTANCE) {
            shortestPathStationNames.addAll(distanceShortestPath.getPath(startStation.getName(), endStation.getName()).getVertexList());
            String startStationName = shortestPathStationNames.get(0);
            for (String stationName : shortestPathStationNames) {
                if (stationName.equals(startStationName)) {
                    continue;
                }
                timeSum += Dijkstra.timeGraph.getEdgeWeight(Dijkstra.timeGraph.getEdge(startStationName,stationName));
                distanceSum += Dijkstra.distanceGraph.getEdgeWeight(Dijkstra.distanceGraph.getEdge(startStationName,stationName));
                startStationName = stationName;
            }
        }
        if (routeCode == RouteCode.MIN_TIME) {
            shortestPathStationNames.addAll(timeShortestPath.getPath(startStation.getName(), endStation.getName()).getVertexList());
            String startStationName = shortestPathStationNames.get(0);
            for (String stationName : shortestPathStationNames) {
                System.out.println(stationName);
                if (stationName.equals(startStationName)) {
                    continue;
                }
                timeSum += Dijkstra.timeGraph.getEdgeWeight(Dijkstra.timeGraph.getEdge(startStationName,stationName));
                distanceSum += Dijkstra.distanceGraph.getEdgeWeight(Dijkstra.distanceGraph.getEdge(startStationName,stationName));
                startStationName = stationName;
            }

        }
        OutputView.showResult(distanceSum,timeSum,shortestPathStationNames);

    }

    private Station enterEndStation(Station startStation) {
        try {
            return pathCheckService.putEndStation(startStation, inputView.enterEndStation());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return enterEndStation(startStation);
        }
    }

    private Station enterStartStation() {
        try {
            return pathCheckService.putStartStation(inputView.enterStartStation());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return enterStartStation();
        }
    }

    private RouteCode determineRouteCriteria() {
        try {
            return RouteCode.find(inputView.enterRouteCriteria());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return determineRouteCriteria();
        }
    }

    private void enterMainFunction() {
        try {
            mainCode = MainCode.find(inputView.enterMainFunction());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            enterMainFunction();
        }
    }
}
