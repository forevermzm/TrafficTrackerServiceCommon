package dao;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pojo.json.GoogleAddress;

import static com.google.common.base.Preconditions.checkNotNull;

@Component
public class MapsDAO {
    private final static Logger LOG = LogManager.getFormatterLogger();

    private final GeoApiContext context;

    @Autowired
    public MapsDAO(GeoApiContext context) {
        this.context = checkNotNull(context);
    }

    public GoogleAddress getGoogleAddress(String address) {
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
}
