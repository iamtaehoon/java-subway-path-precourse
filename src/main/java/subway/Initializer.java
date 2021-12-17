package subway;

import java.util.ArrayList;
import java.util.Arrays;

import subway.domain.Line;
import subway.domain.Section;
import subway.domain.Station;
import subway.repository.LineRepository;
import subway.repository.StationRepository;

public class Initializer { // TODO 굳이 예외처리 기능 넣지 않음. 다 하고 시간 남으면 초기화 단계에서도 예외 기능 만들기.
    public static void init() {
        putStations();
        putLines();
        putSectionOnLine();
    }

    private static void putSectionOnLine() {
        Line line = LineRepository.find("2호선");
        line.addFirstStation("교대역");
        line.addStation(new Section(2,3), "강남역");
        line.addStation(new Section(2, 3), "역삼역");

        line = LineRepository.find("3호선");
        line.addFirstStation("교대역");
        line.addStation(new Section(3,2), "남부터미널역");
        line.addStation(new Section(6,5), "양재역");
        line.addStation(new Section(1,1), "매봉역");

        line = LineRepository.find("신분당선");
        line.addFirstStation("강남역");
        line.addStation(new Section(2,8), "양재역");
        line.addStation(new Section(10,3), "양재시민의숲역");
    }

    private static void putLines() {
        ArrayList<String> linesName = new ArrayList<>(
            Arrays.asList("2호선", "3호선", "신분당선"));
        linesName.stream().forEach(lineName -> LineRepository.addLine(new Line(lineName)));
    }

    private static void putStations() {
        ArrayList<String> stationNames = new ArrayList<>(
            Arrays.asList("교대역", "강남역", "역삼역", "남부터미널역", "양재역", "양재시민의숲역", "매봉역"));
        stationNames.stream().forEach(stationName -> StationRepository.addStation(new Station(stationName)));
    }
}
