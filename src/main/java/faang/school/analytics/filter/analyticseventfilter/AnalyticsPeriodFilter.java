package faang.school.analytics.filter.analyticseventfilter;

import faang.school.analytics.model.event.AnalyticsEventFilter;
import faang.school.analytics.model.entity.AnalyticsEvent;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
public class AnalyticsPeriodFilter implements faang.school.analytics.filter.analyticseventfilter.AnalyticsEventFilter {
    @Override
    public boolean isApplicable(AnalyticsEventFilter filters) {
        return filters.interval() == null && filters.from() != null && filters.to() != null;
    }

    @Override
    public Stream<AnalyticsEvent> apply(Stream<AnalyticsEvent> eventStream, AnalyticsEventFilter filters) {
        return eventStream.filter(event ->
                event.getReceivedAt().isAfter(filters.from()) && event.getReceivedAt().isBefore(filters.to()));
    }
}
