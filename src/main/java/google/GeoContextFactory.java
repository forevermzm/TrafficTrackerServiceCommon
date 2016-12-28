package google;

import com.google.maps.GeoApiContext;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class GeoContextFactory {
    private static final String googleApiKey = System.getProperty("googleApiKey");
    private static final GeoApiContext context =
            new GeoApiContext().setApiKey(googleApiKey);

    @Bean
    public static GeoApiContext createApiContext() {
        return context;
    }
}
