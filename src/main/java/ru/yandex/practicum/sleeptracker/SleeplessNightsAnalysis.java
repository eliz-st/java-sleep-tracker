package ru.yandex.practicum.sleeptracker;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

public class SleeplessNightsAnalysis
        implements Function<List<SleepingSession>, SleepAnalysisResult> {

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sessions) {

        if (sessions.isEmpty()) {
            return new SleepAnalysisResult(
                    "Количество бессонных ночей",
                    0L);
        }

        SleepingSession firstSession = sessions.get(0);
        LocalDateTime firstStart = firstSession.getStart();
        LocalDate firstNightDate;

        if (firstStart.toLocalTime().isAfter(LocalTime.NOON)) {
            firstNightDate = firstStart.toLocalDate().plusDays(1);
        } else {
            firstNightDate = firstStart.toLocalDate();
        }

        SleepingSession lastSession =
                sessions.get(sessions.size() - 1);
        LocalDateTime lastEnd = lastSession.getEnd();
        LocalDate lastNightDate =
                lastEnd.toLocalDate();

        long sleeplessNights = Stream.iterate(
                        firstNightDate,
                        date -> !date.isAfter(lastNightDate),
                        date -> date.plusDays(1))
                .filter(nightDate ->
                        sessions.stream()
                                .noneMatch(session -> sessionIntersectsNight(session, nightDate)))
                .count();

        return new SleepAnalysisResult(
                "Количество бессонных ночей",
                sleeplessNights);
    }

    private boolean sessionIntersectsNight(
            SleepingSession session,
            LocalDate nightDate) {

        LocalDateTime nightStart =
                nightDate.atStartOfDay();
        LocalDateTime nightEnd =
                nightDate.atTime(6, 0);

        return session.getStart().isBefore(nightEnd)
                && session.getEnd().isAfter(nightStart);
    }
}