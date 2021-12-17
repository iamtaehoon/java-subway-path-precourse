package subway;

import java.util.Scanner;

import subway.controller.PathCheckController;

public class Application {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        PathCheckController pathCheckController = new PathCheckController(scanner);
        pathCheckController.run();
    }
}
