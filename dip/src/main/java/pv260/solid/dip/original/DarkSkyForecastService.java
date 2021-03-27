package pv260.solid.dip.original;

import pv260.solid.dip.original.model.DarkSkyForecastResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import static java.nio.charset.StandardCharsets.UTF_8;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class DarkSkyForecastService implements ForecastInterface{

    private static final String SERVICE_URL = "https://api.forecast.io/forecast/";

    private static final String API_KEY = "fc2a39c15866166ea203cabadf93a236";

    private static final int CONNECTION_TIMEOUT = 500;
    private double longitude;
    private double latitude;
    private boolean locationSet=false;
    private Gson jsonParser;

    public DarkSkyForecastService() {
        this.jsonParser = new Gson();
    }

    //@Override

    public double getAverageTemperatureForTomorrow() throws Exception{
        if(!locationSet) throw new IOException("Location was not set yet");
        DarkSkyForecastResponse.DailyData tomorrowRecord = this.        tomorrowsWeatherRecord();
        return (tomorrowRecord.getTemperatureMin()+tomorrowRecord.getTemperatureMax()) /2;
    }

    @Override
    public void setLocation(double longitude, double latitude) {
        this.longitude=longitude;
        this.latitude=latitude;
        this.locationSet=true;
    }

    private DarkSkyForecastResponse query() throws IOException {
        URL remote = new URL(buildUrl(API_KEY,
                this.longitude,
                this.latitude,
                targetTime(),
                "units=si"));
        HttpURLConnection connection = (HttpURLConnection) remote.openConnection();
        connection.setConnectTimeout(CONNECTION_TIMEOUT);
        try (Reader responseReader = new InputStreamReader(connection.getInputStream(), UTF_8)) {
            return jsonParser.fromJson(responseReader, DarkSkyForecastResponse.class);
        }
    }

    private String buildUrl(String apiKey, double longitude, double latitude, String time, String... queryParams) {
        StringBuilder request =
                new StringBuilder().append(SERVICE_URL)
                                   .append(apiKey)
                                   .append('/').append(longitude).append(',').append(latitude)
                                   .append(',').append(time)
                                   .append('?');
        for (int i = 0; i < queryParams.length; i++) {
            if (i != 0) {
                request.append('&');
            }
            request.append(queryParams[i]);
        }
        return request.toString();
    }

    //these would be other services this depends on

    private static String targetTime() {
        return LocalDate.now().plusDays(2)
                            .atTime(12, 0)
                            .format(DateTimeFormatter.ISO_DATE_TIME);
    }




    private DarkSkyForecastResponse.DailyData tomorrowsWeatherRecord() throws IOException {
        DarkSkyForecastResponse forecast = this.query();
        for (DarkSkyForecastResponse.DailyData record : forecast.getDaily().getData()) {
            LocalDateTime recordTime = LocalDateTime.ofEpochSecond(record.getTime(),
                    0,
                    ZoneOffset.UTC);
            if(recordTime.toLocalDate().equals(LocalDate.now().plusDays(1))){
                return record;
            }
        }
        throw new IllegalStateException("External service did not return record for tomorrow");
    }



}
