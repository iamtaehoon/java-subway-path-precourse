package subway;

import java.util.Scanner;

import subway.controller.PathCheckController;

public class Application {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        // TODO: 프로그램 구현
        PathCheckController pathCheckController = new PathCheckController(scanner);
        pathCheckController.run();
    }
}
