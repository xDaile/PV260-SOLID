
package pv260.solid.dip.original;


public class Main {

    public static void main(String[] args) throws Exception {

        DarkSkyForecastService forecastService1= new DarkSkyForecastService();
        OpenWeatherMapService forecastService2= new OpenWeatherMapService();
        RecommendedOutfitService outfitService = new RecommendedOutfitService();
        RecommendedLunchService lunchService = new RecommendedLunchService();
        System.out.println("o--         Awesome Lifestyle Page               --o");
        System.out.println("Tomorrow, it would be wise to dress like this:");
        System.out.println(outfitService.recomendationForTomorrow(forecastService1.getAverageTemperatureForTomorrow()));
        System.out.println("For lunch, we recommend that you:");
        System.out.println(lunchService.recomendationForTomorrow(forecastService2.getAverageTemperatureForTomorrow()));
        System.out.println("o--                                              --o");
    }

}
