/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ems_client_levi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *
 * @author Doshe PC
 */
public class RetrofitClient {
     private static Retrofit retrofit;
    private static final String BASE_URL = "https://api-emsl-production.up.railway.app/";


    private static Retrofit getClient(String baseUrl) {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(baseUrl)
                    .build();
        }
        return retrofit;
    }

    public static EmslService1 getAPIService() {
        return RetrofitClient.getClient(BASE_URL).create(EmslService1.class);
    }
    
}
