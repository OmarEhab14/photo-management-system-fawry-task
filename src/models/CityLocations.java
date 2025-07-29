package models;

import java.util.HashMap;
import java.util.Map;

public class CityLocations {
    private static final Map<String, LocationZone> CITY_LOCATIONS = new HashMap<>();

    static {
        CITY_LOCATIONS.put("cairo", new LocationZone(30.0444, 31.2357, 30));
        CITY_LOCATIONS.put("giza", new LocationZone(29.9870, 31.2118, 20));
        CITY_LOCATIONS.put("alexandria", new LocationZone(31.2001, 29.9187, 25));
    }

    public static LocationZone get(String cityName) {
        return CITY_LOCATIONS.get(cityName);
    }
}
