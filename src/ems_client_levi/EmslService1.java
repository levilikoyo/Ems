/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ems_client_levi;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 *
 * @author Doshe PC
 */
public interface EmslService1 {

   @POST("stocks/send")// en point pour send picking list
           
   Call<ResponseModel <Boolean>> envoyerPickingList(
     @Body List<Stock> list_tosen
     // @Query("organization") String organization
            
   );  
   
   @GET("stocks/receivepackinglist")
   Call<ResponseModel<List<Stock>>> recevoirPackinglist(
            @Query("num") String numPackingList,
           @Query("organization") String organization
   );
   
   @GET("stocks/sync")
   Call<ResponseModel<List<Stock>>> synchroniseddataFromsub_store(
  @Query("organization") String organization
   );
   
     @POST("ohada_trans/sync")
 Call<ResponseModel <List<Ohada_trans>>> envoyerOhada_trans(
     @Body List<Ohada_trans> sync_tosend, 
     @Query("organization") String organization,
     @Query("mois") String mois
   );
 
   
}
