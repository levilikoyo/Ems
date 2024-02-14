
package journals;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class etat_de_besoin_RetrofitClient {
    
    private static Retrofit retrofit;
    private static final String BASE_URL = "http://localhost:4000/";


    private static Retrofit getClient(String baseUrl) {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(baseUrl)
                    .build();
        }
        return retrofit;
    }

    public static etat_de_besoin_Service getAPIService() {
        return etat_de_besoin_RetrofitClient.getClient(BASE_URL).create(etat_de_besoin_Service.class);
    }
    
}
