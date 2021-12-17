package subway.controller;

import java.util.Scanner;

import subway.service.PathCheckService;
import subway.view.InputView;

public class PathCheckController {
    private PathCheckService pathCheckService = new PathCheckService();
    private InputView inputView;

    public PathCheckController(Scanner scanner) {
        InputView inputView = new InputView(scanner);
    }

    public void run() {
    }
}
