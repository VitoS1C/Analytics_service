package faang.school.analytics.mapper.analyticsevent;

import faang.school.analytics.model.event.AnalyticsEvent;
import faang.school.analytics.model.event.CommentEvent;
import faang.school.analytics.model.event.FollowerEvent;
import faang.school.analytics.model.event.FundRaisedEvent;
import faang.school.analytics.model.event.GoalCompletedEvent;
import faang.school.analytics.model.event.LikeEvent;
import faang.school.analytics.model.event.MentorshipRequestedEvent;
import faang.school.analytics.model.event.PostViewEvent;
import faang.school.analytics.model.event.PremiumBoughtEvent;
import faang.school.analytics.model.event.ProjectViewEvent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AnalyticsEventMapper {

    AnalyticsEvent toDto(faang.school.analytics.model.entity.AnalyticsEvent analyticsEvent);

    @Mapping(target = "receiverId", source = "followeeId")
    @Mapping(target = "actorId", source = "followerId")
    @Mapping(target = "receivedAt", source = "subscribedAt")
    faang.school.analytics.model.entity.AnalyticsEvent toEntity(FollowerEvent followerEvent);

    @Mapping(target = "receiverId", source = "postAuthorId")
    @Mapping(target = "actorId", source = "userId")
    @Mapping(target = "receivedAt", source = "likedTime")
    faang.school.analytics.model.entity.AnalyticsEvent toEntity(LikeEvent likeEvent);

    @Mapping(target = "receiverId", source = "commentId")
    @Mapping(target = "actorId", source = "authorId")
    @Mapping(target = "receivedAt", source = "commentedAt")
    faang.school.analytics.model.entity.AnalyticsEvent toEntity(CommentEvent commentEvent);

    @Mapping(target = "receiverId", source = "goalId")
    @Mapping(target = "actorId", source = "userId")
    @Mapping(target = "receivedAt", source = "completedAt")
    faang.school.analytics.model.entity.AnalyticsEvent toEntity(GoalCompletedEvent goalCompletedEvent);

    @Mapping(target = "receiverId", source = "projectId")
    @Mapping(target = "actorId", source = "userId")
    @Mapping(target = "receivedAt", source = "visitTime")
    faang.school.analytics.model.entity.AnalyticsEvent toEntity(ProjectViewEvent event);

    @Mapping(source = "userId", target = "actorId")
    @Mapping(source = "requestedAt", target = "receivedAt")
    faang.school.analytics.model.entity.AnalyticsEvent toEntity(MentorshipRequestedEvent mentorshipRequestedEvent);

    @Mapping(target = "actorId", source = "userId")
    @Mapping(target = "receivedAt", source = "observeTime")
    faang.school.analytics.model.entity.AnalyticsEvent toEntity(PremiumBoughtEvent premiumBoughtEvent);

    @Mapping(target = "actorId", source = "authorPostId")
    @Mapping(target = "receiverId", source = "postId")
    @Mapping(target = "receivedAt", source = "viewTime")
    faang.school.analytics.model.entity.AnalyticsEvent toEntity(PostViewEvent postViewEvent);

    @Mapping(source = "userId", target = "actorId")
    @Mapping(source = "donatedAt", target = "receivedAt")
    faang.school.analytics.model.entity.AnalyticsEvent toEntity(FundRaisedEvent fundRaisedEvent);
}
