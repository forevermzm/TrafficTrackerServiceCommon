package pojo.json;

import com.google.maps.model.TravelMode;

public enum GoogleTravelMode {
    DRIVING,

    BUS,

    WALKING,

    BIKING;

    public static GoogleTravelMode fromTravelMode(TravelMode mode) {
        switch (mode) {
            case DRIVING: return DRIVING;
            case TRANSIT: return BUS;
            case WALKING: return WALKING;
            case BICYCLING: return BIKING;
        }
        throw new IllegalArgumentException("Unknown mode.");
    }
}
