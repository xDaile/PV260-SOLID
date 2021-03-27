package pv260.solid.dip.original;

import pv260.solid.dip.original.model.DarkSkyForecastResponse;
import pv260.solid.dip.original.model.OpenWeatherMapResponse;

import java.io.IOException;

public interface ForecastInterface {
    double getAverageTemperatureForTomorrow() throws Exception;
    void setLocation(double longitude, double latitude);

}
