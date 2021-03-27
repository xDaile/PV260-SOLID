package pv260.solid.dip.original;


/*import java.io.IOException;
import java.time.LocalDate;
import static java.time.format.DateTimeFormatter.ISO_DATE;
import pv260.solid.dip.original.model.OpenWeatherMapResponse;
import pv260.solid.dip.original.model.OpenWeatherMapResponse.ForecastTime;
import pv260.solid.dip.original.model.OpenWeatherMapResponse.Temperature;
*/

import java.io.IOException;

public class RecommendedOutfitService implements RecommendedService{

    @Override
    public String recomendationForTomorrow(double averageTomorrowTemperature) throws IOException {
        if (averageTomorrowTemperature < -10) {
            return "It will be super cold, weak a jacket or two!";
        } else if (averageTomorrowTemperature < 0) {
            return "It will be rather chilly, better wear a coat.";
        } else if (averageTomorrowTemperature < 15) {
            return "Weather will be very pleasant, weak a light jacket and jeans.";
        } else if (averageTomorrowTemperature < 25) {
            return "Tomorrow will be a beautiful day, shirt and shorts should be fine.";
        } else {
            return "It will be really hot, better grab a swimsuit and run to the beach!";
        }
    }

}
