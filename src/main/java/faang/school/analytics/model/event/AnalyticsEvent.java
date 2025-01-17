package faang.school.analytics.model.event;

import com.fasterxml.jackson.annotation.JsonFormat;
import faang.school.analytics.model.enums.EventType;
import lombok.Builder;

import java.time.LocalDateTime;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;

@Builder
public record AnalyticsEvent(
        Long id,
        Long receiverId,
        Long actorId,
        EventType eventType,

        @JsonFormat(shape = STRING)
        LocalDateTime receivedAt
) {
}
