package subway.service;

import subway.repository.LineRepository;
import subway.repository.StationRepository;

public class PathCheckService {
    LineRepository lineRepository = new LineRepository();
    StationRepository stationRepository = new StationRepository();
}
