package pojo.json;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Map;

@JsonSerialize(as = ImmutableTrafficStatusDocument.class)
@JsonDeserialize(as = ImmutableTrafficStatusDocument.class)
@ImmutableStyle
@Value.Immutable
public abstract class TrafficStatusDocument {
    public abstract String getSrcAddress();

    public abstract String getDestAddress();

    public abstract Map<GoogleTravelMode, List<TimeDurationPair>> getTrafficStatuses();

    public abstract Map<GoogleTravelMode, List<TimeDurationPair>> getReversedTrafficStatuses();

    public abstract Instant getLastUpdatedTime();

    public abstract int getUpdateCounter();

    public static ImmutableTrafficStatusDocument.Builder builder() {
        return ImmutableTrafficStatusDocument.builder();
    }

    @JsonSerialize(as = ImmutableTimeDurationPair.class)
    @JsonDeserialize(as = ImmutableTimeDurationPair.class)
    @ImmutableStyle
    @Value.Immutable
    public static abstract class TimeDurationPair {
        public abstract Instant getTime();

        public abstract Duration getDuration();

        public static ImmutableTimeDurationPair.Builder builder() {
            return ImmutableTimeDurationPair.builder();
        }
    }
}
