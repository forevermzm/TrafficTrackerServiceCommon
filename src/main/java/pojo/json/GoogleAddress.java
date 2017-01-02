package pojo.json;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.maps.model.GeocodingResult;
import org.immutables.value.Value;

import static com.google.common.base.Preconditions.checkNotNull;

@JsonSerialize(as = ImmutableGoogleAddress.class)
@JsonDeserialize(as = ImmutableGoogleAddress.class)
@ImmutableStyle
@Value.Immutable
public abstract class GoogleAddress {
    public abstract String getPlaceId();
    public abstract String getFormattedAddress();

    public static ImmutableGoogleAddress.Builder builder() {
        return ImmutableGoogleAddress.builder();
    }

    public static GoogleAddress fromGeocodingResult(GeocodingResult geocodingResult) {
        checkNotNull(geocodingResult);
        return builder()
                .withPlaceId(geocodingResult.placeId)
                .withFormattedAddress(geocodingResult.formattedAddress)
                .build();
    }
}
