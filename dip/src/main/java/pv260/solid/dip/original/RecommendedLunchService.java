
package pv260.solid.dip.original;

import java.io.IOException;

public class RecommendedLunchService {
    private final DarkSkyForecastService weatherService;

    public RecommendedLunchService() {

        this.weatherService = new DarkSkyForecastService();
    }

    public String recomendLunchForTomorrow() {
        try {
            return getTommorowLuch(this.weatherService.getAverageTemperatureForTomorrow());
        } catch (IOException e) {
            return "Error when calculating best lunch recommendation for tomorrow";
        }
    }

    private String getTommorowLuch(double temperatureAroundLunch) {
        if (temperatureAroundLunch < -15) {
            return "You will need a lot of energy to keep warm, tomorrow you should eat something very nutritious.";
        } else if (temperatureAroundLunch < 15) {
            return "No day like tomorrow for some chicken.";
        } else if (temperatureAroundLunch < 30) {
            return "It will be quite hot tomorrow, be sure to order a cold beer with your lunch.";
        } else {
            return "You probably will not be hungry at all in such a hot weather. Just get an ice cream!";
        }
    }

   /* private double findTomorrowsTemperatureAroundLunch() throws IOException{
          DailyData tomorrowRecord = this.        tomorrowsWeatherRecord();
          return (tomorrowRecord.getTemperatureMin()+tomorrowRecord.getTemperatureMax()) /2;
    }
*/

    //private DailyData tomorrowsWeatherRecord() throws IOException { }
}