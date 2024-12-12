package faang.school.analytics.model.event;

import faang.school.analytics.model.enums.Interval;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record AnalyticsEventFilter(
        Interval interval,
        LocalDateTime from,
        LocalDateTime to
) {
}
