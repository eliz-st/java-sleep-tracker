package ru.yandex.practicum.sleeptracker;

import java.time.LocalTime;
import java.util.List;
import java.util.function.Function;

public class ChronotypeAnalysis
        implements Function<List<SleepingSession>, SleepAnalysisResult> {

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sessions) {

        List<SleepingSession> nightSessions = sessions.stream()
                .filter(session -> session != null
                        && session.getStart() != null
                        && session.getEnd() != null)
                .filter(this::isNightSession)
                .toList();

        List<Chronotype> chronotypes = nightSessions.stream()
                .map(this::classifyNightSession)
                .toList();

        long owlCount = chronotypes.stream()
                .filter(type -> type == Chronotype.OWL)
                .count();

        long larkCount = chronotypes.stream()
                .filter(type -> type == Chronotype.LARK)
                .count();

        long pigeonCount = chronotypes.stream()
                .filter(type -> type == Chronotype.PIGEON)
                .count();

        Chronotype resultType;

        if (owlCount > larkCount && owlCount > pigeonCount) {
            resultType = Chronotype.OWL;
        } else if (larkCount > owlCount && larkCount > pigeonCount) {
            resultType = Chronotype.LARK;
        } else {
            resultType = Chronotype.PIGEON;
        }

        return new SleepAnalysisResult(
                "Хронотип пользователя",
                resultType);
    }

    private Chronotype classifyNightSession(SleepingSession session) {

        LocalTime startTime = session.getStart().toLocalTime();
        LocalTime endTime = session.getEnd().toLocalTime();

        if (startTime.isAfter(LocalTime.of(23, 0))
                && endTime.isAfter(LocalTime.of(9, 0))) {
            return Chronotype.OWL;
        }

        if (startTime.isBefore(LocalTime.of(22, 0))
                && endTime.isBefore(LocalTime.of(7, 0))) {
            return Chronotype.LARK;
        }

        return Chronotype.PIGEON;
    }

    private boolean isNightSession(SleepingSession session) {

        LocalTime startTime = session.getStart().toLocalTime();
        LocalTime endTime = session.getEnd().toLocalTime();

        boolean crossesMidnight =
                !session.getStart().toLocalDate()
                        .equals(session.getEnd().toLocalDate());

        boolean startsAtNight =
                startTime.isBefore(LocalTime.of(6, 0));

        boolean endsAtNight =
                endTime.isBefore(LocalTime.of(6, 0));

        return crossesMidnight || startsAtNight || endsAtNight;
    }
}