package pv260.solid.dip.original;

import java.io.IOException;

public interface RecommendedService {
    String recomendationForTomorrow(double averageTomorrowTemperature) throws IOException;
}
