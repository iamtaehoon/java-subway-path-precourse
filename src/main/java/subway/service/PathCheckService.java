package subway.service;

import static subway.ErrorMessage.*;

import subway.domain.Station;
import subway.repository.StationRepository;

public class PathCheckService {
    public Station putStartStation(String startStationName) {
        return StationRepository.find(startStationName);
    }

    public Station putEndStation(Station startStation, String endStationName) {
        if (startStation.getName().equals(endStationName)) {
            throw new IllegalArgumentException(DUPLICATE_ERROR);
        }
        return StationRepository.find(endStationName);
    }
}
