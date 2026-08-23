package ru.yandex.practicum.sleeptracker;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.function.Function;

public class SleepTrackerApp {

    private static final List<Function<List<SleepingSession>, SleepAnalysisResult>> ANALYSES =
            List.of(
                    new SessionCountAnalysis(),
                    new MinSleepDurationAnalysis(),
                    new MaxSleepDurationAnalysis(),
                    new AverageSleepDurationAnalysis(),
                    new BadSleepQualityAnalysis(),
                    new SleeplessNightsAnalysis(),
                    new ChronotypeAnalysis());

    public static void main(String[] args) throws IOException {
        Path filePath = Path.of(args[0]);
        List<String> lines = Files.readAllLines(filePath);
        List<SleepingSession> sessions = lines.stream()
                .map(SleepTrackerApp::parseSession)
                .toList();

        ANALYSES.forEach(analysis -> {
            SleepAnalysisResult result = analysis.apply(sessions);

            System.out.println(
                    result.getDescription() + ": " + result.getValue());
        });
    }

    private static SleepingSession parseSession(String line) {
        String[] parts = line.split(";");

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");
        LocalDateTime start =
                LocalDateTime.parse(parts[0], formatter);
        LocalDateTime end =
                LocalDateTime.parse(parts[1], formatter);
        SleepQuality quality =
                SleepQuality.valueOf(parts[2]);

        return new SleepingSession(start, end, quality);
    }
}