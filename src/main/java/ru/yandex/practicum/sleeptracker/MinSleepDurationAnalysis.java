package ru.yandex.practicum.sleeptracker;
import java.time.Duration;
import java.util.List;
import java.util.function.Function;

public class MinSleepDurationAnalysis implements Function<List<SleepingSession>, SleepAnalysisResult> {

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sessions) {
        long minDuration = sessions.stream()
                .map(session -> Duration.between(session.getStart(), session.getEnd()).toMinutes())
                .min(Long::compareTo)
                .orElse(0L);

        return new SleepAnalysisResult(
                "Минимальная продолжительность сна, мин",
                minDuration);
    }
}
