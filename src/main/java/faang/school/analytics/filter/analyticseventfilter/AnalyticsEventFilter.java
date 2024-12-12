package faang.school.analytics.filter.analyticseventfilter;

import faang.school.analytics.model.entity.AnalyticsEvent;

import java.util.stream.Stream;

public interface AnalyticsEventFilter {

    boolean isApplicable(faang.school.analytics.model.event.AnalyticsEventFilter filters);

    Stream<AnalyticsEvent> apply(Stream<AnalyticsEvent> eventStream, faang.school.analytics.model.event.AnalyticsEventFilter filters);
}
