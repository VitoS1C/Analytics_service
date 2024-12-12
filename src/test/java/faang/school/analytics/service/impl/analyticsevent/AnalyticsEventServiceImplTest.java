package faang.school.analytics.service.impl.analyticsevent;

import faang.school.analytics.mapper.analyticsevent.AnalyticsEventMapperImpl;
import faang.school.analytics.model.event.AnalyticsEvent;
import faang.school.analytics.model.event.AnalyticsEventFilter;
import faang.school.analytics.model.enums.EventType;
import faang.school.analytics.model.enums.Interval;
import faang.school.analytics.repository.AnalyticsEventRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@ExtendWith(MockitoExtension.class)
class AnalyticsEventServiceImplTest {

    @Mock
    private AnalyticsEventRepository analyticsEventRepository;

    @Spy
    private AnalyticsEventMapperImpl analyticsEventMapper;

    @Mock
    private faang.school.analytics.filter.analyticseventfilter.AnalyticsEventFilter analyticsEventFilter;

    @InjectMocks
    private AnalyticsEventServiceImpl analyticsEventService;

    @BeforeEach
    void setUp() {
        analyticsEventService = new AnalyticsEventServiceImpl(List.of(analyticsEventFilter),
                analyticsEventMapper, analyticsEventRepository);
    }


    @Test
    @DisplayName("Save Event Test")
    void testSaveEvent() {
        var analyticsEvent = new faang.school.analytics.model.entity.AnalyticsEvent();
        analyticsEventService.saveEvent(analyticsEvent);
        verify(analyticsEventRepository).save(any(faang.school.analytics.model.entity.AnalyticsEvent.class));
        verifyNoMoreInteractions(analyticsEventRepository);
    }
    @Test
    @DisplayName("Get Analytics Test")
    void testGetAnalytics() {
        var analyticsEvent = faang.school.analytics.model.entity.AnalyticsEvent.builder()
                .id(1L)
                .receiverId(1L)
                .actorId(2L)
                .eventType(EventType.PROFILE_VIEW)
                .receivedAt(LocalDateTime.parse("2023-12-12T00:00:00"))
                .build();

        var dto = AnalyticsEvent.builder()
                .id(1L)
                .receiverId(1L)
                .actorId(2L)
                .eventType(EventType.PROFILE_VIEW)
                .receivedAt(LocalDateTime.parse("2023-12-12T00:00:00"))
                .build();

        var from = LocalDateTime.parse("2023-05-08T18:32:34.752000");
        var to = LocalDateTime.parse("2024-05-08T18:32:34.752000");

        doReturn(Stream.of(analyticsEvent)).when(analyticsEventRepository).findByReceiverIdAndEventType(anyLong(), any(EventType.class));
        doReturn(true).when(analyticsEventFilter).isApplicable(any(AnalyticsEventFilter.class));
        doReturn(Stream.of(analyticsEvent, analyticsEvent, analyticsEvent)).when(analyticsEventFilter).apply(any(), any(AnalyticsEventFilter.class));


        List<AnalyticsEvent> result = analyticsEventService.getAnalytics(1, EventType.PROFILE_VIEW, Interval.YEAR,
                from, to);

        verify(analyticsEventRepository).findByReceiverIdAndEventType(anyLong(), any(EventType.class));
        verifyNoMoreInteractions(analyticsEventRepository);
        verify(analyticsEventMapper, times(3)).toDto(any(faang.school.analytics.model.entity.AnalyticsEvent.class));
        assertThat(result).isNotNull().hasSize(3);
        assertThat(result.get(0)).isEqualTo(dto);
    }
}