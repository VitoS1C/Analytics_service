package faang.school.analytics.service;

import faang.school.analytics.model.event.AnalyticsEvent;
import faang.school.analytics.model.enums.EventType;
import faang.school.analytics.model.enums.Interval;

import java.time.LocalDateTime;
import java.util.List;

public interface AnalyticsEventService {

    void saveEvent(faang.school.analytics.model.entity.AnalyticsEvent entity);

    List<AnalyticsEvent> getAnalytics(long receiverId, EventType eventType, Interval interval,
                                      LocalDateTime from, LocalDateTime to);
}
