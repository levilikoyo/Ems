
package ems_client;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Engin {
    
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("designation")
    @Expose
    private String designation;
    @SerializedName("description")
    @Expose
    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Data {id : " + id +", designation : "+ designation + ", description : "+ description + "}";
    }
    
    
    
}
