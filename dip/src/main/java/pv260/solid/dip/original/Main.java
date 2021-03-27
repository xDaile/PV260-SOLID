
package pv260.solid.dip.original;


public class Main {

    public static void main(String[] args) throws Exception {
       // RecommendedOutfitService outfitService = new RecommendedOutfitService();
        RecommendedLunchService lunchService = new RecommendedLunchService();
        System.out.println("o--         Awesome Lifestyle Page               --o");
        System.out.println("Tomorrow, it would be wise to dress like this:");
     //   System.out.println(outfitService.recommendOutfitForTomorrow());
        System.out.println("For lunch, we recommend that you:");
        System.out.println(lunchService.recomendLunchForTomorrow());
        System.out.println("o--                                              --o");
    }

}
