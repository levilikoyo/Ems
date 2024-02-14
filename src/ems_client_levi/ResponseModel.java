/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ems_client_levi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 *
 * @author Doshe PC
 * @param <T>
 */
public class ResponseModel<T>{
 @SerializedName("data")
    @Expose
private T data;
  
   @SerializedName("errorMessage")
    @Expose
private String errorMessage;
      
      @SerializedName("isSuccess")
    @Expose
private boolean success ;

    public ResponseModel(T data, String errorMessage, boolean success) {
        this.data = data;
        this.errorMessage = errorMessage;
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "ResponseModel{" + "data=" + data + ", errorMessage=" + errorMessage + ", success=" + success + '}';
    }

  

   


}


