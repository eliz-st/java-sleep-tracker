package ru.yandex.practicum.sleeptracker;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDateTime;
import java.util.List;

public class SleepTrackerAppTest {

    @Test
    void shouldCountSleepingSessions() {
        SleepingSession session1 = new SleepingSession(
                LocalDateTime.of(2025, 10, 1, 23, 15),
                LocalDateTime.of(2025, 10, 2, 7, 30),
                SleepQuality.GOOD);

        SleepingSession session2 = new SleepingSession(
                LocalDateTime.of(2025, 10, 2, 23, 50),
                LocalDateTime.of(2025, 10, 3, 6, 40),
                SleepQuality.NORMAL);

        SleepingSession session3 = new SleepingSession(
                LocalDateTime.of(2025, 10, 3, 23, 40),
                LocalDateTime.of(2025, 10, 4, 8, 0),
                SleepQuality.BAD);
        List<SleepingSession> sessions = List.of(
                session1,
                session2,
                session3);

        SessionCountAnalysis analysis = new SessionCountAnalysis();
        SleepAnalysisResult result = analysis.apply(sessions);
        assertEquals(3, result.getValue());

    }

    @Test
    void shouldReturnZeroForEmptyList() {

        List<SleepingSession> sessions = List.of();
        SessionCountAnalysis analysis = new SessionCountAnalysis();
        SleepAnalysisResult result = analysis.apply(sessions);
        assertEquals(0, result.getValue());

    }

    @Test
    void shouldFindMinimumSleepDuration() {
        SleepingSession session1 = new SleepingSession(
                LocalDateTime.of(2025, 10, 1, 23, 0),
                LocalDateTime.of(2025, 10, 2, 7, 0),
                SleepQuality.GOOD);

        SleepingSession session2 = new SleepingSession(
                LocalDateTime.of(2025, 10, 2, 23, 0),
                LocalDateTime.of(2025, 10, 3, 5, 0),
                SleepQuality.NORMAL);

        SleepingSession session3 = new SleepingSession(
                LocalDateTime.of(2025, 10, 3, 13, 30),
                LocalDateTime.of(2025, 10, 3, 14, 15),
                SleepQuality.BAD);

        List<SleepingSession> sessions = List.of(
                session1,
                session2,
                session3);
        MinSleepDurationAnalysis analysis =
                new MinSleepDurationAnalysis();
        SleepAnalysisResult result =
                analysis.apply(sessions);
        assertEquals(45L, result.getValue());
    }

    @Test
    void shouldReturnZeroMinimumForEmptyList() {
        List<SleepingSession> sessions = List.of();
        MinSleepDurationAnalysis analysis = new MinSleepDurationAnalysis();
        SleepAnalysisResult result = analysis.apply(sessions);
        assertEquals(0L, result.getValue());

    }

    @Test
    void shouldFindMaximumSleepDuration() {
        SleepingSession session1 = new SleepingSession(
                LocalDateTime.of(2025, 10, 1, 23, 0),
                LocalDateTime.of(2025, 10, 2, 7, 0),
                SleepQuality.GOOD);

        SleepingSession session2 = new SleepingSession(
                LocalDateTime.of(2025, 10, 2, 23, 0),
                LocalDateTime.of(2025, 10, 3, 5, 0),
                SleepQuality.NORMAL);

        SleepingSession session3 = new SleepingSession(
                LocalDateTime.of(2025, 10, 3, 23, 40),
                LocalDateTime.of(2025, 10, 4, 8, 0),
                SleepQuality.BAD);

        List<SleepingSession> sessions = List.of(
                session1,
                session2,
                session3);

        MaxSleepDurationAnalysis analysis =
                new MaxSleepDurationAnalysis();
        SleepAnalysisResult result =
                analysis.apply(sessions);
        assertEquals(500L, result.getValue());
    }

    @Test
    void shouldReturnZeroMaximumForEmptyList() {
        List<SleepingSession> sessions = List.of();
        MaxSleepDurationAnalysis analysis =
                new MaxSleepDurationAnalysis();
        SleepAnalysisResult result =
                analysis.apply(sessions);
        assertEquals(0L, result.getValue());
    }

    @Test
    void shouldCalculateAverageSleepDuration() {
        SleepingSession session1 = new SleepingSession(
                LocalDateTime.of(2025, 10, 1, 22, 0),
                LocalDateTime.of(2025, 10, 1, 23, 0),
                SleepQuality.GOOD);

        SleepingSession session2 = new SleepingSession(
                LocalDateTime.of(2025, 10, 2, 22, 0),
                LocalDateTime.of(2025, 10, 3, 0, 0),
                SleepQuality.NORMAL);

        SleepingSession session3 = new SleepingSession(
                LocalDateTime.of(2025, 10, 3, 22, 0),
                LocalDateTime.of(2025, 10, 4, 1, 0),
                SleepQuality.BAD);

        List<SleepingSession> sessions = List.of(
                session1,
                session2,
                session3);

        AverageSleepDurationAnalysis analysis =
                new AverageSleepDurationAnalysis();
        SleepAnalysisResult result =
                analysis.apply(sessions);
        assertEquals(120.0, result.getValue());
    }

    @Test
    void shouldReturnZeroAverageForEmptyList() {
        List<SleepingSession> sessions = List.of();
        AverageSleepDurationAnalysis analysis =
                new AverageSleepDurationAnalysis();
        SleepAnalysisResult result =
                analysis.apply(sessions);
        assertEquals(0.0, result.getValue());
    }

    @Test
    void shouldCountBadSleepQualitySessions() {
        SleepingSession session1 = new SleepingSession(
                LocalDateTime.of(2025, 10, 1, 23, 0),
                LocalDateTime.of(2025, 10, 2, 7, 0),
                SleepQuality.BAD);

        SleepingSession session2 = new SleepingSession(
                LocalDateTime.of(2025, 10, 2, 23, 0),
                LocalDateTime.of(2025, 10, 3, 7, 0),
                SleepQuality.GOOD);

        SleepingSession session3 = new SleepingSession(
                LocalDateTime.of(2025, 10, 3, 23, 0),
                LocalDateTime.of(2025, 10, 4, 7, 0),
                SleepQuality.BAD);

        List<SleepingSession> sessions = List.of(
                session1,
                session2,
                session3);
        BadSleepQualityAnalysis analysis =
                new BadSleepQualityAnalysis();
        SleepAnalysisResult result =
                analysis.apply(sessions);
        assertEquals(2L, result.getValue());
    }

    @Test
    void shouldReturnZeroWhenNoBadSleepSessions() {
        SleepingSession session1 = new SleepingSession(
                LocalDateTime.of(2025, 10, 1, 23, 0),
                LocalDateTime.of(2025, 10, 2, 7, 0),
                SleepQuality.GOOD);

        SleepingSession session2 = new SleepingSession(
                LocalDateTime.of(2025, 10, 2, 23, 0),
                LocalDateTime.of(2025, 10, 3, 7, 0),
                SleepQuality.NORMAL);

        List<SleepingSession> sessions = List.of(
                session1,
                session2);
        BadSleepQualityAnalysis analysis =
                new BadSleepQualityAnalysis();
        SleepAnalysisResult result =
                analysis.apply(sessions);
        assertEquals(0L, result.getValue());
    }

    @Test
    void shouldReturnZeroWhenAllNightsHaveSleep() {
        SleepingSession session1 = new SleepingSession(
                LocalDateTime.of(2025, 10, 1, 23, 0),
                LocalDateTime.of(2025, 10, 2, 7, 0),
                SleepQuality.GOOD);

        SleepingSession session2 = new SleepingSession(
                LocalDateTime.of(2025, 10, 2, 23, 0),
                LocalDateTime.of(2025, 10, 3, 7, 0),
                SleepQuality.GOOD);

        List<SleepingSession> sessions = List.of(
                session1,
                session2);
        SleeplessNightsAnalysis analysis =
                new SleeplessNightsAnalysis();
        SleepAnalysisResult result =
                analysis.apply(sessions);
        assertEquals(0L, result.getValue());
    }

    @Test
    void shouldCountOneSleeplessNight() {
        SleepingSession session1 = new SleepingSession(
                LocalDateTime.of(2025, 10, 1, 23, 0),
                LocalDateTime.of(2025, 10, 2, 7, 0),
                SleepQuality.GOOD);

        SleepingSession session2 = new SleepingSession(
                LocalDateTime.of(2025, 10, 3, 23, 0),
                LocalDateTime.of(2025, 10, 4, 7, 0),
                SleepQuality.GOOD);

        List<SleepingSession> sessions = List.of(
                session1,
                session2);
        SleeplessNightsAnalysis analysis =
                new SleeplessNightsAnalysis();
        SleepAnalysisResult result =
                analysis.apply(sessions);
        assertEquals(1L, result.getValue());
    }

    @Test
    void shouldNotCountNightAsSleeplessWhenSleepEndsAtThree() {
        SleepingSession session = new SleepingSession(
                LocalDateTime.of(2025, 10, 1, 23, 0),
                LocalDateTime.of(2025, 10, 2, 3, 0),
                SleepQuality.GOOD);

        List<SleepingSession> sessions = List.of(session);
        SleeplessNightsAnalysis analysis =
                new SleeplessNightsAnalysis();
        SleepAnalysisResult result =
                analysis.apply(sessions);
        assertEquals(0L, result.getValue());
    }

    @Test
    void shouldNotCountNightAsSleeplessWhenSleepStartsAtTwo() {
        SleepingSession session = new SleepingSession(
                LocalDateTime.of(2025, 10, 2, 2, 0),
                LocalDateTime.of(2025, 10, 2, 7, 0),
                SleepQuality.GOOD);

        List<SleepingSession> sessions = List.of(session);
        SleeplessNightsAnalysis analysis =
                new SleeplessNightsAnalysis();
        SleepAnalysisResult result =
                analysis.apply(sessions);
        assertEquals(0L, result.getValue());
    }

    @Test
    void shouldDetectOwlChronotype() {
        SleepingSession session1 = new SleepingSession(
                LocalDateTime.of(2025, 10, 1, 23, 30),
                LocalDateTime.of(2025, 10, 2, 9, 30),
                SleepQuality.GOOD);

        SleepingSession session2 = new SleepingSession(
                LocalDateTime.of(2025, 10, 2, 23, 40),
                LocalDateTime.of(2025, 10, 3, 10, 0),
                SleepQuality.NORMAL);

        SleepingSession session3 = new SleepingSession(
                LocalDateTime.of(2025, 10, 3, 22, 30),
                LocalDateTime.of(2025, 10, 4, 8, 0),
                SleepQuality.GOOD);

        List<SleepingSession> sessions = List.of(
                session1,
                session2,
                session3);

        ChronotypeAnalysis analysis =
                new ChronotypeAnalysis();
        SleepAnalysisResult result =
                analysis.apply(sessions);
        assertEquals(Chronotype.OWL, result.getValue());
    }

    @Test
    void shouldDetectLarkChronotype() {
        SleepingSession session1 = new SleepingSession(
                LocalDateTime.of(2025, 10, 1, 21, 30),
                LocalDateTime.of(2025, 10, 2, 6, 30),
                SleepQuality.GOOD);

        SleepingSession session2 = new SleepingSession(
                LocalDateTime.of(2025, 10, 2, 21, 0),
                LocalDateTime.of(2025, 10, 3, 6, 0),
                SleepQuality.NORMAL);

        SleepingSession session3 = new SleepingSession(
                LocalDateTime.of(2025, 10, 3, 22, 30),
                LocalDateTime.of(2025, 10, 4, 8, 0),
                SleepQuality.GOOD);

        List<SleepingSession> sessions =
                List.of(session1, session2, session3);
        ChronotypeAnalysis analysis = new ChronotypeAnalysis();
        SleepAnalysisResult result = analysis.apply(sessions);
        assertEquals(Chronotype.LARK, result.getValue());
    }

    @Test
    void shouldDetectPigeonChronotype() {
        SleepingSession session1 = new SleepingSession(
                LocalDateTime.of(2025, 10, 1, 22, 30),
                LocalDateTime.of(2025, 10, 2, 8, 0),
                SleepQuality.GOOD);

        SleepingSession session2 = new SleepingSession(
                LocalDateTime.of(2025, 10, 2, 22, 45),
                LocalDateTime.of(2025, 10, 3, 8, 30),
                SleepQuality.NORMAL);

        List<SleepingSession> sessions =
                List.of(session1, session2);
        ChronotypeAnalysis analysis = new ChronotypeAnalysis();
        SleepAnalysisResult result = analysis.apply(sessions);
        assertEquals(Chronotype.PIGEON, result.getValue());
    }

    @Test
    void shouldReturnPigeonWhenChronotypesAreEqual() {
        SleepingSession owlSession = new SleepingSession(
                LocalDateTime.of(2025, 10, 1, 23, 30),
                LocalDateTime.of(2025, 10, 2, 9, 30),
                SleepQuality.GOOD
        );

        SleepingSession larkSession = new SleepingSession(
                LocalDateTime.of(2025, 10, 2, 21, 30),
                LocalDateTime.of(2025, 10, 3, 6, 30),
                SleepQuality.GOOD
        );

        List<SleepingSession> sessions =
                List.of(owlSession, larkSession);
        ChronotypeAnalysis analysis = new ChronotypeAnalysis();
        SleepAnalysisResult result = analysis.apply(sessions);
        assertEquals(Chronotype.PIGEON, result.getValue());
    }

    @Test
    void shouldReturnZeroSleeplessNightsForEmptyList() {
        List<SleepingSession> sessions = List.of();
        SleeplessNightsAnalysis analysis =
                new SleeplessNightsAnalysis();
        SleepAnalysisResult result =
                analysis.apply(sessions);
        assertEquals(0L, result.getValue());
    }

    @Test
    void shouldReturnPigeonForEmptySessions() {
        List<SleepingSession> sessions = List.of();
        ChronotypeAnalysis analysis = new ChronotypeAnalysis();
        SleepAnalysisResult result = analysis.apply(sessions);
        assertEquals(Chronotype.PIGEON, result.getValue());
    }
}