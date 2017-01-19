package pojo.json;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import java.util.List;

@JsonSerialize(as = ImmutableTrafficStatusData.class)
@JsonDeserialize(as = ImmutableTrafficStatusData.class)
@ImmutableStyle
@Value.Immutable
public abstract class TrafficStatusData {
    public abstract String getSrc();

    public abstract String getDest();

    public abstract List<Data> getData();

    public static ImmutableTrafficStatusData.Builder builder() {
        return ImmutableTrafficStatusData.builder();
    }

    @JsonSerialize(as = ImmutableData.class)
    @JsonDeserialize(as = ImmutableData.class)
    @ImmutableStyle
    @Value.Immutable
    public static abstract class Data {
        public abstract Double getTime();
        public abstract Double getDuration();
        public abstract Double getReverseDuration();

        public static ImmutableData.Builder builder() {
            return ImmutableData.builder();
        }
    }
}
