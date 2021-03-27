
package pv260.solid.dip.original;

import java.io.IOException;


public class RecommendedLunchService implements RecommendedService {

    @Override
    public String recomendationForTomorrow(double averageTomorrowTemperature) throws IOException {
        if (averageTomorrowTemperature < -15.0) {
            return "You will need a lot of energy to keep warm, tomorrow you should eat something very nutritious.";
        } else if (averageTomorrowTemperature < 15) {
            return "No day like tomorrow for some chicken.";
        } else if (averageTomorrowTemperature < 30) {
            return "It will be quite hot tomorrow, be sure to order a cold beer with your lunch.";
        } else {
            return "You probably will not be hungry at all in such a hot weather. Just get an ice cream!";
        }
    }
}