package subway.controller;

import java.util.List;
import java.util.Scanner;

import subway.Initializer;
import subway.code.MainCode;
import subway.code.RouteCode;
import subway.domain.Result;
import subway.domain.Station;
import subway.service.PathCheckService;
import subway.view.InputView;
import subway.view.OutputView;

public class PathCheckController {
    private MainCode mainCode;
    private PathCheckService pathCheckService = new PathCheckService();
    private InputView inputView;

    public PathCheckController(Scanner scanner) {
        Initializer.init();
        inputView = new InputView(scanner);
    }

    public void run() {
        while (mainCode != MainCode.QUIT) {
            determineMainFunction();
            RouteCode routeCode = determineRouteCriteria();
            if (routeCode == RouteCode.BACK) {
                run();
                return;
            }
            List<String> stationsNameInShortestPath = findShortestPath(routeCode);
            Result result = pathCheckService.calculateResult(stationsNameInShortestPath);
            OutputView.showResult(result);
            stationsNameInShortestPath.clear();
        }
    }

    private List<String> findShortestPath(RouteCode routeCode) {
        Station startStation = enterStartStation();
        Station endStation = enterEndStation(startStation);
        List<String> stationsNameInShortestPath = pathCheckService.findShortestPath(startStation, endStation, routeCode);
        return stationsNameInShortestPath;
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

    private void determineMainFunction() {
        try {
            mainCode = MainCode.find(inputView.enterMainFunction());
            if (mainCode == MainCode.QUIT) {
                System.exit(0);
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            determineMainFunction();
        }
    }
}
