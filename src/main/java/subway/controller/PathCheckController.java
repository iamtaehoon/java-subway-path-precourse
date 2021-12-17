package subway.controller;

import java.util.Scanner;

import org.jgrapht.alg.shortestpath.DijkstraShortestPath;

import subway.Initializer;
import subway.code.MainCode;
import subway.code.RouteCode;
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

    }

    private DijkstraShortestPath determineRouteCriteria() {
        RouteCode routeCode = enterRouteCriteria();
        if (routeCode == RouteCode.BACK) {
            System.out.println("종료 로직 만들어야 함.");
        }
        return routeCode.makeDijkstra();
    }

    private RouteCode enterRouteCriteria() {
        RouteCode routeCode = null;
        try {
            routeCode = RouteCode.find(inputView.enterRouteCriteria());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            enterRouteCriteria();
        }
        return routeCode;
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
