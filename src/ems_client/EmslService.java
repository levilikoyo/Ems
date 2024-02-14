
package ems_client;

import java.util.List;
import model.Budget_trans;
import model.Ohada_trans;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface EmslService {
    
  @POST("budget_transR")
  Call<model.Resultat_sync> ajouter_table_budget_trans(
          @Body
          List <Budget_trans> table_budget_transp, @Query("projet") String projet, @Query("batch") String batch
          
  );
  
   @POST("ohada_transR")
  Call<model.Resultat_sync> ajouter_table_ohada_trans(
          @Body
          List <Ohada_trans> table_ohada_transp
  );
  
}
