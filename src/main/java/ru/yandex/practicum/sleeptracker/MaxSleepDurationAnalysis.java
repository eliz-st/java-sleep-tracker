package ru.yandex.practicum.sleeptracker;
import java.time.Duration;
import java.util.List;
import java.util.function.Function;

public class MaxSleepDurationAnalysis implements Function<List<SleepingSession>, SleepAnalysisResult> {

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sessions) {
        long maxDuration = sessions.stream()
                .map(session -> Duration.between(session.getStart(), session.getEnd()).toMinutes())
                .max(Long::compareTo)
                .orElse(0L);

        return new SleepAnalysisResult(
                "Максимальная продолжительность сна, мин",
                maxDuration);
    }
}