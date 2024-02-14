
package journals;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface etat_de_besoin_Service {
    
   @GET("engins")
   Call<List<etat_de_besoin_engin>> listEngins();
   
   @FormUrlEncoded
   @POST("engins")
   Call<etat_de_besoin_engin> ajouterEngin(
           @Field("no")
           String no,
           @Field("demandeur")
           String demandeur,
           @Field("description")
           String description,
           @Field("qty")
           String qty,
            @Field("unite")
           String unite,
             @Field("up")
           String up,
              @Field("pt")
           String pt,
           @Field("device")
           String device
   );  
}

/*public interface etat_de_besoin_Service {
    
   @GET("engins")
   Call<List<etat_de_besoin_engin>> listEngins();
   
   @FormUrlEncoded
   @POST("engins")
   Call<etat_de_besoin_engin> ajouterEngin(
           @Field("designation")
           String designation,
           @Field("description")
           String description
         
   );  
}*/
