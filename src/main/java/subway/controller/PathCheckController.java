package subway.controller;

import java.util.List;
import java.util.Scanner;

import subway.Initializer;
import subway.domain.Line;
import subway.repository.LineRepository;
import subway.service.PathCheckService;
import subway.view.InputView;

public class PathCheckController {
    private PathCheckService pathCheckService = new PathCheckService();
    private InputView inputView;

    public PathCheckController(Scanner scanner) {
        InputView inputView = new InputView(scanner);
    }

    public void run() {
        Initializer.init();
        List<Line> lines = LineRepository.lines();
        for (Line line : lines) {
            System.out.println(line.getName());
            line.showAllSection();
        }
    }
}
