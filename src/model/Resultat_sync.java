/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 *
 * @author Doshe PC
 */
public class Resultat_sync {
    
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("data")
    @Expose
    private List<Budget_trans> data;

    public Resultat_sync(String msg, List<Budget_trans> data) {
        this.msg = msg;
        this.data = data;
    }
    
    

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<Budget_trans> getData() {
        return data;
    }

    public void setData(List<Budget_trans> data) {
        this.data = data;
    }
    
    
    
    
}
