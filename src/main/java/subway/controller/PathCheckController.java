package subway.controller;

import java.util.Scanner;

import org.jgrapht.alg.shortestpath.DijkstraShortestPath;

import subway.Initializer;
import subway.code.MainCode;
import subway.code.RouteCode;
import subway.domain.Station;
import subway.repository.StationRepository;
import subway.service.PathCheckService;
import subway.view.InputView;

public class PathCheckController {
    MainCode mainCode;
    private PathCheckService pathCheckService = new PathCheckService();
    private InputView inputView;

    public PathCheckController(Scanner scanner) {
        Initializer.init();
        inputView = new InputView(scanner);
    }

    public void run() {
        enterMainFunction();
        if (mainCode == MainCode.QUIT) {
            System.out.println("종료 로직 만들어야 함.");
        }
        DijkstraShortestPath dijkstraShortestPath = determineRouteCriteria();
        Station startStation = enterStartStation();
        Station endStation = enterEndStation(startStation);

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

    private DijkstraShortestPath determineRouteCriteria() {
        RouteCode routeCode = enterRouteCriteria();
        if (routeCode == RouteCode.BACK) {
            System.out.println("종료 로직 만들어야 함.");
        }
        return routeCode.makeDijkstra();
    }

    private RouteCode enterRouteCriteria() {
        try {
            return RouteCode.find(inputView.enterRouteCriteria());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return enterRouteCriteria();
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
