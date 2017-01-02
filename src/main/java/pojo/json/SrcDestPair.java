package pojo.json;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

@JsonSerialize(as = ImmutableSrcDestPair.class)
@JsonDeserialize(as = ImmutableSrcDestPair.class)
@ImmutableStyle
@Value.Immutable
public abstract class SrcDestPair {

    public abstract GoogleAddress getSrcAddress();
    public abstract GoogleAddress getDestAddress();

    @Value.Derived
    public String getId() {
        return getSrcAddress().getPlaceId() + "-" + getDestAddress().getPlaceId();
    }

    @Value.Derived
    public String getPath() {
        return getSrcAddress().getPlaceId() + "/" + getDestAddress().getPlaceId();
    }

    public static ImmutableSrcDestPair.Builder builder() {
        return ImmutableSrcDestPair.builder();
    }
}
