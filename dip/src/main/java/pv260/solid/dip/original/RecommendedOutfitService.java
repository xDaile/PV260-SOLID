package pv260.solid.dip.original;


/*import java.io.IOException;
import java.time.LocalDate;
import static java.time.format.DateTimeFormatter.ISO_DATE;
import pv260.solid.dip.original.model.OpenWeatherMapResponse;
import pv260.solid.dip.original.model.OpenWeatherMapResponse.ForecastTime;
import pv260.solid.dip.original.model.OpenWeatherMapResponse.Temperature;
*/

import java.io.IOException;

public class RecommendedOutfitService {

    private final OpenWeatherMapService weatherService;

    public RecommendedOutfitService() {
        this.weatherService = new OpenWeatherMapService();
    }

    public String recommendOutfitForTomorrow() throws IOException {
        double averageTemperature = this.weatherService.getAverageTemperatureForTomorrow();
        return getOutfit(averageTemperature);


    }

    private String getOutfit( double averageTemperature){
        if (averageTemperature < -10) {
            return "It will be super cold, weak a jacket or two!";
        } else if (averageTemperature < 0) {
            return "It will be rather chilly, better wear a coat.";
        } else if (averageTemperature < 15) {
            return "Weather will be very pleasant, weak a light jacket and jeans.";
        } else if (averageTemperature < 25) {
            return "Tomorrow will be a beautiful day, shirt and shorts should be fine.";
        } else {
            return "It will be really hot, better grab a swimsuit and run to the beach!";
        }
    }
    /*
    private double findTomorrowsAverageTemperature() throws IOException {
        OpenWeatherMapService temperatureService=new OpenWeatherMapService();
        return temperatureService.getAverageTemperatureForTomorrow();
       /* Temperature tomorrowTemperature = this.obtainTomorrowTemperatureRecord();
        return (tomorrowTemperature.getMorn()
                + tomorrowTemperature.getDay()
                + tomorrowTemperature.getEve()) / 3;
    }

*/



}
