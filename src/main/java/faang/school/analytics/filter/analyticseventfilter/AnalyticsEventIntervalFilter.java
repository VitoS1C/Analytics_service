package faang.school.analytics.filter.analyticseventfilter;

import faang.school.analytics.model.event.AnalyticsEventFilter;
import faang.school.analytics.model.entity.AnalyticsEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.stream.Stream;

@Component
public class AnalyticsEventIntervalFilter implements faang.school.analytics.filter.analyticseventfilter.AnalyticsEventFilter {

    @Override
    public boolean isApplicable(AnalyticsEventFilter filters) {
        return filters.interval() != null;
    }

    @Override
    public Stream<AnalyticsEvent> apply(Stream<AnalyticsEvent> eventStream, AnalyticsEventFilter filters) {
        return eventStream.filter(event ->
                event.getReceivedAt().plus(filters.interval().getTimeInterval())
                        .isAfter(LocalDateTime.now()));
    }
}
