package subway.repository;

import static subway.ErrorMessage.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import subway.domain.Line;

public class LineRepository {
    private static final List<Line> lines = new ArrayList<>();

    public static List<Line> lines() {
        return Collections.unmodifiableList(lines);
    }

    public static void addLine(Line line) {
        lines.add(line);
    }

    public static boolean deleteLineByName(String name) {
        return lines.removeIf(line -> Objects.equals(line.getName(), name));
    }

    public static void deleteAll() {
        lines.clear();
    }

    public static Line find(String name) {
        return lines.stream()
            .filter(line -> line.getName().equals(name))
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException(NO_OBJECT_ERROR));
    }
}
