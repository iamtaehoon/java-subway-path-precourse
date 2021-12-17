package subway.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import subway.Initializer;
import subway.code.MainCode;
import subway.domain.Line;
import subway.repository.LineRepository;
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
