package dao;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.maps.DirectionsApi;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.DirectionsLeg;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.TravelMode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pojo.json.GoogleAddress;
import pojo.json.GoogleTravelMode;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Map;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

@Component
public class MapsDAO {
    private final static Logger LOG = LogManager.getFormatterLogger();

    private final GeoApiContext context;

    private static final List<TravelMode> CARED_MODES;

    static {
        CARED_MODES = ImmutableList.<TravelMode>builder()
                .add(TravelMode.DRIVING)
                .add(TravelMode.TRANSIT)
                .add(TravelMode.WALKING)
                .add(TravelMode.BICYCLING)
                .build();
    }

    @Autowired
    public MapsDAO(GeoApiContext context) {
        this.context = checkNotNull(context);
    }

    public GoogleAddress getGoogleAddress(String address) {
        checkArgument(Strings.isNotBlank(address));
        try {
            GeocodingResult[] result = GeocodingApi.geocode(context, address).await();

            if (result.length != 1) {
                throw new RuntimeException("Cannot understand address: " + address);
            }

            return GoogleAddress.fromGeocodingResult(result[0]);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Map<GoogleTravelMode, Duration> getGoogleDirection(String srcAddress, String destAddress, Instant time) {
        checkArgument(Strings.isNotBlank(srcAddress));
        checkArgument(Strings.isNotBlank(destAddress));
        checkNotNull(time);

        try {
            ImmutableMap.Builder<GoogleTravelMode, Duration> travelResults = ImmutableMap.builder();
            for (TravelMode mode: CARED_MODES) {
                DirectionsResult result = DirectionsApi
                        .getDirections(context, srcAddress, destAddress)
                        .departureTime(new org.joda.time.Instant(time.toEpochMilli()))
                        .mode(mode)
                        .await();

                if (result.routes.length == 0 || result.routes[0].legs.length == 0) {
                    LOG.info("Unknown directions result: " + result.routes);
                }

                DirectionsLeg leg = result.routes[0].legs[0];
                Duration travelTime = Duration.ofSeconds(leg.duration.inSeconds);
                if (leg.durationInTraffic != null) {
                    travelTime = Duration.ofSeconds(leg.durationInTraffic.inSeconds);
                }

                travelResults.put(GoogleTravelMode.fromTravelMode(mode), travelTime);
            }
            return travelResults.build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
