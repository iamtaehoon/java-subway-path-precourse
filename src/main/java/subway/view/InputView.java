package subway.view;

import java.util.Scanner;

public class InputView {
    private Scanner scanner;

    public InputView(Scanner scanner) {
        this.scanner = scanner;
    }

    public String enterMainFunction() {
        System.out.println("## 메인 화면\n"
            + "1. 경로 조회\n"
            + "Q. 종료\n");
        System.out.println("## 원하는 기능을 선택하세요.");
        return scanner.nextLine();
    }

    public String enterStartStation() {
        System.out.println("## 출발역을 입력하세요.");
        return scanner.nextLine();
    }

    public String enterEndStation() {
        System.out.println("## 도착역을 입력하세요.");
        return scanner.nextLine();
    }
}
